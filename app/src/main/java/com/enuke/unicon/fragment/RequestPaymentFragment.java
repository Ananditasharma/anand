package com.enuke.unicon.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;


public class RequestPaymentFragment extends Fragment implements View.OnClickListener {

    CardView btnNext;
    EditText etWithdrawalFee;
    TextView tvShareCost;
    LinearLayout llBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_request_payment, container, false);

        tvShareCost = v.findViewById(R.id.tvShareCost);
        etWithdrawalFee = v.findViewById(R.id.etWithdrawalFee);
        btnNext = v.findViewById(R.id.btnNext);
        llBack = v.findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);

        btnNext.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext){
            BankSelectionFragment bankSelectionFragment = new BankSelectionFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, bankSelectionFragment).addToBackStack(null).commit();
        }else if(view == llBack){
            FinancialDetailFragment financialDetailFragment = new FinancialDetailFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, financialDetailFragment).addToBackStack(null).commit();
        }
    }
}
