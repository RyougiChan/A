package com.shetuan.clubgathering;

import com.shetuan.clubgathering.R;
import com.shetuan.Drawer.Navigation_Fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						Navigation_Fragment.class);
				startActivity(intent);
			}
		});

		button = (Button) this.findViewById(R.id.button2);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						Register.class);
				startActivity(intent);
			}
		});
		
		TextView textview = (TextView) this.findViewById(R.id.iforget);
		textview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						Forgotpassword.class);
				startActivity(intent);
			}
		});

	}
	
}
