package com.enuke.unicon.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.enuke.unicon.R;

public class Notice_fragment extends Fragment implements View.OnClickListener {

    private TextView tvTitle;
    private LinearLayout llBack;
    private LinearLayout llshare;

    public static Notice_fragment newInstance() {
     Notice_fragment fragment = new Notice_fragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.notice, container, false);


        setToolbar(view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void setToolbar(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        llBack = view.findViewById(R.id.llBack1);
        llshare = view.findViewById(R.id.llshare);
        llshare.setOnClickListener(this);
        tvTitle.setText("알림");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
               // onBackPressed();
                break;
            case R.id.llshare:
                showDialog();
                break;

        }
    }
    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_notice, null);
        dialogBuilder.setView(dialogView);

        //Show the dialog
        final AlertDialog alert = dialogBuilder.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alert.setCancelable(false);
        CardView btncancel = dialogView.findViewById(R.id.btnSelectItem);


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
            }
        });

    }
}
