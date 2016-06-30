package com.clubelite.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class UserProfile extends ListActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_user);
		initView();
	}

	private void initView() {
		String[] userTitle = new String[] { "昵称", "性别", "地区", "个性签名" };
		String[] userDetail = new String[] { "两仪式", "女", "日本", "就算是神我也杀给你看" };
		List<Map<String, Object>> userListItem = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < userTitle.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemDetails", userDetail[i]);
			map.put("ItemTitle", userTitle[i]);
			userListItem.add(map);
		}
		setListAdapter(new SimpleAdapter(UserProfile.this, userListItem, R.layout.user_line,
				new String[] { "ItemTitle", "ItemDetails" }, new int[] { R.id.ItemTitle, R.id.ItemDetails }));
	}
}
