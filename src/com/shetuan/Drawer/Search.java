package com.shetuan.Drawer;

import com.shetuan.clubgathering.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class Search extends Activity implements OnQueryTextListener {
	private SearchView searchView;
	private ListView autoFitList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		searchView = (SearchView) findViewById(R.id.searchView);
		searchView.setIconifiedByDefault(false);
		searchView.setOnQueryTextListener(this);
		
		autoFitList = (ListView) findViewById(R.id.autoFitList);
		autoFitList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1));
		autoFitList.setTextFilterEnabled(true);
		searchView.setSubmitButtonEnabled(true);
		searchView.setQueryHint("在此键入关键字/词");
	}

	public boolean onQueryTextSubmit(String query) {
		
		return false;
	}

	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			autoFitList.clearTextFilter();
		}else {
			autoFitList.setFilterText(newText);
		}
		return true;
	}
}
