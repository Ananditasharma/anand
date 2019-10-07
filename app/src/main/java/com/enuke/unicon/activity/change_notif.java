package com.enuke.unicon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class change_notif extends AppCompatActivity implements View.OnClickListener {
    ImageView next;
    private CardView Btndeposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.login_changenotif);
         findviews();
    }
    private void findviews(){
        Btndeposit = findViewById(R.id.btnDeposit);
        Btndeposit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnDeposit:
                startActivity(new Intent(change_notif.this, SocialLoginActivity.class));
                break;
        }
    }
    }
