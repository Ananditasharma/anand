package com.enuke.unicon.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class login_changednotif extends AppCompatActivity {

    private TextView tvTitle;
    private LinearLayout llBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.login_changenotif);
        setToolbar();



    }
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.forgot_password1));
    }


}
