package com.mrym.newsbulletion.mvp.fragment.follow;

import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FollowPresenter extends BasePresenter<FollowView> {

    public FollowPresenter(FollowView followView) {
        attachView(followView);
    }

    public void getPhotoGrils(String type, int page) {
        List<PhotoGirl> girls=new ArrayList<>();
        PhotoGirl photoGirl=new PhotoGirl();
        photoGirl.setCreatedAt("2016");
        photoGirl.setUrl("http://img1.imgtn.bdimg.com/it/u=715133909,3016426832&fm=21&gp=0.jpg");
        girls.add(photoGirl);
        PhotoGirl photoGirl2=new PhotoGirl();
        photoGirl2.setCreatedAt("2016");
        photoGirl2.setUrl("http://img2.imgtn.bdimg.com/it/u=578140212,1247034920&fm=21&gp=0.jpg");
        girls.add(photoGirl2);
        PhotoGirl photoGirl3=new PhotoGirl();
        photoGirl3.setCreatedAt("2016");
        photoGirl3.setUrl("http://img0.imgtn.bdimg.com/it/u=1689583646,2560929748&fm=21&gp=0.jpg");
        girls.add(photoGirl3);
        PhotoGirl photoGirl4=new PhotoGirl();
        photoGirl4.setCreatedAt("2016");
        photoGirl4.setUrl("http://img0.imgtn.bdimg.com/it/u=901927688,1103088070&fm=11&gp=0.jpg");
        girls.add(photoGirl4);
        PhotoGirl photoGirl5=new PhotoGirl();
        photoGirl5.setCreatedAt("2016");
        photoGirl5.setUrl("http://img4.imgtn.bdimg.com/it/u=816863604,4119850766&fm=21&gp=0.jpg");
        girls.add(photoGirl5);
        PhotoGirl photoGirl6=new PhotoGirl();
        photoGirl6.setCreatedAt("2016");
        photoGirl6.setUrl("http://img3.imgtn.bdimg.com/it/u=517189370,1247182831&fm=21&gp=0.jpg");
        girls.add(photoGirl6);
        PhotoGirl photoGirl7=new PhotoGirl();
        photoGirl7.setCreatedAt("2016");
        photoGirl7.setUrl("http://img5.imgtn.bdimg.com/it/u=3004669286,1094578953&fm=21&gp=0.jpg");
        girls.add(photoGirl7);



        mvpView.loadComplete();
        mvpView.loadingSuccess(girls);
    }
}
