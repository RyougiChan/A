package com.clubelite.setting;

import com.shetuan.clubgathering.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FeedBackAdvise extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		intialView();
	}

	public void intialView() {
		ImageView imageView = (ImageView) findViewById(R.id.addProblemImage);
		imageView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(FeedBackAdvise.this, "Click this button will load a image", 100).show();
			}
		});
	}
}
