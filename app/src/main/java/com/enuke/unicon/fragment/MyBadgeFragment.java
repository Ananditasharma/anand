package com.enuke.unicon.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.enuke.unicon.R;

public class MyBadgeFragment extends Fragment implements View.OnClickListener {
    LinearLayout llBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_badge, container, false);
        llBack = v.findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view == llBack){
            ApplicationWithdrawalFragment applicationWithdrawalFragment = new ApplicationWithdrawalFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, applicationWithdrawalFragment).addToBackStack(null).commit();
        }
    }
}
