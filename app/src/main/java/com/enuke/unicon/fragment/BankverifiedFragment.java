package com.enuke.unicon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.MyprofileActivity;

public class BankverifiedFragment extends Fragment implements View.OnClickListener {
private CardView btnacc;
private CardView btn;
private LinearLayout ll;
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
        View view = inflater.inflate(R.layout.bankaccverified, container, false);
        findViews(view);
        return view;
    }
    private void findViews(View view){
        btnacc=view.findViewById(R.id.btnaccfurther);
        btn = view.findViewById(R.id.btn);
        btnacc.setOnClickListener(this);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.btnaccfurther:
                Addaccount newsaddfragment = new Addaccount();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.rlmainLayout, newsaddfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;


            case R.id.btn:
                Intent myIntent1 = new Intent(getActivity(), MyprofileActivity.class);
                getActivity().startActivity(myIntent1);
                break;


        }
    }
}
