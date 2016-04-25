package com.practice.mobliesafe.activity;

import android.content.Intent;
import android.os.Bundle;

import com.practice.mobliesafe.R;

/**
 * 第3个设置向导页
 * 
 * @author Kevin
 * 
 */
public class Setup3Activity extends BaseSetupActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup3);
	}

	@Override
	public void showNextPage() {
		startActivity(new Intent(this, Setup4Activity.class));
		finish();

		// 两个界面切换的动画
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);// 进入动画和退出动画
	}

	@Override
	public void showPreviousPage() {
		startActivity(new Intent(this, Setup1Activity.class));
		finish();

		// 两个界面切换的动画
		overridePendingTransition(R.anim.tran_previous_in,
				R.anim.tran_previous_out);// 进入动画和退出动画
	}
}
