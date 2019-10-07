package com.enuke.unicon.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.enuke.unicon.R;
import com.enuke.unicon.fragment.Accinfo;
import com.enuke.unicon.fragment.ChannelFragment;
import com.enuke.unicon.fragment.FeedFragment;
import com.enuke.unicon.fragment.MyPageCreator1;
import com.enuke.unicon.fragment.SettingsFragment;
import com.enuke.unicon.utils.Utils;

public class MyprofileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private LinearLayout llBack;
    private CardView Btn;
    private ImageView Btnacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.mypagepersonal);

        setToolbar();
        findViews();
    }

    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.personal));
    }

    private void findViews() {
        Btnacc = findViewById(R.id.accbtn);
        Btn = findViewById(R.id.btnSave);
        Btn.setOnClickListener(this);
        llBack.setOnClickListener(this);
        Btnacc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;

            case R.id.btnSave:
               getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.accbtn:
                Accinfo myacc = new Accinfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.rlmainLayout, myacc).addToBackStack(null).commit();
                break;


        }
    }

}
