package com.sovnem.yoyoweibo.db;

import com.sovnem.yoyoweibo.bean.Status;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by sovnem on 16/3/7.
 */
public class StatusDB implements BaseDB<Status> {

    private DbManager manager;
    private static StatusDB db;

    private StatusDB() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(getDBName())
                // 不设置dbDir时, 默认存储在app的私有目录.
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    }
                });
        manager = x.getDb(daoConfig);
    }

    public synchronized static StatusDB getInstance() {
        if (db == null) {
            db = new StatusDB();
        }
        return db;
    }

    @Override
    public String getDBName() {
        return getClass().getSimpleName() + ".db";
    }

    @Override
    public void insert(Status status) {
        try {
            manager.saveBindingId(status);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Status getBy(String by) {
        return null;
    }

}
