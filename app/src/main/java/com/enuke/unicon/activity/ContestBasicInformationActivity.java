package com.enuke.unicon.activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
import com.enuke.unicon.Class.AppSingleton;
import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import android.app.AlertDialog;
import android.graphics.Color;
import androidx.fragment.app.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class ContestBasicInformationActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String START_TIME = "00:00";
    public static final String END_TIME = "23:59";
    public static final int FROM_EVENT = 1;
    public static final int TILL_EVENT = 2;
    public static final int FROM_REVIEW = 3;
    public static final int TILL_REVIEW = 4;
    public static final int FROM_ANNOUNCEMENT = 5;


    private TextView tvTitle;
    private LinearLayout llBack;
    private TextView tvEvent;
    private TextView tvReview;
    private TextView tvAnnouncement;
    private LinearLayout llFromEvent;
    private LinearLayout llTillEvent;
    private LinearLayout llFromReview;
    private LinearLayout llTillReview;
    private LinearLayout llFromAnnouncement;
    private CardView btnNext;
    private TextInputLayout tilContactName;
    private TextInputLayout tilContact;
    private TextInputEditText etContactName;
    private TextInputEditText etContact;
    private View vContactName;
    private View vContact;
    private TextView tvFromEventValue;
    private TextView tvTillEventValue;
    private TextView tvFromReviewValue;
    private TextView tvTillReviewValue;
    private TextView tvFromAnnouncementValue;

    private String fromEventDate;
    private String tillEventDate;
    private String fromReviewDate;
    private String tillReviewDate;
    private String fromAnnouncementDate;

    private String contestContactName, contestEventPeriodFrom, contestEventPeriodTo, contestExaminationPeriodFrom, contestExaminationPeriodTo, contestPublicationDate;
    private int contestContactNo;
    SQLiteDatabase db;
    private String URL_FOR_CONTEST_INFO = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.contest_basic_info);
        setToolbar();
        findViews();
        initializeListeners();
        db = (new DataBaseClass(this)).getWritableDatabase();

    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        tvEvent = findViewById(R.id.tvEvent);
        tvReview = findViewById(R.id.tvReview);
        tvAnnouncement = findViewById(R.id.tvAnnouncement);
        llFromEvent = findViewById(R.id.llFromEvent);
        llTillEvent = findViewById(R.id.llTillEvent);
        llFromReview = findViewById(R.id.llFromReview);
        llTillReview = findViewById(R.id.llTillReview);
        llFromAnnouncement = findViewById(R.id.llFromAnnouncement);
        btnNext = findViewById(R.id.btnNext);
        tilContactName = findViewById(R.id.tilContactName);
        tilContact = findViewById(R.id.tilContact);
        etContactName = findViewById(R.id.etContactName);
        etContact = findViewById(R.id.etContact);
        vContactName = findViewById(R.id.vContactName);
        vContact = findViewById(R.id.vContact);
        tvFromEventValue = findViewById(R.id.tvFromEventValue);
        tvTillEventValue = findViewById(R.id.tvTillEventValue);
        tvFromReviewValue = findViewById(R.id.tvFromReviewValue);
        tvTillReviewValue = findViewById(R.id.tvTillReviewValue);
        tvFromAnnouncementValue = findViewById(R.id.tvFromAnnouncementValue);
        setLabelText();

        String startDate = Utils.getCurrentDateTime();
        String endDate = Utils.getCurrentDateTime();
        fromEventDate = startDate;
        fromReviewDate = startDate;
        fromAnnouncementDate = startDate;
        tillEventDate = endDate;
        tillReviewDate = endDate;
        tvFromEventValue.setText(startDate);
        tvFromReviewValue.setText(startDate);
        tvFromAnnouncementValue.setText(startDate);
        tvTillEventValue.setText(endDate);
        tvTillReviewValue.setText(endDate);
    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.contest_basic_information));
    }

    /**
     * provide labels to sections
     */
    private void setLabelText() {
        String eventText = getString(R.string.event_period);
        String eventHtml = "<font style=\"font-size:14px; color: #fff;\">" + eventText + "<span style=\"color:red;\">*</span></font>";
        tvEvent.setText(Html.fromHtml(eventHtml));

        String reviewText = getString(R.string.review_period);
        String reviewHtml = "<font style=\"font-size:14px; color: #fff;\">" + reviewText + "<span style=\"color:red;\">*</span></font>";
        tvReview.setText(Html.fromHtml(reviewHtml));

        String announcementText = getString(R.string.announcement_date);
        String announcementHtml = "<font style=\"font-size:14px; color: #fff;\">" + announcementText + "<span style=\"color:red;\">*</span></font>";
        tvAnnouncement.setText(Html.fromHtml(announcementHtml));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        llFromEvent.setOnClickListener(this);
        llTillEvent.setOnClickListener(this);
        llFromReview.setOnClickListener(this);
        llTillReview.setOnClickListener(this);
        llFromAnnouncement.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        etContactName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setFocus(vContactName, 1);
                } else {
                    setFocus(vContactName, 2);
                }
            }
        });

        etContactName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setFocus(vContact, 1);
                } else {
                    setFocus(vContact, 2);
                }
            }
        });

        etContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StringBuilder contactBuilder = new StringBuilder(s);
                if (contactBuilder.length() == 4 || s.toString().length() == 9) {
                    String last = String.valueOf(contactBuilder.charAt(s.length() - 1));

                    if (!last.equals("-")) {
                        contactBuilder.deleteCharAt(contactBuilder.length() - 1);
                        etContact.setText(contactBuilder.toString() + '-' + last);
                        etContact.setSelection(etContact.getText().length());
                    }
                }
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
            case R.id.llFromEvent:
                changeBackground(v);
                Utils.hideKeyboard(this);
                showDatePicker(tvFromEventValue, FROM_EVENT);
                break;
            case R.id.llTillEvent:
                changeBackground(v);
                Utils.hideKeyboard(this);
                showDatePicker(tvTillEventValue, TILL_EVENT);
                break;

            case R.id.llFromReview:
                changeBackground(v);
                Utils.hideKeyboard(this);
                showDatePicker(tvFromReviewValue, FROM_REVIEW);
                break;

            case R.id.llTillReview:
                changeBackground(v);
                Utils.hideKeyboard(this);
                showDatePicker(tvTillReviewValue, TILL_REVIEW);
                break;

            case R.id.llFromAnnouncement:
                changeBackground(v);
                Utils.hideKeyboard(this);
                showDatePicker(tvFromAnnouncementValue, FROM_ANNOUNCEMENT);
                break;
            case R.id.btnNext:
                Utils.hideKeyboard(this);
                updateContestBasicInfoToLDB();

