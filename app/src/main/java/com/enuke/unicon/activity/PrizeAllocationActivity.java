package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
import com.enuke.unicon.Class.AppSingleton;
import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PrizeAllocationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private LinearLayout llBack;
    private TextView tvMainPrizeWon, tvMainPrizeWonText, tvPercentFirstPlace,
            tvPercentSecondPlace, tvPercentThirdPlace, tvPrizeWonFirstPlace,
            tvPrizeWonSecondPlace, tvPrizeWonThirdPlace, tvPrizeSum, tvPrizeSumText,tvPlatformFee;
    private int mainPrize = 5000000;
    private CardView btnNext, btnChange;
    private String URL_FOR_CONTEST = "";
    SQLiteDatabase db;

    private String prizeAmount, contestFirstPlaceAmount, contestSecondPlaceAmount, contestThirdPlaceAmount, contestPlatformFee, contestVAT, contestSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_prize_allocation);
        setToolbar();
        findViews();
        initializeListeners();
        db = (new DataBaseClass(this)).getWritableDatabase();

    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.prize_allocation));
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        tvPercentFirstPlace = findViewById(R.id.tvPercentFirstPlace);
        tvPercentSecondPlace = findViewById(R.id.tvPercentSecondPlace);
        tvPercentThirdPlace = findViewById(R.id.tvPercentThirdPlace);
        tvMainPrizeWon = findViewById(R.id.tvMainPrizeWon);
        tvMainPrizeWonText = findViewById(R.id.tvMainPrizeWonText);
        tvPrizeWonFirstPlace = findViewById(R.id.tvPrizeWonFirstPlace);
        tvPrizeWonSecondPlace = findViewById(R.id.tvPrizeWonSecondPlace);
        tvPrizeWonThirdPlace = findViewById(R.id.tvPrizeWonThirdPlace);
        tvPlatformFee = findViewById(R.id.tvPlatformFee);
        tvPrizeSum = findViewById(R.id.tvPrizeSum);
        tvPrizeSumText = findViewById(R.id.tvPrizeSumText);
        tvMainPrizeWon.setText(String.valueOf(mainPrize));
        btnChange = findViewById(R.id.btnChange);
        btnNext = findViewById(R.id.btnNext);
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.btnChange:
                startActivity(new Intent(PrizeAllocationActivity.this,ChangingPriceAllocationActivity.class));
                break;
            case R.id.btnNext:
                updatePrizeContestToLDB();
//                updatePrizeContest();
                startActivity(new Intent(PrizeAllocationActivity.this,ContestBasicInformationActivity.class));
                break;
        }
    }


    private void updatePrizeContestToLDB(){
        try {

            prizeAmount = tvMainPrizeWon.getText().toString();
            contestFirstPlaceAmount = tvPrizeWonFirstPlace.getText().toString();
            contestSecondPlaceAmount = tvPrizeWonSecondPlace.getText().toString();
            contestThirdPlaceAmount = tvPrizeWonThirdPlace.getText().toString();
            contestPlatformFee = tvPlatformFee.getText().toString();
            contestSum = tvPrizeSum.getText().toString();

            db.execSQL("INSERT INTO ClientContestInfo (prizeAmount,contestFirstPlaceAmount,contestSecondPlaceAmount,contestThirdPlaceAmount,contestPlatformFee,contestVAT,contestSum) VALUES(" +
                    "'" + prizeAmount + "'," +
                    "'" + contestFirstPlaceAmount + "'," +
                    "'" + contestSecondPlaceAmount + "'," +
                    "'" + contestThirdPlaceAmount + "'," +
                    "'" + contestPlatformFee + "'," +
                    "'" + contestSum + "');");
        }catch (Exception e){
            Log.e("PrizeAllocationActivity", "updateLocalDataBase: " + e.getMessage());
        }
    }

    private void updatePrizeContest(){
        try{
        // Tag used to cancel the request
        String cancel_req_tag = "contest";
//        pb_loader.setVisibility(View.VISIBLE);
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                URL_FOR_CONTEST, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.i("", "Response: " + response.toString());
////                pb_loader.setVisibility(View.GONE);
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//
//                    if (!error) {
//                        Intent intent = new Intent(PrizeAllocationActivity.this, ContestBasicInformationActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    Log.e("", " res Error: " + e.getMessage());
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("", " Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
////                pb_loader.setVisibility(View.GONE);
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting params to login url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("prizeAmount", prizeAmount);
//                params.put("contestFirstPlaceAmount", contestFirstPlaceAmount);
//                params.put("contestSecondPlaceAmount", contestSecondPlaceAmount);
//                params.put("contestThirdPlaceAmount", contestThirdPlaceAmount);
//                params.put("contestPlatformFee", contestPlatformFee);
//                params.put("contestVAT", contestVAT);
//                params.put("contestSum", contestSum);
//                return params;
//            }
//        };
//        // Adding request to request queue
//        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }catch (Exception e){
        Log.e("PrizeAllocationActivity", "updatePrizeContest: " + e.getMessage());
    }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
