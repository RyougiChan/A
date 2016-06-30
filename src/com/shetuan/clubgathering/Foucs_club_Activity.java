package com.shetuan.clubgathering;

import java.util.ArrayList;
import java.util.HashMap;

import com.shetuan.clubgathering.R;
import com.shetuan.nav_tab.Nav_focus_activity;
import com.shetuan.nav_tab.Nav_focus_club;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Foucs_club_Activity extends Activity {
	public Foucs_club_Activity() {
		// TODO Auto-generated constructor stub
	}

	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.focus_club);
		
		lv = (ListView)findViewById(R.id.lv_focus_club);
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<50;i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.button_01);
			map.put("ItemTitle", "第"+i+"行");
			map.put("ItemText", "这是第"+i+"行");
			listItem.add(map);
		}
		
		SimpleAdapter msimpAdapter = new SimpleAdapter(this, listItem, R.layout.item, new String[]{
				"ItemImage","ItemTitle","ItemText"
		}, new int[]{
				R.id.ItemImage,R.id.ItemTitle,R.id.ItemText
		});
		lv.setAdapter(msimpAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				setTitle("你点击了第"+position+"行");
			}
		});
		
		
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar
				.newTab()
				.setText("关注的社团")
				.setTabListener(
						new MyTabListener<Nav_focus_club>(this, "关注的社团",
								Nav_focus_club.class)));
		actionBar.addTab(actionBar
				.newTab()
				.setText("关注的活动")
				.setTabListener(
						new MyTabListener<Nav_focus_activity>(this, "关注的活动",
								Nav_focus_activity.class)));
	}

	
	
	
	public class MyTabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private Activity activity;
		private Fragment fragment;
		private Class<T> clz;
		private Bundle bundle;
		private String tag;

		
		

		public MyTabListener(Activity activity, String tag, Class<T> clz) {
			// TODO Auto-generated constructor stub
			this(activity, tag, clz, null);
		}

		public MyTabListener(Activity activity, String tag, Class<T> clz,
				Bundle bundle) {
			// TODO Auto-generated constructor stub
			this.activity = activity;
			this.tag = tag;
			this.clz = clz;
			this.bundle = bundle;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if (fragment == null) {
				fragment = Fragment
						.instantiate(activity, clz.getName(), bundle);
				ft.add(android.R.id.content, fragment, tag);
			} else {
				ft.attach(fragment);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if (fragment != null) {
				ft.detach(fragment);
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			Toast.makeText(activity, "reselected", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		@SuppressWarnings("unused")
		String msg = "";
		switch (item.getItemId()) {
		case R.id.action_settings:
			msg = getString(R.string.action_settings);
			break;
		case R.id.search:
			msg = getString(R.string.search);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

}