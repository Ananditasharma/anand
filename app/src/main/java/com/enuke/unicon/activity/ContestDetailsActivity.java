package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.enuke.unicon.R;
import com.enuke.unicon.adapter.ParticipationVideoAdapter;
import com.enuke.unicon.utils.GridSpacingItemDecoration;
import com.enuke.unicon.utils.Utils;
import java.util.ArrayList;

public class ContestDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private LinearLayout llBack;
    private CardView btnDeadline;
    private RecyclerView recyclerParticipationVideo;
    private ParticipationVideoAdapter participationVideoAdapter;
    private CardView Card;
    private LinearLayout Linear2;
    private ArrayList<Boolean> videoList = new ArrayList<Boolean>() {{
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
        setContentView(R.layout.activity_contest_details);
        setToolbar();
        findViews();
        initializeListeners();
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        recyclerParticipationVideo = findViewById(R.id.recyclerParticipationVideo);
        btnDeadline = findViewById(R.id.btnDeadline);
        Card = findViewById(R.id.btnShareFeeDetails);
        Linear2 =findViewById(R.id.linear2);
        recyclerParticipationVideo.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerParticipationVideo.addItemDecoration(new GridSpacingItemDecoration(3, 10, false));
        participationVideoAdapter = new ParticipationVideoAdapter(this, videoList, new ParticipationVideoAdapter.BookMarkListener() {
            @Override
            public void onBookmarkClick(int position, boolean isSelected) {
                videoList.set(position,isSelected);
                participationVideoAdapter.notifyItemChanged(position);
            }
        });
        recyclerParticipationVideo.setAdapter(participationVideoAdapter);
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
            case R.id.linear2:
                startActivity(new Intent(ContestDetailsActivity.this, ShareFeeDetailsActivity.class));
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
