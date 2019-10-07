package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;
import com.enuke.unicon.adapter.MusicListAdapter;
import com.enuke.unicon.adapter.StaredListAdapter;
import com.enuke.unicon.utils.Utils;

import java.util.ArrayList;

public class OngoingContestActivity extends AppCompatActivity implements View.OnClickListener {

    private StaredListAdapter staredListAdapter;
    private MusicListAdapter musicListAdapter;
    private RecyclerView recyclerStaredList;
    private RecyclerView recyclerMusicList;

    private Button btnPopular;
    private Button btnNew;
    private LinearLayout llBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_ongoing_contest);
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
        tvTitle.setText(getString(R.string.ongoing_contest));
    }

    /***
     * This method register all views
     */
    private void findViews() {
        btnNew = findViewById(R.id.btnNew);
        btnPopular = findViewById(R.id.btnPopular);
        recyclerStaredList = findViewById(R.id.recyclerStaredList);
        recyclerMusicList = findViewById(R.id.recyclerMusicList);

        recyclerStaredList.setLayoutManager(new LinearLayoutManager(OngoingContestActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerMusicList.setLayoutManager(new LinearLayoutManager(OngoingContestActivity.this));

        ArrayList list = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
            add("10");
        }};
        staredListAdapter = new StaredListAdapter(this, list);
        recyclerStaredList.setAdapter(staredListAdapter);

       musicListAdapter = new MusicListAdapter(OngoingContestActivity.this, list);
        recyclerMusicList.setAdapter(musicListAdapter);
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnPopular.setOnClickListener(this);
        btnNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                finish();
                break;
            case R.id.btnPopular:

                recyclerMusicList.setVisibility(View.GONE);
                btnPopular.setBackgroundResource(R.drawable.bg_left_selected);
                btnNew.setBackgroundResource(R.drawable.bg_right_unselected);
                btnPopular.setTextColor(getResources().getColor(R.color.white));
                btnNew.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.btnNew:
                btnPopular.setBackgroundResource(R.drawable.bg_left_unselected);
                btnNew.setBackgroundResource(R.drawable.bg_right_selected);
                btnPopular.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnNew.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
