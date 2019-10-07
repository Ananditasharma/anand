package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

/**
 * +
 * This activity handle the social login
 * Email login
 * Facebook login
 * Gmail login
 * KakaoTalk Login
 */
public class SocialLoginActivity extends AppCompatActivity {
    public boolean flag_client = true;
    public static boolean creatorSignUpCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_social_login);

    }

    /***
     *
     * @param v
     * this Handle click for email login section
     */
    public void emaillogin(View v) {
        if(flag_client=true) {
            v.setEnabled(false);
            startActivity(new Intent(getApplicationContext(), Logincreator.class));
            setEnabledView(v);
        }
    }

    /***
     *
     * @param v
     * this Handle click for KakaoTalk Login section
     */
    public void kakaoTalkLogin(View v) {
        if(flag_client=true) {
            v.setEnabled(false);
            setEnabledView(v);
            startActivity(new Intent(SocialLoginActivity.this, FeedActivity.class));
        }

    }

    /***
     *
     * @param view
     * this Handle click for Facebook Login section
     */
    public void facebooklogin(View view) {
        if(flag_client=true) {
            view.setEnabled(false);
            setEnabledView(view);
            startActivity(new Intent(SocialLoginActivity.this, FeedActivity.class));
        }
    }

    /***
     *
     * @param view
     * this Handle click for Google Login section
     */
    public void googlelogin(View view) {
        if(flag_client=true) {
            view.setEnabled(false);
            setEnabledView(view);
            startActivity(new Intent(SocialLoginActivity.this, FeedActivity.class));
        }
    }

    /***
     *
     * @param view
     * this Handle click for Client Login section
     */
    public void clientlogin(View view) {

           view.setEnabled(false);
           startActivity(new Intent(getApplicationContext(), LoginActivity.class));
           setEnabledView(view);



    }

    /***
     *
     * @param view
     * this Handle click for Sign up section
     */
    public void signup(View view) {
        view.setEnabled(false);
//        Toast.makeText(this, "signup", Toast.LENGTH_SHORT).show();
        setEnabledView(view);
        startActivity(new Intent(SocialLoginActivity.this, SignUpActivity.class));
        creatorSignUpCheck = true;

    }




    /***
     *
     * @param view
     * this will enable the view after one second
     */
    private void setEnabledView(final View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        },1000);
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }

}
