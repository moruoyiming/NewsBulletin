package com.mrym.newsbulletion.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mrym.newsbulletion.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class PicassoUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Picasso.with(context)
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(imageView);
    }
    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.shouyetu)
                .error(R.mipmap.shouyetu)
                .config(Bitmap.Config.RGB_565)
                .into(imageView);
    }
}
