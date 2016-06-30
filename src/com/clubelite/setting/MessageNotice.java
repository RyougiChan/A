package com.clubelite.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class MessageNotice extends ListActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_notice);
		initialView();
	}

	public void initialView() {
		String[] newNotice = { "新活动提醒", "新私信提醒", "新好友提醒" };
		List<Map<String, Object>> noticeList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < newNotice.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msgNoticeText", newNotice[i]);
			map.put("msgNoticeCheckBox", null);
			noticeList.add(map);
		}
		setListAdapter(new SimpleAdapter(this, noticeList, R.layout.message_notice_item,
				new String[] { "msgNoticeText", "msgNoticeCheckBox" },
				new int[] { R.id.msgNoticeText, R.id.msgNoticeCheckBox }));
	}
}
