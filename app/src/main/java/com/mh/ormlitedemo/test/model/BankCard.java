package com.mh.ormlitedemo.test.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by JackHuang on 2016/9/2.
 */
@DatabaseTable(tableName = "BANK_CARD")
public class BankCard {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "BankName")
    private String BankName;
    @DatabaseField(columnName = "CardType")
    private String CardType;
    @DatabaseField(columnName = "CardNumber")
    private String CardNumber;
    @DatabaseField(columnName = "HolderName")
    private String HolderName;
    @DatabaseField(columnName = "Uid")
    private String Uid;

    public BankCard() {
    }

    public BankCard(int id, String bankName, String cardType, String cardNumber, String holderName, String uid) {
        this.id = id;
        BankName = bankName;
        CardType = cardType;
        CardNumber = cardNumber;
        HolderName = holderName;
        Uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getHolderName() {
        return HolderName;
    }

    public void setHolderName(String holderName) {
        HolderName = holderName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
