package com.shetuan.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class Private_message_Container extends ListFragment {

	private int position;
	private static final String ARG_POSITION = "position";
	private FragmentManager fm = getFragmentManager();
//	private static int[] webviewURL = { R.string.foucs_club, R.string.foucs_activity };
	// private static final int[] drawables = { R.drawable.f, R.drawable.s,
	// R.drawable.t, R.drawable.fo,
	// R.drawable.fi, R.drawable.fi, R.drawable.fi, R.drawable.fi };
	
	public static Private_message_Container newInstance(int position) {
		Private_message_Container pmc = new Private_message_Container();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		pmc.setArguments(b);
		return pmc;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		position = getArguments().getInt(ARG_POSITION);
		View view = inflater.inflate(R.layout.fragment_private_message, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		List<Map<String, Object>> msgList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemIcon", R.drawable.logo_icon);
			map.put("ItemName", "两仪式");
			map.put("ItemSign", "韶华白首 不过转瞬");
			msgList.add(map);
		}
		setListAdapter(new SimpleAdapter(this.getActivity(), msgList, R.layout.private_message_item, new String[]{"ItemIcon","ItemName","ItemSign"}, new int[]{R.id.itemIcon, R.id.itemName, R.id.itemSign}));
	}


	private String StringConverter(int i) {
		String s = Integer.toString(i);
		return s;
	}

	public void initFragment(Fragment mfragment) {
		changeFragment(mfragment, true);
	}

	private void changeFragment(Fragment mfragment, boolean init) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.primsg_frame, mfragment);
		if (!init)
			ft.addToBackStack(null);
		ft.commit();
	}
}
