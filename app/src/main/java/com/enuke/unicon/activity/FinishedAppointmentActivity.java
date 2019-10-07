package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class FinishedAppointmentActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llBack;
    private TextView tvTitle, tvBankName, tvBankAccountNumber;
    private CardView btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.contest_registered);
        setToolbar();
        findViews();
        initializeListeners();
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        tvBankName = findViewById(R.id.tvBankName);
        tvBankAccountNumber = findViewById(R.id.tvBankAccountNumber);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.finished_appointment));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.btnConfirm:
                startActivity(new Intent(FinishedAppointmentActivity.this, ShareFeeDetailsActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}