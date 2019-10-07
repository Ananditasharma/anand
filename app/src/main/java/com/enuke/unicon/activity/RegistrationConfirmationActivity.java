package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class RegistrationConfirmationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvRewardAmount;
    private TextView tvPercentFirstPlace;
    private TextView tvPercentSecondPlace;
    private TextView tvPercentThirdPlace;
    private TextView tvDeadlineStart;
    private TextView tvDeadlineEnd;
    private TextView tvJudgingDeadline;
    private TextView tvAnnouncement;
    private ImageView imgMain;
    private CardView btnNext;
    private LinearLayout llBack;
    SQLiteDatabase dBase;

    private String contestEventPeriodFrom, contestEventPeriodTo, contestExaminationPeriodTo, contestExaminationPeriodFrom, contestPublicationDate, contestDetail, contestContent;
    private int prizeAmount,contestFirstPlaceAmount,contestSecondPlaceAmount,contestThirdPlaceAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.registration_confirmation);
        setToolbar();
        findViews();
        initializeListeners();
        dBase = (new DataBaseClass(this)).getWritableDatabase();

        /*updating local*/
        updateRegistrationConfirmationView();

    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        tvRewardAmount = findViewById(R.id.tvRewardAmount);
        tvPercentFirstPlace = findViewById(R.id.tvPercentFirstPlace);
        tvPercentSecondPlace = findViewById(R.id.tvPercentSecondPlace);
        tvPercentThirdPlace = findViewById(R.id.tvPercentThirdPlace);
        tvDeadlineStart = findViewById(R.id.tvDeadlineStart);
        tvDeadlineEnd = findViewById(R.id.tvDeadlineEnd);
        tvJudgingDeadline = findViewById(R.id.tvJudgingDeadline);
        tvAnnouncement = findViewById(R.id.tvAnnouncement);
        btnNext = findViewById(R.id.btnNext);
        imgMain = findViewById(R.id.imgMain);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.registration_confirmation));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.btnNext:
                startActivity(new Intent(RegistrationConfirmationActivity.this,DepositInformationActivity.class));
                break;
        }
    }

    private void updateRegistrationConfirmationView(){
        try {
            Cursor cr_find_contest_reg = dBase.rawQuery("SELECT prizeAmount,contestFirstPlaceAmount,contestSecondPlaceAmount,contestThirdPlaceAmount,contestEventPeriodFrom,contestEventPeriodTo,contestExaminationPeriodFrom,contestExaminationPeriodTo," +
                    "contestPublicationDate,contestDetail,contestContent FROM ClientContestTable WHERE userId='" + "" + "'", null);
            if (cr_find_contest_reg.getCount() > 0) {
                cr_find_contest_reg.moveToFirst();
                prizeAmount = cr_find_contest_reg.getInt(0);
                contestFirstPlaceAmount = cr_find_contest_reg.getInt(1);
                contestSecondPlaceAmount = cr_find_contest_reg.getInt(2);
                contestThirdPlaceAmount = cr_find_contest_reg.getInt(3);
                contestEventPeriodFrom = cr_find_contest_reg.getString(4);
                contestEventPeriodTo = cr_find_contest_reg.getString(5);
                contestExaminationPeriodTo = cr_find_contest_reg.getString(6);
                contestExaminationPeriodFrom = cr_find_contest_reg.getString(7);
                contestPublicationDate = cr_find_contest_reg.getString(8);
                contestDetail = cr_find_contest_reg.getString(9);
                contestContent = cr_find_contest_reg.getString(10);

            }
            cr_find_contest_reg.close();
        }catch (Exception e){
            Log.e("RegistrationConfirmAct", "updateRegistrationConfirmationView: " + e.getMessage());
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
