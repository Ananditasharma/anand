package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.enuke.unicon.Class.AppSingleton;
import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.enuke.unicon.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * +
 * This activity handle the normal login with user name(email) and password
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText etEmail;
    TextView Forgot;
    TextInputEditText etPassword;
    View vEmail;
    View vPassword;
    CardView btnLogin;
    TextInputLayout tilEmail, tilPassword;
    LinearLayout llBack;
    ProgressBar pb_loader;
    TextView llcreator,tv_signup_client;
    SocialLoginActivity socialLoginActivity;

    SQLiteDatabase db_login;
    private String email, password;
    private static final String URL_FOR_LOGIN = "https://XXX.XXX.X.XX/android_login_example/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_login_screen);
        socialLoginActivity = new SocialLoginActivity();
        findViews();

        db_login = (new DataBaseClass(this)).getWritableDatabase();

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setFocus(vEmail, 1);
                } else {
                    setFocus(vEmail, 2);
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setFocus(vPassword, 1);
                } else {
                    setFocus(vPassword, 2);
                }
            }
        });

    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        Forgot = findViewById(R.id.forgot);
        etEmail = findViewById(R.id.etEmail);
        llcreator = findViewById(R.id.llcreator);
        pb_loader = findViewById(R.id.pb_loader);
        etPassword = findViewById(R.id.etPassword);
        vEmail = findViewById(R.id.vEmail);
        vPassword = findViewById(R.id.vPassword);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);
        llBack = findViewById(R.id.llBack1);
        tv_signup_client = findViewById(R.id.tv_signup_client);
        btnLogin.setOnClickListener(this);
        llBack.setOnClickListener(this);
        llcreator.setOnClickListener(this);
        tv_signup_client.setOnClickListener(this);
        Forgot.setOnClickListener(this);
    }

    public static LoginActivity newInstance() {
        LoginActivity fragment = new LoginActivity();
        return fragment;
    }

    /***
     *
     * @param v
     * @param position
     * it handle the focus of the underline view of Edit text
     */
    private void setFocus(View v, int position) {
        try {
            if (position == 1) {
                v.setPadding(0, 0, 0, 0);
                v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(2)));
            } else {
                v.setPadding(0, dpToPx(1), 0, 0);
                v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(1)));
            }
        }catch (Exception e){
            Log.e("LoginActivity", "Registration Error: " + e.getMessage());
        }
    }

    /**
     * conver
     *
     * @param dp
     * @return
     */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (isValidate()) {
                    email = etEmail.getText().toString();
                    password = etPassword.getText().toString();
                    updateLocalDataBase();
//                    loginUser(email, password);
                    Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.forgot:
                startActivity(new Intent(LoginActivity.this, Forget_pass.class));
                break;
            case R.id.llcreator:
                startActivity(new Intent(LoginActivity.this, SocialLoginActivity.class));
                break;
            case R.id.tv_signup_client:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                SocialLoginActivity.creatorSignUpCheck = false;
                break;
        }
    }

    /**
     * form validation
     * @return
     */
    private boolean isValidate() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * email validation
     * @param email
     * @return
     */
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

    /*uploading to cloud*/
    private void loginUser( final String email, final String password) {
        try {
            // Tag used to cancel the request
            String cancel_req_tag = "login";
            pb_loader.setVisibility(View.VISIBLE);
//            StringRequest strReq = new StringRequest(Request.Method.POST,
//                    URL_FOR_LOGIN, new Response.Listener<String>() {
//
//                @Override
//                public void onResponse(String response) {
//                    Log.i("", "Register Response: " + response.toString());
//                    pb_loader.setVisibility(View.GONE);
//                    try {
//                        JSONObject jObj = new JSONObject(response);
//                        boolean error = jObj.getBoolean("error");
//
//                        if (!error) {
//                            String user = jObj.getJSONObject("user").getString("name");
//                            // Launch User activity
//                            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
//                            intent.putExtra("username", user);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            String errorMsg = jObj.getString("error_msg");
//                            Toast.makeText(getApplicationContext(),
//                                    errorMsg, Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        Log.e("", "Login res Error: " + e.getMessage());
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("", "Login Error: " + error.getMessage());
//                    Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
//                    pb_loader.setVisibility(View.GONE);
//
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    // Posting params to login url
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("email", email);
//                    params.put("password", password);
//                    return params;
//                }
//            };
//            // Adding request to request queue
//            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
        }catch (Exception e){
            Log.e("LoginActivity", "loginUser: " + e.getMessage());
        }
    }

    /*updating local db*/
    private void updateLocalDataBase(){
        try {
            db_login.execSQL("INSERT INTO UserInfo (email,memberPassword) VALUES(" +
                    "'" + email + "'," +
                    "'" + password + "');");
        }catch (Exception e){
            Log.e("LoginActivity", "updateLocalDataBase: " + e.getMessage());
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
