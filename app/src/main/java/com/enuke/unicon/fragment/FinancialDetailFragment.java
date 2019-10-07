package com.enuke.unicon.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.FeedActivity;
import com.enuke.unicon.adapter.FinancialDetailListAdapter;

import java.util.ArrayList;


public class FinancialDetailFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    RecyclerView recyclerFinancialDetail;
    FinancialDetailListAdapter financialDetailListAdapter;
    LinearLayout llBack;
    FeedActivity feedActivity;
    TextView tvMainPrizeWon,tv_share_cost,tv_v1,tv_value;
    CardView btnNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedActivity = (FeedActivity)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_financial_detail, container, false);

        try {
            llBack = v.findViewById(R.id.llBack1);
             btnNext = v.findViewById(R.id.btnNext);
            llBack.setOnClickListener(this);
            btnNext.setOnClickListener(this);
            recyclerFinancialDetail = v.findViewById(R.id.recyclerFinancialDetail);
            tvMainPrizeWon = v.findViewById(R.id.tvMainPrizeWon);
            tv_share_cost = v.findViewById(R.id.tv_share_cost);
            tv_value = v.findViewById(R.id.tv_value);
            tv_v1 = v.findViewById(R.id.tv_v1);

        /*change view for financial card click*/
            if(feedActivity.applicationWithdrawalFragment.financialCardCheck){
                tvMainPrizeWon.setText("출금할 상금을 선택하세요");
                tv_share_cost.setText("선택된 상금");
                tv_v1.setVisibility(View.GONE);
                tv_value.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
            }

            recyclerFinancialDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
            ArrayList list = new ArrayList<String>() {{
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
                add("7");
                add("8");
                add("9");
                add("10");
            }};

            financialDetailListAdapter = new FinancialDetailListAdapter(getActivity(), list, feedActivity.applicationWithdrawalFragment);
            recyclerFinancialDetail.setAdapter(financialDetailListAdapter);

        }catch (Exception e){
            Log.e("FinancialDetailFragment","onCreateView : " + e.getMessage());
        }

        return v;
    }

    public static FinancialDetailFragment newInstance() {
        FinancialDetailFragment fragment = new FinancialDetailFragment();
        return fragment;
    }

    @Override
    public void onClick(View view) {
        if(view == llBack){
            MyPrizeMoneyFragment myPrizeMoneyFragment = new MyPrizeMoneyFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, myPrizeMoneyFragment).commit();
        }else if(view == btnNext){
            BankSelectionFragment bankSelectionFragment = new BankSelectionFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, bankSelectionFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
