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
		// 開啟ActionBar上APP icon的功能
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setHomeButtonEnabled(true);

		// //取得ActionBar
		// actionBar = getActionBar();
		// //设置不显示标题
		// actionBar.setDisplayShowTitleEnabled(false);
		// //设置显示logo
		// actionBar.setDisplayShowHomeEnabled(true);
		// actionBar.setDisplayUseLogoEnabled(true);
		// actionBar.setLogo(R.drawable.netease_top);
		// //设置actionbar背景
		// Drawable background
		// =getResources().getDrawable(R.drawable.top_bar_background);
		// actionBar.setBackgroundDrawable(background);
		// actionBar.setDisplayHomeAsUpEnabled(true);
		// 隐藏actionBar
		// ActionBar actionBar = getActionBar();
		// actionBar.setDisplayShowHomeEnabled(false);

		// 初始化变量
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
		menuLists.add("首页");
		menuLists.add("关注");
		menuLists.add("私信");
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuLists);
		mDrawerList.setAdapter(adapter);

		// 加载首页
		init();
		userMsg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle("用户资料");
				initFragment(new User_Fragment());
			}
		});
		// 初始化设置动作
		settingTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle("设置");
				Intent intent = new Intent(Navigation_Fragment.this, SettingActivity.class);
				startActivity(intent);
				mDrawerLayout.closeDrawers();
			}
		});
		// 初始化退出动作
		exitTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ActivityManager am = (ActivityManager)getSystemService
				// (Context.ACTIVITY_SERVICE);
				// am.restartPackage(getPackageName());

				// android.os.Process.killProcess(android.os.Process.myPid());
				// // 获取PID
				// System.exit(0); // 常规java、c#的标准退出法，返回值为0代表正常退出

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
		// // 將ActionBar的图标与drawer结合起来
		// if (mDrawerToggle.onOptionsItemSelected(item)) {
		// return true;
		// }

		switch (item.getItemId()) {
		case R.id.search:
			setTitle("检索");
			Intent search_intent = new Intent(Navigation_Fragment.this, Search.class);
			startActivity(search_intent);
			mDrawerLayout.closeDrawers();
			break;
		case R.id.message:
			setTitle("私信");
			initFragment(new Private_message_Fragment());
			mDrawerLayout.closeDrawers();
			break;
		case R.id.action_settings:
			setTitle("设置");
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
		// 需要將ActionDrawerToggle与DrawerLayout同步
		// 将ActionBarDrawerToggle中的drawer图标设置为ActionBar中的Home-button的icon
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
			setTitle("首页");
			initFragment(new Index_Fragment_Container());
			break;
		case 1:
			setTitle("关注");
			initFragment(new Foucs_Fragment());
			break;
		case 2:
			setTitle("私信");
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
