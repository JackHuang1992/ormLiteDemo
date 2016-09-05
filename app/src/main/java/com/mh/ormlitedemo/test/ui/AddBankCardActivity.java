package com.mh.ormlitedemo.test.ui;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.mh.ormlitedemo.BaseActivity;
import com.mh.ormlitedemo.R;
import com.mh.ormlitedemo.test.model.BankCard;
import com.mh.ormlitedemo.test.dao.BankCardDao;
import com.mh.ormlitedemo.test.network.HttpClient;
import com.mh.ormlitedemo.test.network.HttpUrl;
import com.mh.ormlitedemo.test.utils.DatavalidationUtils;
import com.mh.ormlitedemo.test.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by JackHuang on 2016/9/2.
 */

public class AddBankCardActivity extends BaseActivity implements View.OnClickListener{

    private Button mConfirmBankCardAddBtn;
    private EditText cardHolderEdt;
    private EditText cardNumberEdt;

    private ProgressDialog mProgressDialog;

    @Override
    public void initView() {
        setContentView(R.layout.activity_bank_card_add);
        cardHolderEdt = (EditText) findViewById(R.id.card_holder_edt);
        cardNumberEdt = (EditText) findViewById(R.id.card_number_edt);
        mConfirmBankCardAddBtn = (Button) findViewById(R.id.confirm_bank_card_add_btn);
        mConfirmBankCardAddBtn.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        initTitle(true, "添加银行卡");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_bank_card_add_btn:
                confirmBankCardAdd();
                break;
        }

    }

    private void confirmBankCardAdd() {
        if (checkTypeFields()) {
            if (!DatavalidationUtils.isRegexChinese(cardHolderEdt.getText().toString())) {
                ToastUtil.showToast(this, "持卡人只允许汉字");
                return;
            }

            showProgressDialog();
            queryBankInfo(cardNumberEdt.getText().toString());
        }

    }

    private void showProgressDialog() {
    }

    public void queryBankInfo(String cardNumber) {
        // Create RequestBody
        Request request = new Request.Builder()
                .url(HttpUrl.ALIPAY_BANK_INFO_QUERY + cardNumber)
                .build();
        HttpClient.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bankCardAddedFailure("处理失败...");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(response.body().string());

                            if (obj.getBoolean("validated")) {
                                if ("CC".equals(obj.getString("cardType"))) {
                                    validatedCardTypeFailure();
                                } else {
                                    bankCardAddedSuccess(obj.getString("bank"), obj.getString("cardType"), obj.getString("key"));
                                }
                            } else {
                                bankCardAddedFailure("卡号验证失败, 请检查卡号");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void bankCardAddedSuccess(final String bank, final String cardType, String cardNumber) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String bankNameStr = new JSONObject(loadJSONFromAsset("bank.json")).getString(bank);
                    String cardTypeStr = new JSONObject(loadJSONFromAsset("card_type.json")).getString(cardType);

                    BankCard bankCard = new BankCard();
                    bankCard.setBankName(bankNameStr);
                    bankCard.setCardType(cardTypeStr);
                    bankCard.setCardNumber(cardNumberEdt.getText().toString());
                    bankCard.setHolderName(cardHolderEdt.getText().toString());
                    bankCard.setHolderName(cardHolderEdt.getText().toString());
                    bankCard.setUid("JackHuang");
//                    bankCard.setUid(getSharedPreferences("passWordFile", Context.MODE_PRIVATE).getString(LoginActivity.USERNAME, ""));

                    BankCardDao bankCardDao = new BankCardDao(getApplicationContext());
                    bankCardDao.add(bankCard);
//                    DBHelper.addBankCard(bankCard);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        hideProgressDialog();
                        cardHolderEdt.setText("");
                        cardNumberEdt.setText("");
                        Toast.makeText(AddBankCardActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                        mConfirmBankCardAddBtn.setEnabled(true);
                    }
                });
            }
        }).start();
    }

    public void validatedCardTypeFailure() {
//        hideProgressDialog();
        ToastUtil.showToast(AddBankCardActivity.this, "暂不支持信用卡");
        mConfirmBankCardAddBtn.setEnabled(true);
    }

    public void bankCardAddedFailure(String msg) {
//        hideProgressDialog();
        ToastUtil.showToast(this, msg);
        mConfirmBankCardAddBtn.setEnabled(true);
    }

  /*  public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }*/

    public boolean checkTypeFields() {
        if (cardNumberEdt.getText().toString() == null
                || "".equals(cardNumberEdt.getText().toString())
                || cardHolderEdt.getText().toString() == null
                || "".equals(cardHolderEdt.getText().toString())) {
            Toast.makeText(this, "持卡人或卡号未输入", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}
