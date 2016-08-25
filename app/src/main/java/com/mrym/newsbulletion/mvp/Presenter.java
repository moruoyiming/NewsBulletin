package com.mrym.newsbulletion.mvp;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
