package com.shetuan.clubgathering;

import com.shetuan.Drawer.Navigation_Fragment;
import com.shetuan.clubgathering.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends Activity {
	
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Register.this,
						Navigation_Fragment.class);
				startActivity(intent);
			}
		});

	}
	

}
