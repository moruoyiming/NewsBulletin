package com.mrym.newsbulletion.db;


import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.db.gen.DaoMaster;
import com.mrym.newsbulletion.db.gen.DaoSession;

/**
 * Created by wyk on 2016/7/12. 
 */  
public class GreenDaoManager {  
    private static GreenDaoManager mInstance;  
    private DaoMaster mDaoMaster;  
    private DaoSession mDaoSession;
  
  
    public GreenDaoManager() {  
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(NewsApplication.getContext(), "notes-db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());  
        mDaoSession = mDaoMaster.newSession();  
    }  
  
    public static GreenDaoManager getInstance() {  
        if (mInstance == null) {  
            mInstance = new GreenDaoManager();  
        }  
        return mInstance;  
    }  
  
    public DaoMaster getMaster() {  
        return mDaoMaster;  
    }  
  
    public DaoSession getSession() {  
        return mDaoSession;  
    }  
  
    public DaoSession getNewSession() {  
        mDaoSession = mDaoMaster.newSession();  
        return mDaoSession;  
    }  
}  