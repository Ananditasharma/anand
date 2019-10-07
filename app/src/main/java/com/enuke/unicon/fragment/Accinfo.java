package com.enuke.unicon.fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.MyprofileActivity;

public class Accinfo extends Fragment implements View.OnClickListener {
    private TextView tvTitle;
    private LinearLayout llBack;
    private CardView addacc;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        //  boolean index = args.getBoolean("flag_client", true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.account_info, container, false);
       setToolbar(view);
       findViews(view);
        return view;
    }

    private void findViews( View view){
        addacc = view.findViewById(R.id.btnsave);
        img1=view.findViewById(R.id.del1);
        img2=view.findViewById(R.id.del2);
        img3=view.findViewById(R.id.del3);
        img4=view.findViewById(R.id.del4);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        addacc.setOnClickListener(this);

    }
    private void setToolbar(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        llBack = view.findViewById(R.id.llBack1);
        tvTitle.setText("계좌정보");
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.deleteacc, null);
        dialogBuilder.setView(dialogView);

        //Show the dialog
        final AlertDialog alert = dialogBuilder.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alert.setCancelable(false);
       TextView btncancel = dialogView.findViewById(R.id.btncancel);
        TextView btnconfirm = dialogView.findViewById(R.id.btnconfirm);


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
            }
        });
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.del1:
                showDialog();
                break;
            case R.id.del2:
                showDialog();
                break;
            case R.id.del3:
                showDialog();
                break;
            case R.id.del4:
                showDialog();
                break;
            case R.id.btnsave:
                Addaccount newsaddfragment = new Addaccount();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.rlmain, newsaddfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;



        }
    }
}
