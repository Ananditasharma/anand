package com.enuke.unicon.activity;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.enuke.unicon.R;

import com.enuke.unicon.fragment.priceallocationfragment;

import com.enuke.unicon.utils.Utils;


public class ChangingPriceAllocationActivity extends AppCompatActivity {


    private Fragment fragment;
    Fragment Prizeallocationfragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.empty_fragment);


        initializeFragment();

    }


    private void initializeFragment() {
        Prizeallocationfragment = priceallocationfragment.newInstance();

        openFragment(Prizeallocationfragment);


    }
    @Override
    public void onBackPressed () {
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