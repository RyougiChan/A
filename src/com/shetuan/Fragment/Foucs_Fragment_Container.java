package com.shetuan.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shetuan.clubgathering.R;
import com.shetuan.clubgathering.R.string;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Foucs_Fragment_Container extends Fragment {

	private int position;
	private static final String ARG_POSITION = "position";
	private FragmentManager fm = getFragmentManager();
//	private static int[] webviewURL = { R.string.foucs_club, R.string.foucs_activity };
	String[] webviewURL = new String[] {"file:///android_asset/foucs/foucs_club.html", "file:///android_asset/foucs/foucs_activity.html" };
	// private static final int[] drawables = { R.drawable.f, R.drawable.s,
	// R.drawable.t, R.drawable.fo,
	// R.drawable.fi, R.drawable.fi, R.drawable.fi, R.drawable.fi };
	
	public static Foucs_Fragment_Container newInstance(int position) {
		Foucs_Fragment_Container ffc = new Foucs_Fragment_Container();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		ffc.setArguments(b);
		return ffc;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		position = getArguments().getInt(ARG_POSITION);
		View view = inflater.inflate(R.layout.fragment_focus, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ListView foucs_lv = (ListView) view.findViewById(R.id.foucs_lv);
//		foucs_wv.loadUrl(webviewURL[position]);
		List<Map<String, Object>> focusList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemIcon", R.drawable.logo_icon);
			map.put("ItemName", "爱心助学协会");
			map.put("ItemSign", "中山大学爱心助学协会成立于2002年5月，是由中山大学学生自发组织的跨四校区公益性社团。在中山大学团委的领导和帮助下，协会始终以“让贫瘠的土地上再少一张愁苦的脸儿”为不断追求的目标……");
			focusList.add(map);
		}
		foucs_lv.setAdapter(new SimpleAdapter(this.getActivity(), focusList, R.layout.fragment_index_item, new String[]{"ItemIcon","ItemName","ItemSign"}, new int[]{R.id.index_list_item_title_avatar, R.id.index_list_item_title_text, R.id.index_list_item_content}));
	}

	public void initFragment(Fragment mfragment) {
		changeFragment(mfragment, true);
	}

	private void changeFragment(Fragment mfragment, boolean init) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.foucs_frame, mfragment);
		if (!init)
			ft.addToBackStack(null);
		ft.commit();
	}
}
