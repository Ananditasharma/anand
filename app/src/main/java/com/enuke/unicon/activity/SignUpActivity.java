package com.enuke.unicon.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.enuke.unicon.Class.AppSingleton;
import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.fragment.PrivacyPolicyFragment;
import com.enuke.unicon.fragment.TermsOfUseFragment;
import com.enuke.unicon.model.VideoData;
import com.enuke.unicon.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_next,iv_upload,iv_certi_next,imgAddFile;
    TextView tv_signup_header,tv_file_name,tv_f2_terms,tv_f4_privacy;
    CardView btnNextCompanyInfo,btnContactNext,btnMemberNext;
    EditText etSignUpEmail,et_company_name,et_bis_reg_number,et_representative_name,et_contact_name,et_contact_phone,et_member_nickname,et_member_password,et_member_re_password,et_certification_number;
    LinearLayout llBack,ll_view_1_email,ll_view_2_company,ll_view_3_contact,ll_view_4_member,ll_view_5_certification;
    FrameLayout container;

    private String signUpEmail, companyName, businessRegNo, repName, regCertificate, contactName, contachPhone, memberNickName, memberPassword;
    private static final String URL_FOR_REGISTRATION = "http://52.79.128.120:8080/v1/auth/register";
    ProgressBar pb_loader;
    SQLiteDatabase db;
    String userRole = "client";
    File memberFile;
    private int selectedPermissionCode;
    final int IMAGE_PICKER_SELECTION_CODE_ONE = 201;
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1001;
    final int REQUEST_PERMISSION_SETTING = 2001;
    private VideoData videoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Utils.setTransparentStatusbar(this);
        Utils.changeLang("KO", this);

        iv_next = findViewById(R.id.iv_next);
        iv_upload = findViewById(R.id.iv_upload);
        iv_certi_next = findViewById(R.id.iv_certi_next);
        btnNextCompanyInfo = findViewById(R.id.btn_next_company_info);
        btnContactNext = findViewById(R.id.btnContactNext);
        btnMemberNext = findViewById(R.id.btnMemberNext);
        tv_signup_header = findViewById(R.id.tv_signup_header);
        et_company_name = findViewById(R.id.et_company_name);
        et_bis_reg_number = findViewById(R.id.et_bis_reg_number);
        et_representative_name = findViewById(R.id.et_representative_name);
        tv_file_name = findViewById(R.id.tv_file_name);
        imgAddFile = findViewById(R.id.imgAddFile);
        ll_view_1_email = findViewById(R.id.ll_view_1_email);
        ll_view_2_company = findViewById(R.id.ll_view_2_company);
        ll_view_3_contact = findViewById(R.id.ll_view_3_contact);
        ll_view_4_member = findViewById(R.id.ll_view_4_member);
        ll_view_5_certification = findViewById(R.id.ll_view_5_certification);
        llBack = findViewById(R.id.llBack1);
        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        et_contact_name = findViewById(R.id.et_contact_name);
        et_contact_phone = findViewById(R.id.et_contact_phone);
        et_member_re_password = findViewById(R.id.et_member_re_password);
        et_member_nickname = findViewById(R.id.et_member_nickname);
        et_member_password = findViewById(R.id.et_member_password);
        et_certification_number = findViewById(R.id.et_certification_number);
        container = findViewById(R.id.container);
        tv_f4_privacy = findViewById(R.id.tv_f4_privacy);
        tv_f2_terms = findViewById(R.id.tv_f2_terms);
        pb_loader = findViewById(R.id.pb_loader);
        iv_next.setOnClickListener(this);
        llBack.setOnClickListener(this);
        btnNextCompanyInfo.setOnClickListener(this);
        btnContactNext.setOnClickListener(this);
        btnMemberNext.setOnClickListener(this);
        iv_upload.setOnClickListener(this);
        iv_certi_next.setOnClickListener(this);
        et_company_name.setOnClickListener(this);
        et_bis_reg_number.setOnClickListener(this);
        et_representative_name.setOnClickListener(this);
        tv_f2_terms.setOnClickListener(this);
        tv_f4_privacy.setOnClickListener(this);

        db = (new DataBaseClass(this)).getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        try {
            if (view == iv_next && !etSignUpEmail.getText().toString().isEmpty()) {
                ll_view_1_email.setVisibility(View.GONE);
                if(SocialLoginActivity.creatorSignUpCheck){
                    ll_view_4_member.setVisibility(View.VISIBLE);
                }else {
                    ll_view_2_company.setVisibility(View.VISIBLE);
                }
                updateEmail();
            } else if (view == btnNextCompanyInfo) {
                ll_view_2_company.setVisibility(View.GONE);
                ll_view_3_contact.setVisibility(View.VISIBLE);
                updateCompanyInfo();
            } else if (view == btnContactNext) {
                ll_view_3_contact.setVisibility(View.GONE);
                ll_view_4_member.setVisibility(View.VISIBLE);
                updateContact();
            } else if (view == btnMemberNext) {
                updateMember();
//                if (isValidate()) {
                    if(SocialLoginActivity.creatorSignUpCheck){
                        /*change ui*/
                        ll_view_4_member.setVisibility(View.GONE);
                        ll_view_5_certification.setVisibility(View.VISIBLE);
                    }else {
                        updateLocalDataBase();
                        /*change ui*/
                        ll_view_4_member.setVisibility(View.GONE);
                        ll_view_5_certification.setVisibility(View.VISIBLE);
//                        registerUser(signUpEmail, memberPassword, regCertificate, contactName, memberNickName, userRole, contachPhone, companyName, businessRegNo, repName);
                    }
//                }
            } else if (view == iv_certi_next) {
                Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();
            }else if(view == iv_upload) {
                selectedPermissionCode = IMAGE_PICKER_SELECTION_CODE_ONE;
                if (checkAppPermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL)) {
                    pickImage(IMAGE_PICKER_SELECTION_CODE_ONE);
                } else {
                    ActivityCompat.requestPermissions(SignUpActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
                }
            } else {
//                Toast.makeText(getApplicationContext(),getString(R.string.please_enter_email), Toast.LENGTH_LONG).show();
            }
            if (view == llBack) {
                onBackPressed();
            }else if(view == tv_f2_terms){
                container.bringToFront();
                TermsOfUseFragment termsOfUseFragment = new TermsOfUseFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, termsOfUseFragment).commit();
            }else  if(view == tv_f4_privacy){
                container.bringToFront();
                PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, privacyPolicyFragment).commit();
            }
        }catch (Exception e){
            Log.e("SignUpActivity", "onClick:"+e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedMediaUri = Objects.requireNonNull(data).getData();
            switch (requestCode) {
                case IMAGE_PICKER_SELECTION_CODE_ONE:
                    videoData = getFileDetails(selectedMediaUri);
                    if (videoData == null) {
                        Toast.makeText(this, "Please Upload file", Toast.LENGTH_SHORT).show();
                    } else if (Double.parseDouble(videoData.getMbSize()) < 20.00) {
                        tv_file_name.setText(videoData.getFileName());
                        imgAddFile.setImageURI(selectedMediaUri);
                        Log.i("SignUpActivity","Filename: " + videoData.getFileName());
                        Log.i("SignUpActivity","FilePath: " + videoData.getFullPath());
                    }
                    break;
            }
        }
    }


    private void pickImage(int permissionCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, permissionCode);
    }

    private boolean checkAppPermission(int permissionCode) {
        switch (permissionCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL:
                return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        }
        return false;
    }

    private VideoData getFileDetails(Uri returnUri) {

        String filePath = Utils.getFilePath(this, returnUri);
        DecimalFormat df = new DecimalFormat("##0.00");
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(this, returnUri);
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMillisec = Long.parseLong(time);
        Date date = new Date(timeInMillisec);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String videoTime = formatter.format(date);
        String[] separated = videoTime.split(":");
        try {
            videoTime = separated[0] + "h:" + separated[1] + "m:" + separated[2] + "s";
        } catch (Exception e) {

        }
        retriever.release();

        Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
        int nameIndex = Objects.requireNonNull(returnCursor).getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);

        returnCursor.moveToFirst();

        String fileName = returnCursor.getString(nameIndex);
        String fullPath = filePath;
        double byteSize = (double) returnCursor.getLong(sizeIndex);
        String mbSize = df.format(byteSize / 1000000);
        int kbSize = (int) (byteSize / 1000);
        Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(fullPath, MediaStore.Images.Thumbnails.MICRO_KIND);
        returnCursor.close();

        return new VideoData(returnUri, fileName, fullPath, String.valueOf(byteSize), mbSize, String.valueOf(kbSize), thumbnail, videoTime);

    }
    private void gotoSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getString(R.string.need_storage_permission));
        builder.setMessage(getString(R.string.app_need_storage_permission));
        builder.setPositiveButton(getString(R.string.grant), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage(selectedPermissionCode);
                } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!showRationale) {
                        gotoSetting();
                    }
                }

            }
        }
    }


    private void updateEmail(){
        signUpEmail = etSignUpEmail.getText().toString();
    }
    private void updateCompanyInfo(){
        companyName = et_company_name.getText().toString();
        businessRegNo = et_bis_reg_number.getText().toString();
        repName =  et_representative_name.getText().toString();
    }
    private void updateContact(){
        contactName = et_contact_name.getText().toString();
        contachPhone = et_contact_phone.getText().toString();
    }
    private void updateMember(){
        memberNickName = et_member_nickname.getText().toString();
        memberPassword = et_member_password.getText().toString();
    }



    /*updating cloud / registering user*/
    private void registerUser(final String signUpEmail,  final String memberPassword, final String regCertificate,
                              final String contactName, final String memberNickName, final String userRole, final String contactPhone, final String companyName, final String  businessRegNo, final String repName) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";
        pb_loader.setVisibility(View.VISIBLE);

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL_FOR_REGISTRATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {

                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                String user = jObj.getJSONObject("user").getString("name");
                                Toast.makeText(getApplicationContext(), " " + user +", You are successfully Added!", Toast.LENGTH_SHORT).show();

                                // Launch login activity
                                Log.i("SignUpActivity", "Registration successful.");
                                pb_loader.setVisibility(View.GONE);

                                /*change ui*/
                                ll_view_4_member.setVisibility(View.GONE);
                                ll_view_5_certification.setVisibility(View.VISIBLE);

                            } else {
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                                Log.i("SignUpActivity", "Registration successful.");
                                pb_loader.setVisibility(View.GONE);

                            }
                        } catch (JSONException e) {
                            Log.e("SignUpActivity", "Registration Error JSON: " + e.getMessage());
                            pb_loader.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("SignUpActivity", "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Registration failed!", Toast.LENGTH_LONG).show();
                pb_loader.setVisibility(View.GONE);

                NetworkResponse response = error.networkResponse;
                if(response != null && response.data != null) {
                    String errorString = new String(response.data);
                    Log.i("log error", errorString);
                }

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", signUpEmail);
                params.put("password", memberPassword);
//                params.put("businessRegistrationCertificate", videoData.getFullPath());
                params.put("name", contactName);
                params.put("nickName", memberNickName);
                params.put("role", userRole);
                params.put("phoneNumber", contactPhone);
                params.put("facebook", "Sd");
                params.put("google", "sd");
                params.put("talk", "sd");
                params.put("kakao", "sd");
                params.put("companyName", companyName);
                params.put("businessRegistrationNumber", businessRegNo);
                params.put("representativeName", repName);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        memberFile = new File(videoData.getFullPath());
        smr.addFile("businessRegistrationCertificate", memberFile.getAbsolutePath());
//        smr.addMultipartParam("Regd_ID", "text/plain", "55");
//        smr.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.i("Button", "Image path - " + smr.toString());
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(smr, cancel_req_tag);

    }

    /*updating local db*/
    private void updateLocalDataBase(){
        try {
            db.execSQL("INSERT INTO UserInfo (email,password, businessRegistrationCertificate, name, nickName, role, phoneNumber, companyName, businessRegistrationNumber, representativeName) VALUES(" +
                    "'" + signUpEmail + "'," +
                    "'" + memberPassword + "'," +
                    "'" + videoData.getFullPath() + "'," +
                    "'" + contactName + "'," +
                    "'" + memberNickName + "'," +
                    "'" + userRole + "'," +
                    "'" + contachPhone + "'," +
                    "'" + companyName + "'," +
                    "'" + businessRegNo + "'," +
                    "'" + repName + "');");
        }catch (Exception e){
            Log.e("SignUpActivity", "updateLocalDataBase:"+e.getMessage());
        }
    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /*validation check need to define message*/
    private boolean isValidate() {
        if (!isEmailValid(etSignUpEmail.getText().toString())) {
            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            etSignUpEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etSignUpEmail.getText())) {
            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (TextUtils.isEmpty(et_member_password.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(et_bis_reg_number.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(et_company_name.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(et_contact_name.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(et_member_nickname.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(et_member_re_password.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(et_representative_name.getText())) {
//            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }

}