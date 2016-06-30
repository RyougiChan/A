package com.clubelite.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AccountManage extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_manager);
		String[] manageItem = { "邮箱", "手机", "密码" };
		String[] manageDetails = { "ryougi@gmail.com", "133********", "修改密码" };
		List<Map<String, String>> manageList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < manageItem.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("itemTitle", manageItem[i]);
			map.put("itemDetails", manageDetails[i]);
			manageList.add(map);
		}
		ListAdapter listAdapter = new SimpleAdapter(this, manageList, R.layout.account_manage_item,
				new String[] { "itemTitle", "itemDetails" }, new int[] { R.id.ItemTitle, R.id.ItemDetails });
		setListAdapter(listAdapter);
	}

	@SuppressLint("InflateParams")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		View rootView = LayoutInflater.from(this).inflate(R.layout.account_manager, null);
		View emailView = LayoutInflater.from(this).inflate(R.layout.account_email, null);
		View passwordView = LayoutInflater.from(this).inflate(R.layout.account_password, null);
		TextView viewTitle = (TextView)emailView.findViewById(R.id.emailChange);
		TextView viewSubmit = (TextView)emailView.findViewById(R.id.emailSubmit);
		TextView pswdConfirm = (TextView)passwordView.findViewById(R.id.passwordConfirm);
		final PopupWindow popup = new PopupWindow(null, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT, true);
		popup.setTouchable(true);
		popup.setOutsideTouchable(true);
		popup.update();
		popup.setBackgroundDrawable(new ColorDrawable(0x7A000000));
		
		switch (position) {
		case 0:
			viewTitle.setText("修改邮箱");
			popup.setContentView(emailView);
			break;
		case 1:
			viewTitle.setText("修改手机");
			popup.setContentView(emailView);
			break;
		case 2:
			viewTitle.setText("修改密码");
			popup.setContentView(passwordView);
			break;

		default:
			break;
		}
		popup.showAtLocation(rootView, Gravity.CENTER, 0, 0);
		viewSubmit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				popup.dismiss();
			}
		});
		pswdConfirm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				popup.dismiss();
			}
		});
	}
}
