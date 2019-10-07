package com.enuke.unicon.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;

public class Forget_pass extends AppCompatActivity implements View.OnClickListener {
    ImageView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.forget_pass);
         findViews();

}

    private void findViews(){
        next = findViewById(R.id.iv_next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_next:

                    startActivity(new Intent(Forget_pass.this, change_notif.class));

                break;
            case R.id.llBack1:
                onBackPressed();
                break;

        }
    }
}
