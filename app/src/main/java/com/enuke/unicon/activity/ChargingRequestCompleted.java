package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class ChargingRequestCompleted extends AppCompatActivity  implements View.OnClickListener{
    private LinearLayout llBack;
    private TextView tvTitle, tvBankName, tvBankAccountNumber;
    private CardView btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.changeLang("KO", this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charging_request);
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
        btnComplete = findViewById(R.id.btnComplete);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.charging_request_completed));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnComplete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.btnComplete:

                break;
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
