package com.enuke.unicon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.enuke.unicon.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.enuke.unicon.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * +
 * This activity handle the normal login with user name(email) and password
 */
public class Logincreator extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText etEmail;
    TextView forgot;
    TextInputEditText etPassword;
    View vEmail;
    View vPassword;
    CardView btnLogin;
    TextInputLayout tilEmail, tilPassword;
    LinearLayout llBack,llsign;
    TextView llcreator;
    boolean flag_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.activity_logincreator);
        findViews();

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
        forgot = findViewById(R.id.forgot);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        vEmail = findViewById(R.id.vEmail);
        vPassword = findViewById(R.id.vPassword);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);


        llBack = findViewById(R.id.llBack1);
        forgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        llBack.setOnClickListener(this);


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
                    startActivity(new Intent(Logincreator.this, FeedActivity.class));
                }
                break;
            case R.id.forgot:
                startActivity(new Intent(Logincreator.this, Forget_pass.class));
                break;
            case R.id.llBack1:
                onBackPressed();
                break;

        }
    }

    /**
     * form validation
     * @return
     */
    private boolean isValidate() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            Toast.makeText(Logincreator.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(Logincreator.this, getResources().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(Logincreator.this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}

