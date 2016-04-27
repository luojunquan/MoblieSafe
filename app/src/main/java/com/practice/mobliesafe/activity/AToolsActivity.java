package com.practice.mobliesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.practice.mobliesafe.R;

/**
 * Created by 赖上罗小贱 on 2016/4/27.
 */
public class AToolsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atools);
    }

    /**
     * 归属地查询
     *
     * @param view
     */
    public void numberAddressQuery(View view) {
        startActivity(new Intent(this, AddressActivity.class));
    }

}
