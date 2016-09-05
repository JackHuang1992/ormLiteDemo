package com.mh.ormlitedemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public abstract class BaseActivity extends FragmentActivity {
	TextView titleTV;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		((BaseApplication) getApplication()).pushActivity(this);
		initView();
		initListener();
		initData();
	}
	
	public abstract void initView();
	public abstract void initListener();
	public abstract void initData();
	
	
	public void initTitle(boolean canBack,String title){
		titleTV = (TextView) findViewById(R.id.tv_title);
		titleTV.setText(title);
		ImageButton backBtn = (ImageButton) findViewById(R.id.btn_back);
		backBtn.setVisibility(canBack?View.VISIBLE:View.GONE);
		backBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
//				overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
			}
		});
	}

	public void setXTitle(String title) {
		if (null != titleTV) {
			titleTV.setText(title);
		}
	}
}
