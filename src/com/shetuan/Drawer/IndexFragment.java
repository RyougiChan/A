package com.shetuan.Drawer;

import com.shetuan.clubgathering.R;

import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class IndexFragment extends Fragment {

	private TextView textView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index, container, false);
		initView(view);
		return view;
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initView(View view) {
		WebView mWebView = (WebView) view.findViewById(R.id.webView1);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("file:///android_asset/index/index.html");
	}

}
