package com.enuke.unicon.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.enuke.unicon.activity.SocialLoginActivity;
import com.enuke.unicon.R;
import com.enuke.unicon.custom.BottomNavigationBar;
import com.enuke.unicon.fragment.ApplicationWithdrawalFragment;
import com.enuke.unicon.fragment.ChannelFragment;
import com.enuke.unicon.fragment.FeedFragment;
import com.enuke.unicon.fragment.MyPageCreator1;
import com.enuke.unicon.fragment.Notice_fragment;
import com.enuke.unicon.utils.Utils;

public class FeedActivity extends AppCompatActivity implements View.OnClickListener {

    public BottomNavigationBar bottomNavigationView;
    public LinearLayout gradient_display;

    Fragment feedFragment;
    Fragment channelFragment;
    Fragment myPageCreator1;
    Fragment notice_fragment;
    boolean userRegistered = false;


    CardView btnRegistration;
    TextView clientLogin;
    ImageView imgClose;
    LinearLayout lltalk,llFacebook, llGoogle, llEmail;
    RelativeLayout rl_include_signup;
    public ApplicationWithdrawalFragment applicationWithdrawalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.setTransparentStatusbar(this);
        Utils.changeLang("KO", this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        applicationWithdrawalFragment = new ApplicationWithdrawalFragment();
        findViews();
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        try {
            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            rl_include_signup = findViewById(R.id.rl_include_signup);
            initializeFragment();
            bottomNavigationView.getFeedView().setOnClickListener(this);
            bottomNavigationView.getChannelView().setOnClickListener(this);
            bottomNavigationView.getRecordView().setOnClickListener(this);
            bottomNavigationView.getAlarmView().setOnClickListener(this);
            bottomNavigationView.getMyPageView().setOnClickListener(this);
            bottomNavigationView.setMenu(bottomNavigationView.getFeedView());

            View includeSignupView = findViewById(R.id.includeSignupView);
            btnRegistration = includeSignupView.findViewById(R.id.btnRegistration);
            clientLogin = includeSignupView.findViewById(R.id.clientLogin);
            imgClose = includeSignupView.findViewById(R.id.imgClose);
            lltalk = includeSignupView.findViewById(R.id.lltalk);
            llFacebook = includeSignupView.findViewById(R.id.llFacebook);
            llGoogle = includeSignupView.findViewById(R.id.llGoogle);
            llEmail = includeSignupView.findViewById(R.id.llemail);

            btnRegistration.setOnClickListener(this);
            clientLogin.setOnClickListener(this);
            imgClose.setOnClickListener(this);
            lltalk.setOnClickListener(this);
            llFacebook.setOnClickListener(this);
            llGoogle.setOnClickListener(this);
            llEmail.setOnClickListener(this);

            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            gradient_display = findViewById(R.id.gradient_display);
            bottomNavigationView.getFeedView().setOnClickListener(this);
            bottomNavigationView.getChannelView().setOnClickListener(this);
            bottomNavigationView.getRecordView().setOnClickListener(this);
            bottomNavigationView.getAlarmView().setOnClickListener(this);
            bottomNavigationView.getMyPageView().setOnClickListener(this);
            bottomNavigationView.setMenu(bottomNavigationView.getFeedView());
        }catch (Exception e){
            Log.e("FeedActivity", "findViews: " + e.getMessage());
        }

    }


    /**
     * default fragment initialization
     */
    private void initializeFragment() {
        notice_fragment = Notice_fragment.newInstance();
        feedFragment = FeedFragment.newInstance();
        channelFragment = ChannelFragment.newInstance();
        myPageCreator1 = MyPageCreator1.newInstance();
        openFragment(feedFragment);
    }

    /**
     * update fragment in view
     * @param fragment
     */
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedImage:
                openFragment(feedFragment);
                gradient_display.setVisibility(View.GONE);
                bottomNavigationView.setMenu(v);
                break;

            case R.id.channelImage:
                gradient_display.setVisibility(View.GONE);
                openFragment(channelFragment);
                bottomNavigationView.setMenu(v);
                break;

            case R.id.recordImage:
//                gradient_display.setVisibility(View.GONE);
//                if(userRegistered) {
//                    startActivity(new Intent(FeedActivity.this, PrizeAllocationActivity.class));
//                }else{
//                    rl_include_signup.setVisibility(View.VISIBLE);
//                    rl_include_signup.bringToFront();
//                }
                gradient_display.setVisibility(View.GONE);
                startActivity(new Intent(FeedActivity.this, PrizeAllocationActivity.class));
                bottomNavigationView.setMenu(v);
                break;

            case R.id.alarmImage:
                openFragment(notice_fragment);
                gradient_display.setVisibility(View.GONE);
                bottomNavigationView.setMenu(v);
                break;

            case R.id.myPageImage:
                openFragment(myPageCreator1);
                gradient_display.setVisibility(View.VISIBLE);
                bottomNavigationView.setMenu(v);
                break;

            default:
                gradient_display.setVisibility(View.GONE);
                openFragment(feedFragment);
                bottomNavigationView.setMenu(v);
                break;
        }

        if(v == clientLogin){

        }else if(v == btnRegistration){
                startActivity(new Intent(FeedActivity.this, SignUpActivity.class));
        }else if(v == imgClose){

        }else if(v == lltalk){

        }else if(v == llFacebook){

        }else if(v == llGoogle){

        }else if(v == llEmail){

        }
    }
}