//package com.mrym.newsbulletion.adapter;
//
//import android.content.Intent;
//import android.support.v4.view.PagerAdapter;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.mrym.newsbulletion.ui.activity.GuideActivity;
//import com.mrym.newsbulletion.ui.activity.HomeActivity;
//
//import java.util.ArrayList;
///**
// * Created by Jian on 2016/8/26.
// * Email: 798774875@qq.com
// * Github: https://github.com/moruoyiming
// */
//public class GuidePagerAdapter extends PagerAdapter {
//
//    private final Button mBtn;
//    private final LinearLayout mLlPoints;
//    private GuideActivity mActivity;
//    private ArrayList<ImageView> imageViews;
//    private int[] guideIds;
//
//    public GuidePagerAdapter(GuideActivity activity, ArrayList<ImageView> imageViews, int[] guideIds, Button mBtnStart, LinearLayout mLlPoints) {
//        this.mActivity = activity;
//        this.imageViews = imageViews;
//        this.guideIds = guideIds;
//        this.mBtn = mBtnStart;
//        this.mLlPoints = mLlPoints;
//    }
//
//    @Override
//    public int getCount() {
//        return imageViews.size();
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView = imageViews.get(position);
//        container.addView(imageView);
//        if (position == guideIds.length - 1) {
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(mActivity, HomeActivity.class);
////                    mActivity.startActivity(intent);
////                    mActivity.finish();
//                }
//            });
//        }
//        return imageView;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
////    @Override
////    public void onClick(View v) {
////        if (v.getId() == R.id.bt_guide_start) {
////
////        }
////    }
//}
