package com.mrym.newsbulletion.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.facebook.drawee.view.SimpleDraweeView;
import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.utils.common.AppUtils;
import com.mrym.newsbulletion.utils.common.UIUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shawn on 2016/5/17.
 */
public class GuideActivity extends Activity {
    private static final String TAG = GuideActivity.class.getCanonicalName();
    @Bind(R.id.guide_vp_pager)
    ViewPager mPager;
    @Bind(R.id.guide_ll_points)
    LinearLayout llPoints;
    @Bind(R.id.guide_v_point_red)
    View pointRed;
    ArrayList<SimpleDraweeView> imageViews;
    int[] guideIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        NewsApplication.addActivity(this, TAG);

        ButterKnife.bind(this);
        initData();
        mPager.setAdapter(new GuidePagerAdapter());
        mPager.setOnPageChangeListener(new GuidePagerChangeListener());
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
                llPoints.addView(view);
            } else {
                pointRed.setVisibility(View.GONE);
            }
            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(getApplicationContext());
            simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            simpleDraweeView.setImageURI(Uri.parse("res://com.mrym.newsbulletion/"+guideIds[i]));
            imageViews.add(simpleDraweeView);
        }
    }

    @OnClick(R.id.bt_guide_start)
    public void onClick() {
//        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(GlobalVariable.VERSION_KEY_ENTER_GUIDE, AppUtils.getVersionCode()).apply();
//        startActivity(new Intent(this, .class));
//        finish();
    }

    private class GuidePagerChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * ViewPager滑动时回调
         *
         * @param position
         * @param positionOffset
         * @param positionOffsetPixels
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int redMoveX = (int) ((position + positionOffset) * UIUtils.dip2px(12));
            android.widget.RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) pointRed.getLayoutParams();
            layoutParams.leftMargin = redMoveX;
            pointRed.setLayoutParams(layoutParams);
        }

        @Override
        public void onPageSelected(int position) {
//            if (position == guideIds.length - 1) {
//                btStart.setVisibility(View.VISIBLE);
//            } else {
//                btStart.setVisibility(View.INVISIBLE);
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    private class GuidePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView child = imageViews.get(position);
            container.addView(child);
            if (position == guideIds.length - 1) {
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        PreferenceManager.getDefaultSharedPreferences(GuideActivity.this).edit().putInt(GlobalVariable.VERSION_KEY_ENTER_GUIDE, AppUtils.getVersionCode()).apply();
//                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
//                        finish();
                    }
                });
            }
            return child;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageViews.clear();
        imageViews=null;
        NewsApplication.removeActivity(TAG);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }
}
