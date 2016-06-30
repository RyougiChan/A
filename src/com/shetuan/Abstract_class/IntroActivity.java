package com.shetuan.Abstract_class;

import android.app.Activity;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public abstract class IntroActivity extends Activity {
	private static final String FLAG_RESOURCE = "FLAG_RESOURCE";
	/**
	* ��̨������ɵı�־��
	*/
	private static final byte BACKGROUND_FINISH = 0x01;
	/**
	* ǰ̨������ɵı�־��
	*/
	private static final byte FRONTGROUND_FINISH = 0x10;
	/**
	* ��ʾҪ���ſ���������
	*/
	private static final int INTRO_PLAY = 0;
	/**
* ������������Դ��
*/
	private List<IntroImgResource> mResources;
	/**
	* ͼƬ������ɫ��Ĭ��Ϊ��ɫ��
	*/
	private int mBackgroundColor = 0xFFFFFFFF;
	/**
	* UI�̡߳�
	*/
	private Handler mUiHandler;
	/**
	* ������ʾ������
	*/
	private ImageView mIntroImage;
	/**
	* ��Ļ����
	*/
	private int mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	init();
	runOnMainThread();
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	WindowManager.LayoutParams.FLAG_FULLSCREEN);
	this.setRequestedOrientation(mOrientation);
	this.setContentView(createLayout());
	setIntroResources(mResources);
	startOnBackground();
	showIntro();
	}
	private void init() {
	mResources = new ArrayList<IntroImgResource>();
	mUiHandler = new UIHandler(this);
	}
	/**
	*���ÿ���������ͼƬ��Դ��
	*
	* @param resources
	*����������ͼƬ��Դ��
	*/
	protected abstract void setIntroResources(List<IntroImgResource> resources);
	/**
	*������һ��Ҫ������Activity��
	*
	*@return ��һ��Ҫ������Activity��
	*/
	protected abstract Class<?> nextActivity();
	/**
	*��ʾ����������
	*/
	protected void showIntro() {
	int delayTime = 0;
	for (final IntroImgResource resource : mResources) {
	Message msg = new Message();
	msg.what = INTRO_PLAY;
	Bundle data = new Bundle();
	data.putSerializable(FLAG_RESOURCE, resource);
	msg.setData(data);
	mUiHandler.sendMessageDelayed(msg, delayTime);
	delayTime += resource.playerTime;
	}
	mUiHandler.sendEmptyMessageDelayed(FRONTGROUND_FINISH, delayTime);
	}
	/**
	* ִ�к�ʱ�Ĳ�����
	*/
	private void startOnBackground() {
	HandlerFactory.newHandlerInOtherThread("intro_bg").post(
	new Runnable() {
	@Override
	public void run() {
	runOnBackground();
	mUiHandler.sendEmptyMessage(0x1);
	}
	});
	}
	/**
	* ��������ʱ�Ľ���Layout��
	*
	* @return ���ش����Ľ���Layout.
	*/
	private View createLayout() {
	FrameLayout layout = new FrameLayout(this);
	ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
	LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	layout.setLayoutParams(layoutParams);
	layout.setBackgroundColor(getBackgroundColor());
	mIntroImage = new ImageView(this);
	FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
	FrameLayout.LayoutParams.MATCH_PARENT,
	FrameLayout.LayoutParams.MATCH_PARENT);
	params.gravity = Gravity.CENTER;
	layout.addView(mIntroImage, params);
	return layout;
	}
	/**
	* ��ȡͼƬ������
	*
	* @return
	*/
	public int getBackgroundColor() {
	return mBackgroundColor;
	}
	/**
	* ����ͼƬ������
	*
	* @param backgroundColor
	*/
	public void setBackgroundColor(int backgroundColor) {
	this.mBackgroundColor = backgroundColor;
	}
