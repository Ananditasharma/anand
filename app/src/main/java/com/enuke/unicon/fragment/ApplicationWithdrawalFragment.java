package com.enuke.unicon.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.FeedActivity;

public class ApplicationWithdrawalFragment extends Fragment implements View.OnClickListener {
    CardView btnNext,cardViewPrize,cardViewShare;
    private int statusBarHeight = 0;
    public boolean financialCardCheck = false;
    FeedActivity feedActivity;
    LinearLayout llBack;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedActivity = (FeedActivity)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_application_withdrawal, container, false);

        btnNext = v.findViewById(R.id.btnNext);
        cardViewShare = v.findViewById(R.id.cardViewShare);
        cardViewPrize = v.findViewById(R.id.cardViewPrize);
        llBack = v.findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);


        btnNext.setOnClickListener(this);
        cardViewPrize.setOnClickListener(this);
        cardViewShare.setOnClickListener(this);
        return v;

    }

    public static ApplicationWithdrawalFragment newInstance() {
        ApplicationWithdrawalFragment fragment = new ApplicationWithdrawalFragment();
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Rect rectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        statusBarHeight = rectangle.top;
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext){
            MyBadgeFragment myBadgeFragment = new MyBadgeFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, myBadgeFragment).addToBackStack(null).commit();
        }else if(view == cardViewPrize){
            FinancialDetailFragment financialDetailFragment = new FinancialDetailFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, financialDetailFragment).addToBackStack(null).commit();
            feedActivity.applicationWithdrawalFragment.financialCardCheck = true;

        }else if(view == cardViewShare){
            RequestPaymentFragment requestPaymentFragment = new RequestPaymentFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, requestPaymentFragment).addToBackStack(null).commit();
            feedActivity.applicationWithdrawalFragment.financialCardCheck = false;
        }else if(view == llBack){
            MyPrizeMoneyFragment myPrizeMoneyFragment = new MyPrizeMoneyFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, myPrizeMoneyFragment).addToBackStack(null).commit();
        }

    }
}
