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

public class ShareFeeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llBack;
    private TextView tvTitle, tvPeopleCount;
    private EditText etCurrentShareFee, etCostPerShare, etLimitReached;
    private CardView btnShare, btnChange;
    private TextWatcher twCostpershare;
    private TextWatcher twLimitReached;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_share_fee_details);
        setToolbar();
        initializeWatchers();
        findViews();
        initializeListeners();
    }



    /***
     * This method register all views
     *
     */
    private void findViews() {
        tvPeopleCount = findViewById(R.id.tvPeopleCount);
        etCurrentShareFee = findViewById(R.id.etCurrentShareFee);
        etCostPerShare = findViewById(R.id.etCostPerShare);
        etLimitReached = findViewById(R.id.etLimitReached);
        btnChange = findViewById(R.id.btnChange);
        btnShare = findViewById(R.id.btnShare);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.share_fee_details));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        etCostPerShare.addTextChangedListener(twCostpershare);
        etLimitReached.addTextChangedListener(twLimitReached);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                finish();
                break;
            case R.id.btnChange:

                break;
            case R.id.btnShare:
                startActivity(new Intent(ShareFeeDetailsActivity.this, ShareChargeActivity.class));
                break;
        }
    }

    /***
     * this method change Current Share Fee
     * @return
     */
    private boolean changeCurrentShareFee() {
        long costPerShare = 0, limitReachedValue = 0;
        long currentShareValue = 0;
        String etCostPerShareValue = etCostPerShare.getText().toString();
        String etLimitReachedValue = etLimitReached.getText().toString();

        if (!TextUtils.isEmpty(etCostPerShareValue)) {
            if (etCostPerShareValue.contains(",")) {
                etCostPerShareValue = etCostPerShareValue.replaceAll(",", "");
            }
            try {
                costPerShare = Long.parseLong(etCostPerShareValue);
            } catch (Exception e) {
                Log.d("costPerShare_Exception", e.toString());
                return false;
            }

        }
        if (!TextUtils.isEmpty(etLimitReachedValue)) {
            if (!TextUtils.isEmpty(etLimitReachedValue)) {
                if (etLimitReachedValue.contains(",")) {
                    etLimitReachedValue = etLimitReachedValue.replaceAll(",", "");
                }
                try {
                    limitReachedValue = Long.parseLong(etLimitReachedValue);
                } catch (Exception e) {
                    Log.d("limit_Exception", e.toString());
                    return false;
                }
            }
        }

        currentShareValue = costPerShare * limitReachedValue;
        etCurrentShareFee.setText(formateStringtoComma(currentShareValue));
        return true;
    }

    /**
     * This method initialize TextWatchers
     */
    private void initializeWatchers() {
        twCostpershare = new TextWatcher() {
            ;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeCurrentShareFee();
            }

            @Override
            public void afterTextChanged(Editable s) {
                addCommaInText(etCostPerShare, s, this);
            }
        };
        twLimitReached = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeCurrentShareFee();
            }

            @Override
            public void afterTextChanged(Editable s) {
                addCommaInText(etLimitReached, s, this);
            }
        };

    }


    /***
     * This method add Comma in Text
     * @param editText
     * @param editable
     * @param textWatcher
     */
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

    /***
     * this method reformat tha String to add Comma
     * @param longval
     * @return
     */
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
