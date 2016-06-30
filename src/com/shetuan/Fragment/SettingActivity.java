package com.shetuan.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clubelite.setting.AccountManage;
import com.clubelite.setting.FeedBackAdvise;
import com.clubelite.setting.MessageNotice;
import com.clubelite.setting.UserProfile;
import com.shetuan.clubgathering.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SettingActivity extends ListActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		// Initialize setting list
		String[] sysListString = new String[] { "用户资料", "账号与密码", "消息通知", "建议反馈" };
		List<Map<String, Object>> sysListItem = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < sysListString.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.ic_chevron_right_grey600_48dp);
			map.put("ItemTitle", sysListString[i]);
			sysListItem.add(map);
		}
		setListAdapter(new SimpleAdapter(this, sysListItem, R.layout.setting_line,
				new String[] { "ItemTitle", "ItemImage" }, new int[] { R.id.ItemTitle, R.id.ItemImage }));
		
		ListView checkList = (ListView) findViewById(R.id.clear_check);
		ArrayList<String> ccList = new ArrayList<String>();
		ccList.add("清除缓存");
		ccList.add("检查更新");
		checkList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ccList));
		
		// Binding click action
		checkList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					
					break;
				case 1:
					
					break;
				default:
					break;
				}
			}
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			setTitle("用户资料");
			Intent userIntent = new Intent(SettingActivity.this, UserProfile.class);
			startActivity(userIntent);
			break;
		case 1:
			setTitle("账号密码");
			Intent passwordIntent = new Intent(SettingActivity.this, AccountManage.class);
			startActivity(passwordIntent);
			break;
		case 2:
			setTitle("消息通知");
			Intent newIntent = new Intent(SettingActivity.this, MessageNotice.class);
			startActivity(newIntent);
			break;
		case 3:
			setTitle("建议反馈");
			Intent feedbackIntent = new Intent(SettingActivity.this, FeedBackAdvise.class);
			startActivity(feedbackIntent);
			break;
		default:
			break;
		}
	}
}

