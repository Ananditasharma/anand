package com.enuke.unicon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class share_cost_deposit  extends AppCompatActivity implements View.OnClickListener{
private CardView btn;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.share_cost);
        findViews();
        initializeListeners();
        setToolbar();
    }
    private void findViews(){

        btn = findViewById(R.id.btnComplete);

    }
    private void setToolbar(){
        tvTitle = findViewById(R.id.tvTitle);

        tvTitle.setText(getString(R.string.contest_details));
    }
    private void initializeListeners() {

        btn.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnComplete:
                startActivity(new Intent(share_cost_deposit.this,ContestDetailsActivity.class));
                break;

        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
