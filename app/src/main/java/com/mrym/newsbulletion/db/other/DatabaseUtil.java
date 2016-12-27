//package com.mrym.newsbulletion.db.other;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//
//import com.mrym.newsbulletion.authenticator.UniqueIdentity;
//import com.mrym.newsbulletion.authenticator.account.AccountTool;
//
//import java.io.File;
//import java.lang.ref.SoftReference;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 数据库工具
// * Created by w on 2016/3/28.
// */
//public class DatabaseUtil<T> {
//    private static final String TAG = DatabaseUtil.class.getCanonicalName();
//    // 数据同步地址
//    private String syncUrl;
//    protected T mDbDate;
//    protected Context mContext;
//    protected String dbSavePath;
//    // 默认SQLite数据库名称
//    protected String defaultDbName = "default";
//    // 默认DbManager
//    private DbManager defaultDbManager;
//    // 数据缓存
//    private SoftReference cacheDate;
//    // 默认每页显示条数
//    public int defaultPageCount = 10;
//
//    public DatabaseUtil(Context context) {
//        this.mContext = context;
//        //dbSavePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "letsgo";
//        dbSavePath = context.getDir("database", Context.MODE_PRIVATE).getPath();
//        //defaultDbManager = getDefaultDbManager();
//    }
//
//    /**
//     * 检查缓存是否存在
//     **/
//    public boolean checkCacheExists() {
//        return cacheDate != null && cacheDate.get() != null;
//    }
//
//    /**
//     * 获取所有数据
//     */
//    public ArrayList<T> getAllData() {
//        return null;
//    }
//
//    /**
//     * 创建缓存
//     **/
//    public void createCache(List<T> dbDates) {
//        if (cacheDate != null) cacheDate.clear();
//        cacheDate = new SoftReference(dbDates);
//    }
//
//    /**
//     * 添加单条数据到缓存
//     *
//     * @param dbDate 要缓存的数据
//     **/
//    public void addCache(T dbDate) {
//        ArrayList<T> dbDates = getAllCache();
//        if (dbDates == null) return;
//        dbDates.add(0, dbDate);
//        cacheDate = new SoftReference(dbDates);
//    }
//
//
//    /**
//     * 获取所有缓存
//     **/
//    public ArrayList<T> getAllCache() {
//        if (!checkCacheExists()) return null;
//        return (ArrayList<T>) cacheDate.get();
//    }
//
//    /**
//     * 清理所有缓存
//     */
//    public void clearAllCache() {
//        if (cacheDate != null) cacheDate.clear();
//    }
//
//    /**
//     * 删除某条缓存
//     *
//     * @param index 索引
//     **/
//    public void deleteCacheByIndex(int index) {
//        ArrayList<T> dbDates = getAllCache();
//        if (dbDates == null) return;
//        dbDates.remove(index);
//    }
//
//
//    /**
//     * 获取默认数据库管理者
//     **/
//    protected DbManager getDefaultDbManager() {
//        //if (defaultDbManager == null) {
//        defaultDbManager = x.getDb(getDaoConfig(defaultDbName));
//
//        Log.d(TAG, defaultDbManager.getDatabase().getPath());
//        //}
//        return defaultDbManager;
//    }
//
//    /**
//     * 根据数据库名称获取DbManager
//     *
//     * @param dbName 数据库名称
//     * @return DbManager {@link DbManager}
//     */
//    public DbManager getDbManager(String dbName) {
//        return x.getDb(getDaoConfig(dbName));
//    }
//
//    /**
//     * 根据唯一标识获取DaoConfig
//     *
//     * @param identity 唯一标识
//     * @return daoConfig {@link org.xutils.DbManager.DaoConfig}
//     **/
//    protected DbManager.DaoConfig getDaoConfig(String identity) {
//        return getDaoConfig(identity, null);
//    }
//
//    /**
//     * 获取DaoConfig
//     *
//     * @param identity 唯一标识
//     * @param dbDir    数据库目录。如果为空则自动生成目录；
//     * @return daoConfig {@link org.xutils.DbManager.DaoConfig}
//     **/
//    protected DbManager.DaoConfig getDaoConfig(String identity, File dbDir) {
//        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
//                .setDbName(identity + ".db")
//                .setDbVersion(1)
//                .setAllowTransaction(false)  // 不自动开启事务，需要启动事务的时候，手动开启
//                .setDbOpenListener(new DbManager.DbOpenListener() {
//                    @Override
//                    public void onDbOpened(DbManager db) {
//                        // 开启WAL, 对写入加速提升巨大
//                        db.getDatabase().enableWriteAheadLogging();
//                    }
//                })
//                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//                    @Override
//                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
//                        // TODO: ...
//                        // db.addColumn(...);
//                        // db.dropTable(...);
//                        // ...
//                        // or
//                        // db.dropDb();
//                    }
//                });
//        if (dbDir == null) {
//            // 不设置dbDir时, 默认存储在app的私有目录.
//            daoConfig
//                    .setDbDir(getDbPath());
//        } else {
//            daoConfig.setDbDir(dbDir);
//        }
//
//        return daoConfig;
//    }
//
//
//    public File getDbPath() {
//        String userId = String.valueOf(AccountTool.getInstance().getAccountId());
//        return userId == null || userId.trim().equals("") ? new File(dbSavePath) : new File(dbSavePath + File.separator + UniqueIdentity.getInstance(mContext).getIMEI());
//    }
//
//    /**
//     * 根据表名获取自增ID
//     */
//    public Long getLastInsertRowIdByTableName(DbManager dbManager, String tableName) {
//        try {
//            Cursor cursor = dbManager.execQuery("SELECT seq FROM sqlite_sequence WHERE name='" + tableName + "' LIMIT 1");
//            if (cursor != null) {
//                try {
//                    if (cursor.moveToNext()) {
//                        return cursor.getLong(0);
//                    }
//                } catch (Throwable e) {
//                    throw new DbException(e);
//                } finally {
//                    IOUtil.closeQuietly(cursor);
//                }
//            }
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public void beginTransaction(SQLiteDatabase database) {
//        database.beginTransaction();
//    }
//
//    public void setTransactionSuccessful(SQLiteDatabase database) {
//        database.setTransactionSuccessful();
//    }
//
//    public void endTransaction(SQLiteDatabase database) {
//        database.endTransaction();
//    }
//
//    public String getSyncUrl() {
//        return syncUrl;
//    }
//
//    public void setSyncUrl(String syncUrl) {
//        this.syncUrl = syncUrl + "?customerId=";
//    }
//}
