package com.sovnem.yoyoweibo.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.base.BaseActivity;
import com.sovnem.yoyoweibo.view.fragment.FindFragment;
import com.sovnem.yoyoweibo.view.fragment.FirstFragment;
import com.sovnem.yoyoweibo.view.fragment.MessageFragment;
import com.sovnem.yoyoweibo.view.fragment.MineFragment;

import java.util.ArrayList;

/**
 * 主页  四个tab
 */
public class MainActivity extends BaseActivity {
    private ViewPager vpContent;//内容部分viewpager
    private ArrayList<Fragment> fragments;//view怕个人包含的所有fragment
    private RadioGroup rg;//底部Tab
    private int[] rbIds = new int[]{R.id.rbtn_main_home, R.id.rbtn_main_message, R.id.rbtn_main_explor, R.id.rbtn_main_mine};//所有的radiobutton的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vpContent = (ViewPager) findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(FirstFragment.getInstance());//首页
        fragments.add(MessageFragment.getInstance());//消息
        fragments.add(FindFragment.getInstance());//发现
        fragments.add(MineFragment.getInstance());//我的

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        vpContent.setAdapter(adapter);
        SelectListener s = new SelectListener();
        vpContent.addOnPageChangeListener(s);
        vpContent.setOffscreenPageLimit(1);
        rg = (RadioGroup) findViewById(R.id.rgrp_main_tabs);
        rg.setOnCheckedChangeListener(s);
    }

    /**
     * viewpager的监听器  radiogroup的监听器
     */
    class SelectListener implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            rg.check(rbIds[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            vpContent.setCurrentItem(getIndexOfCheckedId(checkedId), false);
        }
    }

    /**
     * 获取当前选中的id所在的页数
     *
     * @param checkedId
     * @return
     */
    private int getIndexOfCheckedId(int checkedId) {
        for (int i = 0; i < rbIds.length; i++) {
            if (rbIds[i] == checkedId) {
                return i;
            }
        }
        return 0;
    }


}
