package com.mh.ormlitedemo.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mh.ormlitedemo.test.model.BankCard;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JackHuang on 2016/8/31.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "mh.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

//    private Dao<BankCard, Integer> bankCardDao;

    private DBHelper(Context context)
    {
        super(context, DB_NAME, null, 2);
    }

    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BankCard.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, BankCard.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DBHelper instance;
    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DBHelper getHelper(Context context)
    {
        if (instance == null)
        {
            synchronized (DBHelper.class)
            {
                if (instance == null)
                    instance = new DBHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

  /*  *//**
     * 释放资源
     *//*
    @Override
    public void close()
    {
        super.close();
        bankCardDao = null;
    }*/
    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
