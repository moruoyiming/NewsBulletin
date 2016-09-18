package com.mrym.newsbulletion.mvp.fragment.category;

import android.text.TextUtils;
import android.util.Log;

import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.fragment.mine.MineView;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryPresenter extends BasePresenter<GateGoryView> {

    public GateGoryPresenter(GateGoryView getGoryView) {
        attachView(getGoryView);
    }
    public void getGategoryData(String gateGory,int pageIndex)  {
        Log.i("pageIndex","当前页数："+pageIndex);
        List<NewsEntity> newsList=new ArrayList<NewsEntity>();
        for(int i =0 ; i < 10 ; i++){
            NewsEntity news = new NewsEntity();
            news.setId(i);
            news.setNewsId(i);
            news.setCollectStatus(false);
            news.setCommentNum(i + 10);
            news.setInterestedStatus(true);
            news.setLikeStatus(true);
            news.setReadStatus(false);
            news.setNewsCategory("推荐");
            news.setNewsCategoryId(1);
            news.setAuthor("腾讯网");
            news.setHeadimg("http://p0.qhimg.com/t0136dd69d77510bbcb.jpg?size=780x520");
            news.setTitle("可以用谷歌眼镜做的10件酷事：导航、玩游戏");
            List<String> url_list = new ArrayList<String>();
            if(i%2 == 1){
                String url1 = "http://img1.imgtn.bdimg.com/it/u=1205280251,2634350720&fm=21&gp=0.jpg";
                String url2 = "http://p0.qhimg.com/t019c6684eeb0484ea1.jpg?size=1500x1000";
                String url3 = "http://p0.qhimg.com/t0107a32d125af97c90.jpg?size=1500x1000";
                news.setPicOne(url1);
                news.setPicTwo(url2);
                news.setPicThr(url3);
                url_list.add(url1);
                url_list.add(url2);
                url_list.add(url3);
                news.setNewType(3);
            }else{
                news.setTitle("AA用车:智能短租租车平台");
                String url = "http://p0.qhimg.com/t01979caeefeb88b526.jpg?size=1500x1000";
                news.setPicOne(url);
                url_list.add(url);
                news.setNewType(2);
            }
            news.setPicList(url_list);
            news.setPublishTime(Long.valueOf(i));
            news.setReadStatus(false);
            news.setSource("手机腾讯网");
            news.setSummary("腾讯数码讯（编译：Gin）谷歌眼镜可能是目前最酷的可穿戴数码设备，你可以戴着它去任何地方（只要法律法规允许或是没有引起众怒），作为手机的第二块“增强现实显示屏”来使用。另外，虽然它仍未正式销售，但谷歌近日在美国市场举行了仅限一天的开放购买活动，价格则为1500美元（约合人民币9330元），虽然仍十分昂贵，但至少可以满足一些尝鲜者的需求，也预示着谷歌眼镜的公开大规模销售离我们越来越近了。");
            news.setMark(i);
            if(i == 4){
                news.setTitle("部落战争强势回归");
                news.setLocal("推广");
                news.setLarge(true);
                String url = "http://p0.qhimg.com/t0162d7001d832987be.jpg?size=1500x1000";
                news.setPicOne(url);
                url_list.clear();
                url_list.add(url);
                news.setNewType(1);
            }else{
                news.setLarge(false);
            }
            newsList.add(news);
        }
        mvpView.loadComplete();
        mvpView.loadingSuccess(newsList);
//        addSubscription(apiStores.getCategoryNews(gateGory), new SubscriberCallBack<>(new ApiCallback<GateGoryModel>() {
//            @Override
//            public void onSuccess(GateGoryModel model) {
////                if (TextUtils.equals(model.getState(), GlobalVariable.SUCCESS)) {
////                    mvpView.loadingSuccess(model.getNews());
////                } else {
////                    mvpView.loadingError("加载失败！");
////                }
//                Log.i("GateGoryPresenter",model.toString());
//
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
////                mvpView.loadingError(msg);
//            }
//
//            @Override
//            public void onCompleted() {
//            }
//        }));
    }

}
