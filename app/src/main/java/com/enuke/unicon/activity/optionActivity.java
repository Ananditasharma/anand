package com.enuke.unicon.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.enuke.unicon.R;
import com.enuke.unicon.fragment.creatorFragment;
import com.enuke.unicon.fragment.OptionFragment;
import com.enuke.unicon.utils.Utils;

public class optionActivity extends AppCompatActivity {


    private Fragment fragment;
    Fragment optionFragment;
    Fragment CreatorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.empty_fragment);


        initializeFragment();

    }


    private void initializeFragment() {

        optionFragment = OptionFragment.newInstance();
        openFragment(optionFragment);


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }


    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
