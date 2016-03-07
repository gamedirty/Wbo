package com.sovnem.yoyoweibo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * activity基类  控制activity的整体特征
 * 特征一:没有actionbar
 * Created by 赵军辉 on 2015/12/31.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
