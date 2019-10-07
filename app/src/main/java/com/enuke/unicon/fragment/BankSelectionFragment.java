package com.enuke.unicon.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.enuke.unicon.R;

public class BankSelectionFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    CardView btnNext;
    TextView tvSelectedPrize,tvPlatformFee,tvSettlementFee;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    RadioGroup rg_banks;
    ImageView iv_info;
    LinearLayout llBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bank_selection, container, false);
        btnNext = v.findViewById(R.id.btnNext);
        tvSelectedPrize = v.findViewById(R.id.tvSelectedPrize);
        tvPlatformFee = v.findViewById(R.id.tvPlatformFee);
        tvSettlementFee = v.findViewById(R.id.tvSettlementFee);
        radioButton1 = v.findViewById(R.id.radioButton1);
        radioButton2 = v.findViewById(R.id.radioButton2);
        radioButton3 = v.findViewById(R.id.radioButton3);
        radioButton4 = v.findViewById(R.id.radioButton4);
        rg_banks = v.findViewById(R.id.rg_banks);
        iv_info = v.findViewById(R.id.iv_info);
        llBack = v.findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);

        btnNext.setOnClickListener(this);
        iv_info.setOnClickListener(this);
        rg_banks.setOnCheckedChangeListener(this);

        rg_banks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });


        return v;
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext){
            showDialog();

        }else if(view == iv_info) {

        }else if(view == llBack){
            RequestPaymentFragment requestPaymentFragment = new RequestPaymentFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, requestPaymentFragment).addToBackStack(null).commit();
        }
    }

    private void showDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.dialog_registration_error, null);
        dialogBuilder.setView(dialogView);

        //Show the dialog
        final AlertDialog alert = dialogBuilder.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alert.setCancelable(false);
        TextView alert_header = dialogView.findViewById(R.id.alert_header);
        TextView alert_detail = dialogView.findViewById(R.id.tv_alert_detail);
        TextView confirm = dialogView.findViewById(R.id.ok1);
        alert_header.setText("플랫폼수수료 및 제세공과금");
        alert_detail.setText("플랫폼 수수료는 플랫폼 유지보수에 사용되며, 소득세법에 따라 5만원 초과 시 상금 및 공유비의 22%에 해당하는 제세공과금을 제외한 금액이 지급됩니다. ");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
                PaymentRequestCompleteFragment paymentRequestCompleteFragment = new PaymentRequestCompleteFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, paymentRequestCompleteFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(radioGroup == rg_banks) {
            if (radioButton1.isChecked()) {
                radioButton1.setChecked(true);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                radioButton4.setChecked(false);

            } else if (radioButton2.isChecked()) {
                radioButton2.setChecked(true);
                radioButton1.setChecked(false);
                radioButton3.setChecked(false);
                radioButton4.setChecked(false);

            } else if (radioButton3.isChecked()) {
                radioButton2.setChecked(false);
                radioButton1.setChecked(false);
                radioButton3.setChecked(true);
                radioButton4.setChecked(false);

            } else if (radioButton4.isChecked()) {
                radioButton2.setChecked(false);
                radioButton1.setChecked(false);
                radioButton3.setChecked(false);
                radioButton4.setChecked(true);
            }
        }
    }
}
