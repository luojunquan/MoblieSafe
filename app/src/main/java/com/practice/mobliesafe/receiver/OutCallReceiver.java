package com.practice.mobliesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.practice.mobliesafe.db.dao.AddressDao;

/**
 * Created by 赖上罗小贱 on 2016/4/28.
 */
public class OutCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String number = getResultData();
        String address = AddressDao.getAddress(number);
        Toast.makeText(context,address,Toast.LENGTH_LONG).show();
    }
}
