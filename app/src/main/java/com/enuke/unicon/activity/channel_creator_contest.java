package com.enuke.unicon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.adapter.ParticipationVideoAdapter;
import com.enuke.unicon.utils.GridSpacingItemDecoration;
import com.enuke.unicon.utils.Utils;
import com.enuke.unicon.adapter.ParticipationVideoAdapter1;

import java.util.ArrayList;

public class channel_creator_contest  extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private LinearLayout llBack;
    private CardView btnDeadline;
    private RecyclerView recyclerParticipationVideo1;
    private ParticipationVideoAdapter participationVideoAdapter1;
    private CardView Card;
    private LinearLayout Linear2;
    private ArrayList <Boolean> videoList = new ArrayList<Boolean>() {{
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.channel_creator_contest);
        setToolbar();
        findViews();
        initializeListeners();
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        recyclerParticipationVideo1 = findViewById(R.id.recyclerParticipationVideo1);
        btnDeadline = findViewById(R.id.btnDeadline);
        Card = findViewById(R.id.btnShareFeeDetails);
        Linear2 =findViewById(R.id.linear2);
        recyclerParticipationVideo1.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerParticipationVideo1.addItemDecoration(new GridSpacingItemDecoration(3, 10, false));

        recyclerParticipationVideo1.setAdapter(participationVideoAdapter1);
    }

    /**
     * change toolbar content
     */
    private void setToolbar(){
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.contest_details));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnDeadline.setOnClickListener(this);
        Linear2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            /*case R.id.linear2:
                startActivity(new Intent(channel_creator_contest.this, ShareFeeDetailsActivity.class));
                break;
            /*case R.id.btnDeadline:
               // startActivity(new Intent(ContestDetailsActivity.this, PrizeAllocationActivity.class));
                break;*/

        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}

