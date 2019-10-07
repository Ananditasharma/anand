package com.enuke.unicon.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.FeedActivity;
import com.enuke.unicon.activity.MyprofileActivity;
import com.enuke.unicon.activity.SocialLoginActivity;

public class SettingsFragment extends Fragment implements View.OnClickListener {



    private RelativeLayout rl1;
   RelativeLayout rl_myPrize;
   FeedActivity feedActivity;
   LinearLayout ll_logout;
    private TextView tvTitle;
    private LinearLayout llBack;
    private RelativeLayout main;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Rect rectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        //  boolean index = args.getBoolean("flag_client", true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings, container, false);
        feedActivity = (FeedActivity)getActivity();
        findViews(view);
        VisibleBottomNavigation();
        setToolbar(view);
        return view;
    }

    private void findViews(View view) {
        main= view.findViewById(R.id.main);
        rl1= view.findViewById(R.id.rlmyprofile);
        rl_myPrize = view.findViewById(R.id.rl_myPrize);
        ll_logout = view.findViewById(R.id.ll_logout);
        rl_myPrize.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
        rl1.setOnClickListener(this);
    }
    private void VisibleBottomNavigation(){
        feedActivity.bottomNavigationView.setVisibility(View.VISIBLE);
        feedActivity.gradient_display.setVisibility(View.VISIBLE);
    }


    private void setToolbar(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        llBack = view.findViewById(R.id.llBack1);
        tvTitle.setText("설정");
        llBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlmyprofile:
                startActivity(new Intent(getActivity(), MyprofileActivity.class));
                 break;
            case R.id.rl_myPrize:
                MyPrizeMoneyFragment myPrizeMoneyFragment = new MyPrizeMoneyFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main, myPrizeMoneyFragment).addToBackStack(null).commit();
                break;
            case R.id.ll_logout:
                startActivity(new Intent(getActivity(), SocialLoginActivity.class));
                break;
        }
    }
}

