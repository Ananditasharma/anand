package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ShareChargeActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout llBack;
    private TextView tvTitle ;
    private EditText etShareToCharge, etCurrentShareFee, etCostPerShare, etLimitReached;
    private CardView btnCharging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_share_charge);
        setToolbar();
        findViews();
        initializeListeners();

    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        etShareToCharge = findViewById(R.id.etShareToCharge);
        etCurrentShareFee = findViewById(R.id.etCurrentShareFee);
        etCostPerShare = findViewById(R.id.etCostPerShare);
        etLimitReached = findViewById(R.id.etLimitReached);
        btnCharging = findViewById(R.id.btnCharging);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.share_charge));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnCharging.setOnClickListener(this);
         }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                finish();
                break;
            case R.id.btnCharging:
           startActivity(new Intent(ShareChargeActivity.this,share_cost_deposit.class));
                break;
        }
    }


    private void addCommaInText(EditText editText, Editable editable, TextWatcher textWatcher) {
        editText.removeTextChangedListener(textWatcher);
        try {
            String originalString = editable.toString();
            Long longval;
            if (originalString.contains(",")) {
                originalString = originalString.replaceAll(",", "");
            }
            longval = Long.parseLong(originalString);
            //setting text after format to EditText
            editText.setText(formateStringtoComma(longval));
            editText.setSelection(editText.getText().length());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        editText.addTextChangedListener(textWatcher);
    }
    private String formateStringtoComma(Long longval) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.KOREA);
        formatter.applyPattern("#,###,###,###");
        return formatter.format(longval);
    }


    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
