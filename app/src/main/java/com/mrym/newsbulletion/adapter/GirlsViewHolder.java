package com.mrym.newsbulletion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrym.newsbulletion.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GirlsViewHolder extends RecyclerView.ViewHolder {

    private ImageView girlsimage;

    public GirlsViewHolder(View itemView) {
        super(itemView);
        girlsimage = (ImageView) itemView.findViewById(R.id.item_grils_im);
    }

    public ImageView getGirlsimage() {
        return girlsimage;
    }

    public void setGirlsimage(ImageView girlsimage) {
        this.girlsimage = girlsimage;
    }
}
