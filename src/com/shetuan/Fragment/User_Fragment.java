package com.shetuan.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class User_Fragment extends ListFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		String[] userTitle = new String[] { "昵称", "性别", "地区", "个性签名" };
		String[] userDetail = new String[] {"两仪式", "女", "日本", "韶华白首 不过转瞬"};
		List<Map<String, Object>> userListItem = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < userTitle.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemDetails", userDetail[i]);
			map.put("ItemTitle", userTitle[i]);
			userListItem.add(map);
		}
		setListAdapter(new SimpleAdapter(this.getActivity(), userListItem, R.layout.user_line, new String[] { "ItemTitle", "ItemDetails" }, new int[] { R.id.ItemTitle, R.id.ItemDetails }));
	}

}
