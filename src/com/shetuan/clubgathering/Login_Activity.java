package com.shetuan.clubgathering;

import java.util.ArrayList;
import java.util.HashMap;

import com.shetuan.clubgathering.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Login_Activity extends Activity {

	private ListView lv;
	public Login_Activity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		lv = (ListView)findViewById(R.id.lv);
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<51;i++){
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
				setTitle("你点击了第"+position+"行");
			}
		});
	}


	
	
}
