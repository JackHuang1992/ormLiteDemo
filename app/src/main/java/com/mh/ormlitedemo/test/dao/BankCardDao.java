package com.mh.ormlitedemo.test.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.mh.ormlitedemo.test.db.DBHelper;
import com.mh.ormlitedemo.test.model.BankCard;

import java.sql.SQLException;

/**
 * Created by JackHuang on 2016/9/2.
 */

public class BankCardDao {

    private Context context;
    private DBHelper dbHelper;
    private Dao<BankCard,Integer>  bankCardDaoOpe;

    public BankCardDao(Context context)
    {
        this.context = context;
        try {
            dbHelper = DBHelper.getHelper(context);
            bankCardDaoOpe = dbHelper.getDao(BankCard.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void add(BankCard bankCard) {
        try
        {
            bankCardDaoOpe.create(bankCard);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try
        {
            bankCardDaoOpe.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(BankCard bankCard)
    {
        try
        {
            bankCardDaoOpe.update(bankCard);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void queryAll()
    {
        try
        {
            bankCardDaoOpe.queryForAll();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void queryForId(int id)
    {
        try
        {
            bankCardDaoOpe.queryForId(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
