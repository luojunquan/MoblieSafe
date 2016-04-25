package com.practice.mobliesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import com.practice.mobliesafe.R;
import com.practice.mobliesafe.utils.ToastUtils;
import com.practice.mobliesafe.view.SettingItemView;

/**
 * 第2个设置向导页
 * 
 * @author Kevin
 * 
 */
public class Setup2Activity extends BaseSetupActivity {
	private SettingItemView sivSim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup2);
		sivSim = (SettingItemView) findViewById(R.id.siv_sim);
		final String sim = mPref.getString("sim",null);
		if (!TextUtils.isEmpty(sim)){
			sivSim.setChecked(true);
		}else {
			sivSim.setChecked(false);
		}
		sivSim.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (sivSim.isChecked()){
					sivSim.setChecked(false);
					mPref.edit().remove("sim").commit();
				}else {
					sivSim.setChecked(true);
					TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
					String simSerialNumber = tm.getSimSerialNumber();
					mPref.edit().putString("sim",simSerialNumber).commit();
				}
			}
		});
	}

	@Override
	public void showNextPage() {
		// 如果sim卡没有绑定,就不允许进入下一个页面
		String sim = mPref.getString("sim", null);
		if (TextUtils.isEmpty(sim)) {
			ToastUtils.showToast(this, "必须绑定sim卡!");
			return;
		}

		startActivity(new Intent(this, Setup3Activity.class));
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