//                updateContestBasicInfo();
                    startActivity(new Intent(ContestBasicInformationActivity.this,ContestMoreInformationActivity.class));
                break;
        }
    }


    /**
     * change selected view's background
     *
     * @param view
     */
    private void changeBackground(View view) {


        llFromEvent.setBackgroundResource(R.drawable.shape_with_radius_white);
        llTillEvent.setBackgroundResource(R.drawable.shape_with_radius_white);
        llFromReview.setBackgroundResource(R.drawable.shape_with_radius_white);
        llTillReview.setBackgroundResource(R.drawable.shape_with_radius_white);
        llFromAnnouncement.setBackgroundResource(R.drawable.shape_with_radius_white);
        view.setBackgroundResource(R.drawable.stroke_round_corner_fill_red);

    }

    /***
     *
     * @param v
     * @param position
     * it handle the focus of the underline view of Edit text
     */
    private void setFocus(View v, int position) {
        if (position == 1) {
            v.setPadding(0, 0, 0, 0);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(2)));
        } else {
            v.setPadding(0, dpToPx(1), 0, 0);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(1)));
        }
    }

    /**
     * convert do to px
     *
     * @param dp
     * @return
     */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    /**
     * datepicker dialog
     *
     * @param textView
     */
    private void showDatePicker(final TextView textView, final int type) {
        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {

            @Override
            public void onDatePickCompleted(  int year, int month, int day, String dateDesc) {



            }
        }).textConfirm("Done") //text of confirm button

                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size

                .colorConfirm(Color.parseColor("#BF0818"))//color of confirm button
                 .minYear(1990) //min year in loop
                .maxYear(2550) // max year in loop
                .dateChose("2013-11-11") // date chose when init popwindow
                .build();


        pickerPopWin.showPopWin(this);
    }

    /**
     * timepicker dialog
     *
     * @param date
     * @param textView
     * @param type
     */
    private void showTimePicker(final String date, final TextView textView, final int type) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String hour;
                        String min;
                        if (hourOfDay > 9) {
                            hour = String.valueOf(hourOfDay);
                        } else {
                            hour = "0" + hourOfDay;
                        }

                        if (minute > 9) {
                            min = String.valueOf(minute);
                        } else {
                            min = "0" + minute;
                        }
                        String dateTime = date + " " + hour + ":" + min;


                        switch (type) {
                            case FROM_EVENT:
                                fromEventDate = dateTime;
                                break;
                            case TILL_EVENT:
                                tillEventDate = dateTime;
                                break;
                            case FROM_REVIEW:
                                fromReviewDate = dateTime;
                                break;
                            case TILL_REVIEW:
                                tillReviewDate = dateTime;
                                break;
                            case FROM_ANNOUNCEMENT:
                                fromAnnouncementDate = dateTime;
                                break;
                            default:
                                fromEventDate = dateTime;
                        }

                        textView.setText(dateTime);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void updateContestBasicInfoToLDB(){
        try {

            contestContactName = etContactName.getText().toString();
            contestContactNo = Integer.parseInt(etContact.getText().toString());
            contestEventPeriodFrom = tvFromEventValue.getText().toString();
            contestEventPeriodTo = tvTillEventValue.getText().toString();
            contestExaminationPeriodFrom = tvFromReviewValue.getText().toString();
            contestExaminationPeriodTo = tvTillReviewValue.getText().toString();
            contestPublicationDate = tvFromAnnouncementValue.getText().toString();

            ContentValues Contest = new ContentValues();
            Contest.put("contestContactName", contestContactName);
            Contest.put("contestContactNo", contestContactNo);
            Contest.put("contestEventPeriodFrom", contestEventPeriodFrom);
            Contest.put("contestEventPeriodTo", contestEventPeriodTo);
            Contest.put("contestExaminationPeriodFrom", contestExaminationPeriodFrom);
            Contest.put("contestExaminationPeriodTo", contestExaminationPeriodTo);
            Contest.put("contestPublicationDate", contestPublicationDate);

            db.update("ClientContestInfo", Contest, "", null);
        }catch (Exception e){
            Log.e("PrizeAllocationActivity", "updateLocalDataBase: " + e.getMessage());
        }
    }

    private void updateContestBasicInfo(){
        try{
            // Tag used to cancel the request
            String cancel_req_tag = "contest";
//        pb_loader.setVisibility(View.VISIBLE);
//            StringRequest strReq = new StringRequest(Request.Method.POST,
//                    URL_FOR_CONTEST_INFO, new Response.Listener<String>() {
//
//                @Override
//                public void onResponse(String response) {
//                    Log.i("", "Response: " + response.toString());
////                pb_loader.setVisibility(View.GONE);
//                    try {
//                        JSONObject jObj = new JSONObject(response);
//                        boolean error = jObj.getBoolean("error");
//
//                        if (!error) {
//                            Intent intent = new Intent( ContestBasicInformationActivity.this, ContestMoreInformationActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            String errorMsg = jObj.getString("error_msg");
//                            Toast.makeText(getApplicationContext(),
//                                    errorMsg, Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        Log.e("", " res Error: " + e.getMessage());
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("", " Error: " + error.getMessage());
//                    Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
////                pb_loader.setVisibility(View.GONE);
//
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    // Posting params to login url
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("contestContactName", contestContactName);
//                    params.put("contestContactNo", String.valueOf(contestContactNo));
//                    params.put("contestEventPeriodFrom", contestEventPeriodFrom);
//                    params.put("contestEventPeriodTo", contestEventPeriodTo);
//                    params.put("contestExaminationPeriodFrom", contestExaminationPeriodFrom);
//                    params.put("contestExaminationPeriodTo", contestExaminationPeriodTo);
//                    params.put("contestPublicationDate", contestPublicationDate);
//                    return params;
//                }
//            };
//            // Adding request to request queue
//            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
        }catch (Exception e){
            Log.e("PrizeAllocationActivity", "updatePrizeContest: " + e.getMessage());
        }
    }

    /**
     * validate inputs
     *
     * @return
     */
  /*  private boolean isValid() {
        if (TextUtils.isEmpty(etContactName.getText())) {
            Toast.makeText(ContestBasicInformationActivity.this, getResources().getString(R.string.please_enter_contact_name), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etContact.getText())) {
            Toast.makeText(ContestBasicInformationActivity.this, getResources().getString(R.string.please_enter_contact), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etContact.getText().toString().length() < 13) {
            Toast.makeText(ContestBasicInformationActivity.this, getResources().getString(R.string.please_enter_correct_contact), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Utils.isValidDate(fromEventDate, tillEventDate)) {
            Toast.makeText(ContestBasicInformationActivity.this, getResources().getString(R.string.please_enter_correct_event_dates), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Utils.isValidDate(fromReviewDate, tillReviewDate)) {
            Toast.makeText(ContestBasicInformationActivity.this, getResources().getString(R.string.please_enter_correct_review_dates), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fromAnnouncementDate == null) {
            Toast.makeText(ContestBasicInformationActivity.this, getResources().getString(R.string.please_enter_correct_announcement_date), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }*/

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
