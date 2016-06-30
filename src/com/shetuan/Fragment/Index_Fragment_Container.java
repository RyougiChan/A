package com.shetuan.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class Index_Fragment_Container extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index_container, container, false);
		initView(view);
		return view;
	}
	
	String intro = "我们以促进文学交流，提高同学们的文学修养为己任。探索源远流长的中华文化，在生活中提炼真正的文学，从文学中体味人生的真谛。给灵魂一双腾飞的翅膀，给思绪一方翱翔的晴空……";
	
	private void initView(View view) {
		List<Map<String, Object>> msgList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 30; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemIcon", R.drawable.logo_icon);
			map.put("ItemTitle", "南方文学社");
			map.put("ItemContent", intro);
			msgList.add(map);
		}
		setListAdapter(new SimpleAdapter(this.getActivity(), msgList, R.layout.fragment_index_item, 
				new String[]{"ItemIcon","ItemTitle","ItemContent"}, new int[]{R.id.index_list_item_title_avatar, 
						R.id.index_list_item_title_text, R.id.index_list_item_content}));
	}

}
