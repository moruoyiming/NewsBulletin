package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsDetail;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.details.DetailsPresenter;
import com.mrym.newsbulletion.mvp.activity.details.DetailsView;
import com.mrym.newsbulletion.utils.common.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsDetailActivity extends MvpActivity<DetailsPresenter> implements DetailsView {

    public static final String TAG = NewsDetailActivity.class.getCanonicalName();
    @BindView(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;
    @BindView(R.id.mask_view)
    View maskView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.news_detail_from_tv)
    TextView newsDetailFromTv;
    @BindView(R.id.news_detail_body_tv)
    TextView newsDetailBodyTv;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String postId;
    private String mNewsTitle;
    private String mShareLink;


    @Override
    public void initView() {
        SetTranslanteBar();
        postId = getIntent().getStringExtra(GlobalVariable.NEWS_POST_ID);
        mvpPresenter.getOneNewsDataRequest(postId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        toolbar.inflateMenu(R.menu.news_detail);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_web_view:
                        Intent intent = new Intent(NewsDetailActivity.this, NewsBrowserActivity.class);
                        intent.putExtra(GlobalVariable.NEWS_LINK, mShareLink);
                        intent.putExtra(GlobalVariable.NEWS_TITLE, mNewsTitle);
                        startActivity(intent);
                        break;
                    case R.id.action_browser:
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        if (canBrowse(intent2)) {
                            Uri uri = Uri.parse(mShareLink);
                            intent2.setData(uri);
                            startActivity(intent2);
                        }
                        break;
                }
                return true;
            }
        });
        //分享
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareLink == null) {
                    mShareLink = "";
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_contents, mNewsTitle, mShareLink));
                startActivity(Intent.createChooser(intent, getTitle()));
//                showShare();
            }
        });
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mNewsTitle);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mShareLink);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mNewsTitle);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mShareLink);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(mNewsTitle);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mShareLink);
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter = null;
        mShareLink = null;
        maskView = null;
        mActivity = null;
        mNewsTitle = null;
        toolbar = null;
//        RefWatcher refWatcher = NewsApplication.getRefWatcher(NewsDetailActivity.this);
//        refWatcher.watch(this);
    }

    @Override
    public void returnOneNewsData(NewsDetail newsDetail) {
        mShareLink = newsDetail.getShareLink();
        mNewsTitle = newsDetail.getTitle();
        String newsSource = newsDetail.getSource();
        String newsTime = DateUtils.formatDate(newsDetail.getPtime());
        String newsBody = newsDetail.getBody();
        String NewsImgSrc = getImgSrcs(newsDetail);

        setToolBarLayout(mNewsTitle);
        //mNewsDetailTitleTv.setText(newsTitle);
        newsDetailFromTv.setText(NewsApplication.getContext().getString(R.string.news_from, newsSource, newsTime));
        setNewsDetailPhotoIv(NewsImgSrc);
        setNewsDetailBodyTv(newsDetail, newsBody);
    }

    private void setToolBarLayout(String newsTitle) {
        toolbarLayout.setTitle(newsTitle);
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private void setNewsDetailPhotoIv(String imgSrc) {
        Picasso.with(NewsDetailActivity.this)
                .load(imgSrc)
                .placeholder(R.mipmap.shouyetu)
                .error(R.mipmap.shouyetu)
                .config(Bitmap.Config.RGB_565)
                .into(newsDetailPhotoIv);
    }


    private void setNewsDetailBodyTv(final NewsDetail newsDetail, final String newsBody) {
        progressBar.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
        setBody(newsDetail, newsBody);
    }

    private void setBody(NewsDetail newsDetail, String newsBody) {
        int imgTotal = newsDetail.getImg().size();
        if (isShowBody(newsBody, imgTotal)) {
//              mNewsDetailBodyTv.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效,实测经常卡机崩溃
//            mUrlImageGetter = new URLImageGetter(newsDetailBodyTv, newsBody, imgTotal);
            newsDetailBodyTv.setText(Html.fromHtml(newsBody));
        } else {
            newsDetailBodyTv.setText(Html.fromHtml(newsBody));
        }
    }

    private boolean isShowBody(String newsBody, int imgTotal) {
        return imgTotal >= 2 && newsBody != null;
    }

    private String getImgSrcs(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs = newsDetail.getImg();
        String imgSrc;
        if (imgSrcs != null && imgSrcs.size() > 0) {
            imgSrc = imgSrcs.get(0).getSrc();
        } else {
            imgSrc = getIntent().getStringExtra(GlobalVariable.NEWS_IMG_RES);
        }
        return imgSrc;
    }

    private boolean canBrowse(Intent intent) {
        return intent.resolveActivity(getPackageManager()) != null && mShareLink != null;
    }


    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_news_detail;
    }
}
