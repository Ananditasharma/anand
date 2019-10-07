package com.enuke.unicon.fragment;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.adapter.creatorjoinAdapter;
import com.enuke.unicon.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class creatorFragment extends Fragment implements View.OnClickListener {


    private BottomSheetDialog dialog;
    private CardView optioncard;
    private android.widget.Button Button;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button BtnSelect;
    private LinearLayout LL1;
    private LinearLayout LL2;
    private RecyclerView recyclerCreatorjoin;
    private RecyclerView recyclerCreatorreview;
    private RecyclerView recyclerView;

    private LinearLayout llBack;
    private TextView tvTitle;
    private LinearLayout llshare;
    //  private creatorReviewAdapter CreatorReviewAdapter;

    private creatorjoinAdapter CreatorJoinAdapter;


    public creatorFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static creatorFragment newInstance() {
        creatorFragment fragment = new creatorFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Rect rectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO",getActivity());


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_creator, container, false);
        findViews(view);

        setToolbar(view);
        return view;
    }




    private void finish() {
    }

    /***
     * This method register all views
     *
     */
    @SuppressLint("WrongConstant")
    private void findViews(View view) {
        optioncard =view.findViewById(R.id.optioncard);
        Button1 = view.findViewById(R.id.button1);
        Button2 = view.findViewById(R.id.button2);
        Button3 = view.findViewById(R.id.button3);
        Button4 =view.findViewById(R.id.button4);
        BtnSelect =view.findViewById(R.id.btnSelect1Item);
        LL1 = view.findViewById(R.id.linear1);
        LL2 =view.findViewById(R.id.linear2);
       // llBack.setOnClickListener(this);
        //llshare.setOnClickListener(this);
        //LL1.setOnClickListener(this);
        //LL2.setOnClickListener(this);
        recyclerCreatorjoin = view.findViewById(R.id.recyclercreatorjoin);


        recyclerCreatorjoin.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ArrayList list = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
            add("10");
        }};
        CreatorJoinAdapter = new creatorjoinAdapter(getActivity(), list);
        recyclerCreatorjoin.setAdapter(CreatorJoinAdapter);


    }

    private void setToolbar(View view){
        tvTitle = view.findViewById(R.id.tvTitle);
        llBack =view.findViewById(R.id.llBack1);
        llshare =view.findViewById(R.id.llshare);
        tvTitle.setText("Contest");

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                finish();
                break;

            case R.id.linear1:
                showDialog();
                break;

            case R.id.linear2:
                showDialog();
                break;
        }


    }


    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.option, null);
        dialogBuilder.setView(dialogView);

        //Show the dialog
        final AlertDialog alert = dialogBuilder.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alert.setCancelable(false);
        TextView okTV = dialogView.findViewById(R.id.ok1);

        okTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
            }
        });
    }
}
