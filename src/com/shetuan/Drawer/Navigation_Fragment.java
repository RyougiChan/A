package com.shetuan.Drawer;

import java.util.ArrayList;

import com.shetuan.Fragment.Foucs_Fragment;
import com.shetuan.Fragment.Index_Fragment_Container;
import com.shetuan.Fragment.Private_message_Fragment;
import com.shetuan.Fragment.SettingActivity;
import com.shetuan.Fragment.User_Fragment;
import com.shetuan.clubgathering.R;
import com.shetuan.Drawer.DrawerArrowDrawable;
import com.shetuan.Drawer.ActionBarDrawerToggle;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Navigation_Fragment extends FragmentActivity implements OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	private RelativeLayout mDrawer;
	private RelativeLayout userMsg;
	private ListView mDrawerList;
	private ArrayList<String> menuLists;
	private ArrayAdapter<String> adapter;
	private String mTitle;
	private FragmentManager fm = getSupportFragmentManager();
	private DrawerArrowDrawable drawerArrow;
	private ActionBarDrawerToggle mDrawerToggle;
	private TextView userName;
	private TextView userSign;
	private ImageView userAvatar;
	private TextView settingTv;
	private TextView exitTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_navigation_drawer);
		ActionBar ab = getActionBar();
		// �_��ActionBar��APP icon�Ĺ���
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setHomeButtonEnabled(true);

		// //ȡ��ActionBar
		// actionBar = getActionBar();
		// //���ò���ʾ����
		// actionBar.setDisplayShowTitleEnabled(false);
		// //������ʾlogo
		// actionBar.setDisplayShowHomeEnabled(true);
		// actionBar.setDisplayUseLogoEnabled(true);
		// actionBar.setLogo(R.drawable.netease_top);
		// //����actionbar����
		// Drawable background
		// =getResources().getDrawable(R.drawable.top_bar_background);
		// actionBar.setBackgroundDrawable(background);
		// actionBar.setDisplayHomeAsUpEnabled(true);
		// ����actionBar
		// ActionBar actionBar = getActionBar();
		// actionBar.setDisplayShowHomeEnabled(false);

		// ��ʼ������
		mTitle = (String) getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawer = (RelativeLayout) findViewById(R.id.mDrawer);
		userName = (TextView) findViewById(R.id.userName);
		userSign = (TextView) findViewById(R.id.userSign);
		userAvatar = (ImageView) findViewById(R.id.userAvatar);
		userMsg = (RelativeLayout) findViewById(R.id.userMsg);
		settingTv = (TextView) findViewById(R.id.setting);
		exitTv = (TextView) findViewById(R.id.exit);

		drawerArrow = new DrawerArrowDrawable(this) {
			@Override
			public boolean isLayoutRtl() {
				return false;
			}
		};
		mDrawerList.setOnItemClickListener(this);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, drawerArrow, R.string.menu_open,
				R.string.menu_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu(); // Call onPrepareOptionMenu

			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		menuLists = new ArrayList<String>();
		menuLists.add("��ҳ");
		menuLists.add("��ע");
		menuLists.add("˽��");
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuLists);
		mDrawerList.setAdapter(adapter);

		// ������ҳ
		init();
		userMsg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle("�û�����");
				initFragment(new User_Fragment());
			}
		});
		// ��ʼ�����ö���
		settingTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle("����");
				Intent intent = new Intent(Navigation_Fragment.this, SettingActivity.class);
				startActivity(intent);
				mDrawerLayout.closeDrawers();
			}
		});
		// ��ʼ���˳�����
		exitTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ActivityManager am = (ActivityManager)getSystemService
				// (Context.ACTIVITY_SERVICE);
				// am.restartPackage(getPackageName());

				// android.os.Process.killProcess(android.os.Process.myPid());
				// // ��ȡPID
				// System.exit(0); // ����java��c#�ı�׼�˳���������ֵΪ0���������˳�

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
			}
		});
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean isDrawrOpen = mDrawerLayout.isDrawerOpen(mDrawer);
		menu.findItem(R.id.search).setVisible(!isDrawrOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawer)) {
				mDrawerLayout.closeDrawer(mDrawer);
			} else {
				mDrawerLayout.openDrawer(mDrawer);
			}
		}
		// // ��ActionBar��ͼ����drawer�������
		// if (mDrawerToggle.onOptionsItemSelected(item)) {
		// return true;
		// }

		switch (item.getItemId()) {
		case R.id.search:
			setTitle("����");
			Intent search_intent = new Intent(Navigation_Fragment.this, Search.class);
			startActivity(search_intent);
			mDrawerLayout.closeDrawers();
			break;
		case R.id.message:
			setTitle("˽��");
			initFragment(new Private_message_Fragment());
			mDrawerLayout.closeDrawers();
			break;
		case R.id.action_settings:
			setTitle("����");
			Intent setting_intent = new Intent(Navigation_Fragment.this, SettingActivity.class);
			startActivity(setting_intent);
			mDrawerLayout.closeDrawers();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// ��Ҫ��ActionDrawerToggle��DrawerLayoutͬ��
		// ��ActionBarDrawerToggle�е�drawerͼ������ΪActionBar�е�Home-button��icon
		mDrawerToggle.syncState();

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		switch (position) {
		case 0:
			setTitle("��ҳ");
			initFragment(new Index_Fragment_Container());
			break;
		case 1:
			setTitle("��ע");
			initFragment(new Foucs_Fragment());
			break;
		case 2:
			setTitle("˽��");
			initFragment(new Private_message_Fragment());
			break;
		}
	}

	private void init() {
		fm = getSupportFragmentManager();
		initFragment(new Index_Fragment_Container());
	}

	public void initFragment(Fragment mfragment) {
		changeFragment(mfragment, true);
	}

	private void changeFragment(Fragment mfragment, boolean init) {
		FragmentTransaction ft = fm.beginTransaction().setCustomAnimations(R.anim.umeng_fb_slide_in_from_left,
				R.anim.umeng_fb_slide_out_from_left, R.anim.umeng_fb_slide_in_from_right,
				R.anim.umeng_fb_slide_out_from_right);
		;
		ft.replace(R.id.content_frame, mfragment);
		if (!init)
			ft.addToBackStack(null);
		ft.commit();
		mDrawerLayout.closeDrawers();
	}
	// private void changeFragment(Fragment mfragment, boolean init) {
	// FragmentTransaction ft = fm.beginTransaction().setCustomAnimations(
	// R.anim.umeng_fb_slide_in_from_left,
	// R.anim.umeng_fb_slide_out_from_left,
	// R.anim.umeng_fb_slide_in_from_right,
	// R.anim.umeng_fb_slide_out_from_right);
	//
	// ft.replace(R.id.content_frame, mfragment);
	// if (!init)
	// ft.addToBackStack(null);
	// ft.commitAllowingStateLoss();
	// }
}
