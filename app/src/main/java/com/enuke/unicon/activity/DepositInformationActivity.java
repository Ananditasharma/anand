package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class DepositInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llBack;
    private TextView tvTitle, tvMainPrizeWon, tvBankName, tvBankAccountNumber;
    private CardView btnDeposit;
    SQLiteDatabase dBase;

    private int prizeAmount,bankAccountNumber;
    private String bankName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.changeLang("KO", this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposit_information);
        setToolbar();
        findViews();
        initializeListeners();
        dBase = (new DataBaseClass(this)).getWritableDatabase();

//        updateRegistrationConfirmationView();
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        tvMainPrizeWon = findViewById(R.id.tvMainPrizeWon);
        tvBankName = findViewById(R.id.tvBankName);
        tvBankAccountNumber = findViewById(R.id.tvBankAccountNumber);
        btnDeposit = findViewById(R.id.btnDeposit);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.deposit_information));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnDeposit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.btnDeposit:
                startActivity(new Intent(DepositInformationActivity.this, FeedActivity.class));
                break;
        }
    }

    private void updateRegistrationConfirmationView(){
        try {
            Cursor cr_find_contest_reg = dBase.rawQuery("SELECT prizeAmount,bankAccountNumber,bankName FROM ClientContestTable WHERE userId='" + "" + "'", null);
            if (cr_find_contest_reg.getCount() > 0) {
                cr_find_contest_reg.moveToFirst();
                prizeAmount = cr_find_contest_reg.getInt(0);
                bankAccountNumber = cr_find_contest_reg.getInt(1);
                bankName = cr_find_contest_reg.getString(2);

                tvMainPrizeWon.setText("" + prizeAmount);
                tvBankAccountNumber.setText("" + bankAccountNumber);
                tvBankName.setText("" + bankName);

            }
            cr_find_contest_reg.close();
        }catch (Exception e){
            Log.e("DepositInformationAct", "updateRegistrationConfirmationView: " + e.getMessage());

        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
