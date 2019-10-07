package com.enuke.unicon.fragment;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enuke.unicon.R;

public class Addaccount  extends Fragment  implements View.OnClickListener {
     private CardView btn;
    private TextView tvTitle;
    private LinearLayout llBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        //  boolean index = args.getBoolean("flag_client", true);
    }
    public static Addaccount newInstance() {
        Addaccount fragment = new Addaccount();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.addaccount, container, false);

        setToolbar(view);

        findViews(view);
        return view;
    }

    private void setToolbar(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        llBack = view.findViewById(R.id.llBack1);
        tvTitle.setText("계좌추가");
    }
    private void findViews(View view){
        btn=view.findViewById(R.id.btnSave);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:

              BankverifiedFragment newsaddfragment = new BankverifiedFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.rlmainLayout, newsaddfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


        }
    }
}