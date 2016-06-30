package com.clubelite.setting;

import com.shetuan.clubgathering.R;

import android.app.Activity;
import android.os.Bundle;

public class EmailEdit extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_email);
		this.getActionBar().hide();
	}
}
