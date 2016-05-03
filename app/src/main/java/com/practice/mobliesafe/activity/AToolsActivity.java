package com.practice.mobliesafe.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.practice.mobliesafe.R;
import com.practice.mobliesafe.utils.SmsUtils;
import com.practice.mobliesafe.utils.UIUtils;

/**
 * Created by 赖上罗小贱 on 2016/4/27.
 */
public class AToolsActivity extends Activity {
    private Button button;
    private ProgressDialog pd;
    private ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atools);
//        ViewUtils.inject(this);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
    }

    /**
     * 归属地查询
     *
     * @param view
     */
    public void numberAddressQuery(View view) {
        Intent intent = new Intent(this,NumberAddressQueryActivity.class);
        startActivity(intent);
    }
    /**
     * 备份短信
     * @param view
     */
    public void backUpsms(View view){
        //初始化一个进度条的对话框
        pd = new ProgressDialog(this);
        pd.setTitle("提示");
        pd.setMessage("稍安勿躁。正在备份。你等着吧。。");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();
        new Thread(){
            public void run() {
                System.out.print("-----------------------------");
                boolean result = SmsUtils.backUp(AToolsActivity.this,new SmsUtils.BackUpCallBackSms() {

                    @Override
                    public void onBackUpSms(int process) {
                        pd.setProgress(process);
                        progressBar1.setProgress(process);
                        System.out.print("----------#######-----------");
                    }

                    @Override
                    public void befor(int count) {
                        pd.setMax(count);
                        progressBar1.setMax(count);
                        System.out.print("---------********--------");
                    }
                });
                if(result){
                    //安全弹吐司的方法
                    UIUtils.showToast(AToolsActivity.this, "备份成功");
                }else{
                    UIUtils.showToast(AToolsActivity.this, "备份失败");
                }
                pd.dismiss();
            };
        }.start();

    }
}
