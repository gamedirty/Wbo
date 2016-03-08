package com.sovnem.test.orm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sovnem.test.User;

import java.lang.reflect.Field;

/**
 * Created by 赵军辉 on 2016/3/8.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "vbo.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(createTable(User.class));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建表 obj 实体对象 实体中_id就是数据库的_id
     */
    private <T> String createTable(Class<T> c) {
        return createTable(c, c.getSimpleName());
    }

    /**
     * 建表sql语句
     *
     * @param c
     * @param tableName
     * @param <T>
     * @return
     */
    private <T> String createTable(Class<T> c, String tableName) {
        StringBuffer sBuffer = new StringBuffer();

        sBuffer.append("create table if not exists " + tableName + " "
                + "(_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");

        Field[] fields = c.getFields();
        // Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("_id") && !"CREATOR".equals(field.getName())
                    && !"CONTENTS_FILE_DESCRIPTOR".equals(field.getName())
                    && !"PARCELABLE_WRITE_RETURN_VALUE".equals(field.getName()))
                // field.setAccessible(true);
                sBuffer.append(field.getName() + " TEXT,");
        }
        sBuffer.deleteCharAt(sBuffer.length() - 1);
        sBuffer.append(");");
        return sBuffer.toString();
    }

    /**
     * 给特定的表添加列
     *
     * @param db
     * @param tablename
     * @param columnName
     */
    private void addTableColum(SQLiteDatabase db, String tablename, String columnName) {
        if (!isColumnExist(db, tablename, columnName)) {
            try {
                db.execSQL("alter table " + tablename + " add " + columnName + " varchar");
            } catch (Exception e) {
            }
        }
    }

    /**
     * tableName表中是不是存columnName字段
     *
     * @param db
     * @param tableName
     * @param columnName
     * @return
     */
    public boolean isColumnExist(SQLiteDatabase db, String tableName, String columnName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }

        try {
            Cursor cursor = null;
            String sql = "select count(1) as c from sqlite_master where type ='table' and name ='"
                    + tableName.trim() + "' and sql like '%" + columnName.trim() + "%'";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

            cursor.close();
        } catch (Exception e) {
        }
        return result;
    }
}
