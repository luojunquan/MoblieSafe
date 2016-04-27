package com.practice.mobliesafe.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 赖上罗小贱 on 2016/4/27.
 */
public class AddressService extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String number = getResultData();// 获取去电电话号码
    }
}