/**
	* ������Ļ����
	*
	* @return
	*/
	public int getmOrientation() {
	return mOrientation;
	}
	/**
	* ������Ļ�ķ���Ĭ����������
	*
	* @param mOrientation
	* ��Ļ����ActivityInfo.SCREEN_ORIENTATION_PORTRAIT������ActivityInfo.
	* SCREEN_ORIENTATION_LANDSCAPE��
	*/
	public void setmOrientation(int mOrientation) {
	this.mOrientation = mOrientation;
	}
	/**
	* ��ǰ̨��ִ�еĴ��롣����Խ�����к������������ã������ִ��setmOrientation()������
	*/
	protected void runOnMainThread() {
	}
	/**
	* �ں�̨��ִ�еĴ��롣�ڴ˽��бȽϺ�ʱ�Ĳ�����
	*/
	protected void runOnBackground() {
	}
	protected static class UIHandler extends Handler {
	/**
	* �Ƿ���Ҫ�ȴ���
	*/
	private int isWaiting = 0;
	private WeakReference<IntroActivity> activity;
	public UIHandler(IntroActivity activity) {
	this.activity = new WeakReference<IntroActivity>(activity);
	}
	public void handleMessage(android.os.Message msg) {
	if (msg.what == INTRO_PLAY) {
	IntroImgResource resource = (IntroImgResource) msg.getData()
	.getSerializable(FLAG_RESOURCE);
	AlphaAnimation animation = new AlphaAnimation(
	resource.startAlpha, 1f);
	animation.setDuration(resource.playerTime);
	IntroActivity intro = activity.get();
	if (intro != null) {
	if (resource.isExpand) {
	intro.mIntroImage.setScaleType(ScaleType.FIT_XY);
	} else {
	intro.mIntroImage.setScaleType(ScaleType.CENTER);
	}
	intro.mIntroImage.setImageResource(resource.mResId);
	intro.mIntroImage.startAnimation(animation);
	}
	return;
	}
	if (msg.what == BACKGROUND_FINISH || msg.what == FRONTGROUND_FINISH) {
	isWaiting |= msg.what;
	// ����̨��ǰ̨������δ���ʱ����ִ��Activity����ת��
	if (isWaiting == (BACKGROUND_FINISH | FRONTGROUND_FINISH)) {
	IntroActivity intro = activity.get();
	if (intro != null) {
	intro.startActivity(new Intent(intro, intro
	.nextActivity()));
	intro.finish();
	}
	}
	}
	};
	}
	/**
	* ����������ͼƬ��Դ�ࡣ��װ��ͼƬ������ʱ�䡢��ʼʱ��͸���̶ȡ�
	*
	* @author msdx
	*
	*/
	protected class IntroImgResource implements Serializable {
	/**
	* ���л�ID��
	*/
	private static final long serialVersionUID = -2257252088641281804L;
	/**
	* ��ԴͼƬID.
	*/
	private int mResId;
	/**
	* ����ʱ�䣬��λΪ���롣
	*/
	private int playerTime;
	/**
	* ��ʼʱ��͸���̶ȡ�0-1֮�䡣
	*/
	private float startAlpha;
	/**
	* ͼƬ�Ƿ���չ��
	*/
	private boolean isExpand;
	/**
	* ����������Դ�Ĺ��췽����
	*
	* @param mResId
	* ͼƬ��Դ��ID��
	* @param playerTime
	* ͼƬ��Դ�Ĳ���ʱ�䣬��λΪ���롣��
	* @param startAlpha
	*ͼƬ��Դ��ʼʱ��͸���̶ȡ�0-255֮�䡣
*/
	public IntroImgResource(int mResId, int playerTime, float startAlpha, boolean isExpand) {
	super();
	this.mResId = mResId;
	this.playerTime = playerTime;
	this.startAlpha = startAlpha;
	this.isExpand = isExpand;
	}
	/**
	* ��ȡ��ԴͼƬID��
	*
	* @return ��ԴͼƬID��
	*/
	public int getmResId() {
	return mResId;
	}
	/**
	* ������ԴͼƬID.
	*
	* @param mResId
	* Ҫ���õ���ԴͼƬID.
	*/
	public void setmResId(int mResId) {
	this.mResId = mResId;
	}
	/**
	* ������ԴͼƬ�Ĳ���ʱ�䡣
	*
	* @return ��ԴͼƬ�Ĳ���ʱ�䡣
	*/
	public int getPlayerTime() {
	return playerTime;
	}
	/**
	* ������ԴͼƬ�Ĳ���ʱ�䡣
	*
	* @param playerTime
	* ��ԴͼƬ�Ĳ���ʱ�䡣
	*/
	public void setPlayerTime(int playerTime) {
	this.playerTime = playerTime;
	}
	/**
	* �õ���Դ��ʼʱ��͸���̶ȡ�
	*
	* @return
	*/
	public float getStartAlpha() {
	return startAlpha;
	}
	/**
	* ������Դ��ʼʱ��͸���̶ȡ�
	*
	* @param startAlpha
	*/
	public void setStartAlpha(float startAlpha) {
	this.startAlpha = startAlpha;
	}
	/**
	* ����ͼƬ�Ƿ�������չ��
	*
	* @return
	*/
	public boolean isExpand() {
	return isExpand;
	}
	/**
	* ����ͼƬ�Ƿ���չ��
	*
	* @param isExpand
	* ���Ϊtrue����ͼƬ�ᱻ������ȫ��Ļ��С����չʾ������ԭ��Сչʾ��
	*/
	public void setExpand(boolean isExpand) {
	this.isExpand = isExpand;
	}
	}
	}

