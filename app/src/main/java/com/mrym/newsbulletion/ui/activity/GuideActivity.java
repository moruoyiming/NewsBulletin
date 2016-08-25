package com.mrym.newsbulletion.ui.activity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;



import java.util.ArrayList;



/**
 * author : nan
 * time : 2016/8/9.
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.guide_vp_pager)
    ViewPager mPager;
    @BindView(R.id.guide_ll_points)
    LinearLayout mLlPoints;
    @BindView(R.id.guide_v_point_red)
    View mPointRed;
    @BindView(R.id.bt_guide_start)
    Button mBtnStart;
    int[] guideIds;
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        initAdapter();
    }

    private void initData() {
        guideIds = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3, R.mipmap.guide_4};
        int dip2px = UIUtils.dip2px(6);
        imageViews = new ArrayList<>();
        for (int i = 0; i < guideIds.length; i++) {
            if (guideIds.length > 1) {
                View view = new View(getApplicationContext());
                view.setBackgroundResource(R.drawable.point_nomal);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px, dip2px);
                if (i != 0) {
                    params.leftMargin = dip2px;
                }
                view.setLayoutParams(params);
                mLlPoints.addView(view);
            } else {
                mPointRed.setVisibility(View.GONE);
            }
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setBackgroundResource(guideIds[i]);
            imageViews.add(imageView);
        }
    }

    private void initAdapter() {
        GuidePagerAdapter adapter = new GuidePagerAdapter(this, imageViews, guideIds, mBtnStart, mLlPoints);
        mPager.setAdapter(adapter);
        mPager.setOnPageChangeListener(new GuidePagerChangeListener(this, mPointRed));
    }
}
