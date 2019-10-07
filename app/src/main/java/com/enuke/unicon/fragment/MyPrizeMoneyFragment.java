package com.enuke.unicon.fragment;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.FeedActivity;
import com.enuke.unicon.custom.BottomNavigationBar;
import com.enuke.unicon.fragment.ApplicationWithdrawalFragment;
import com.enuke.unicon.fragment.FinancialDetailFragment;

public class MyPrizeMoneyFragment extends Fragment implements View.OnClickListener {
    CardView btnDetails, btnWithdraw;
    TextView tvTitle,tvMainPrizeWon, tvAccumulatedPrize, tvShareCost, tvCumulativeShareCost;
    Fragment financialDetailFragment;
    Fragment applicationWithdrawalFragment;
    LinearLayout llBack;
    FeedActivity feedActivity;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myprize_money, container, false);
        feedActivity = (FeedActivity)getActivity();
        tvMainPrizeWon = v.findViewById(R.id.tvMainPrizeWon);
        tvAccumulatedPrize = v.findViewById(R.id.tvAccumulatedPrize);
        tvShareCost = v.findViewById(R.id.tvShareCost);
        tvCumulativeShareCost = v.findViewById(R.id.tvCumulativeShareCost);
        btnDetails = v.findViewById(R.id.btnDetails);
        btnWithdraw = v.findViewById(R.id.btnWithdraw);
        llBack = v.findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);

        btnDetails.setOnClickListener(this);
        btnWithdraw.setOnClickListener(this);

        HideBottomNavigationView();

        initializeFragment();
        return v;

    }


    private void initializeFragment() {
        applicationWithdrawalFragment = ApplicationWithdrawalFragment.newInstance();
        financialDetailFragment = FinancialDetailFragment.newInstance();
    }

    private void HideBottomNavigationView(){
        feedActivity.bottomNavigationView.setVisibility(View.GONE);
        feedActivity.gradient_display.setVisibility(View.GONE);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        try {
            if (v == btnDetails) {
                openFragment(financialDetailFragment);
            } else if (v == btnWithdraw) {
                openFragment(applicationWithdrawalFragment);
            }else if(v == llBack){
                getActivity().onBackPressed();
            }
        }catch (Exception e){
            Log.e("MyPrizeMoneyFragment", "onClick: " + e.getMessage());
        }

    }
}
