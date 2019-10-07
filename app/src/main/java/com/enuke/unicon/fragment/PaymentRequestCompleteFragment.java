package com.enuke.unicon.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.FeedActivity;


public class PaymentRequestCompleteFragment extends Fragment implements View.OnClickListener {

    CardView btnComplete;
    TextView tvWithdrawalAmount, tvBankName, tvBankAccountNumber;
    LinearLayout llBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment_request_complete, container, false);
        btnComplete = v.findViewById(R.id.btnComplete);
        tvWithdrawalAmount = v.findViewById(R.id.tvWithdrawalAmount);
        tvBankName = v.findViewById(R.id.tvBankName);
        tvBankAccountNumber = v.findViewById(R.id.tvBankAccountNumber);
        llBack = v.findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);

        btnComplete.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        if(view == btnComplete){
            SettingsFragment settingsFragment = new SettingsFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).addToBackStack(null).commit();
        }else if(view == llBack){
            BankSelectionFragment bankSelectionFragment = new BankSelectionFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, bankSelectionFragment).addToBackStack(null).commit();
        }
    }
}
