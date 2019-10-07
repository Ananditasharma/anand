package com.enuke.unicon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.enuke.unicon.R;
import com.enuke.unicon.fragment.MyPageCreator1;
import com.enuke.unicon.utils.Utils;
import android.widget.Button;

public class creator_video  extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTitle;
    private LinearLayout llBack;
  
    private TextView etContestContent;
    private TextView tvContentLength;
    private CardView BtnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.creator_video);
        setToolbar();
        findViews();
        initializeListeners();
       

    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.Upload_Video));
    }


    private void findViews() {
        BtnDone = findViewById(R.id.btnDone);
        etContestContent = findViewById(R.id.etContestContent);
        tvContentLength = findViewById(R.id.tvContentLength);
    }
    private void initializeListeners() {
        llBack.setOnClickListener(this);
         BtnDone.setOnClickListener(this);



        etContestContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvContentLength.setText(s.length() + " / " + 200);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.btnDone:
                MyPageCreator1 myPageCreator1 = new MyPageCreator1();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myPageCreator1).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }

}


