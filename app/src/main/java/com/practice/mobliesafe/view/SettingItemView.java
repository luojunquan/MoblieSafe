package com.practice.mobliesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.*;
import com.practice.mobliesafe.R;

/**
 * Created by 赖上罗小贱 on 2016/4/25.
 */
public class SettingItemView extends RelativeLayout{
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.practice.mobliesafe";
    private TextView tvTitle;
    private TextView tvDesc;
    private CheckBox cbStatus;
    private String mTitle;
    private String mDescOn;
    private String mDescOff;
    public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTitle = attrs.getAttributeValue(NAMESPACE, "mtitle");// 根据属性名称,获取属性的值
        mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
        mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
        initView();
    }
    public SettingItemView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        // 将自定义好的布局文件设置给当前的SettingItemView
        View.inflate(getContext(), R.layout.view_setting_item, this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        cbStatus = (CheckBox) findViewById(R.id.cb_status);

        setTitle(mTitle);// 设置标题
    }

    private void setTitle(String mtitle) {
        tvTitle.setText(mtitle);
    }
    public void setDesc(String desc) {
        tvDesc.setText(desc);
    }
    /**
     * 返回勾选状态
     *
     * @return
     */
    public boolean isChecked() {
        return cbStatus.isChecked();
    }

    public void setChecked(boolean check) {
        cbStatus.setChecked(check);

        // 根据选择的状态,更新文本描述
        if (check) {
            setDesc(mDescOn);
        } else {
            setDesc(mDescOff);
        }
    }
}