package com.enuke.unicon.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.adapter.Mypage2Adapter;

import java.util.ArrayList;

public class MyPageCreator2 extends Fragment implements View.OnClickListener {


    private Button btnContest;
    private Button btnNormal;
private ImageView Img;

    private RecyclerView recyclermypage;
    private Mypage2Adapter mypageAdapter;
    private CardView Card;
    private LinearLayout Linear2;
    private int statusBarHeight = 0;
    private int[] mImageResources = {
            R.drawable.pager_image,
            R.drawable.pager_image,
            R.drawable.pager_image,
            R.drawable.pager_image,
            R.drawable.pager_image,
    };
    private ArrayList <Boolean> videoList = new ArrayList <Boolean>() {{
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
        add(false);
    }};

    public static MyPageCreator2 newInstance() {
        MyPageCreator2 fragment = new MyPageCreator2();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Rect rectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        statusBarHeight = rectangle.top;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mypage2, container, false);

        findViews(view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /***
     * This method register all views
     *
     */
    @SuppressLint("WrongConstant")
    private void findViews(View view) {
        recyclermypage = view.findViewById(R.id.recyclermypage2);
Img = view.findViewById(R.id.imagesettings2);
        Card = view.findViewById(R.id.btnShareFeeDetails);
        Linear2 = view.findViewById(R.id.linear2);
        btnNormal=view.findViewById(R.id.btnNormal);
        btnContest=view.findViewById(R.id.btnContest);
        btnContest.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        Img.setOnClickListener(this);
        recyclermypage.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mypageAdapter = new Mypage2Adapter(getContext(), videoList, new Mypage2Adapter.BookMarkListener() {
            @Override
            public void onBookmarkClick(int position, boolean isSelected) {
                videoList.set(position, isSelected);
                mypageAdapter.notifyItemChanged(position);
            }
        });
        recyclermypage.setAdapter(mypageAdapter);

        btnContest.setBackgroundResource(R.drawable.bg_left_unselected);
        btnNormal.setBackgroundResource(R.drawable.bg_right_selected);
        btnContest.setTextColor(getResources().getColor(R.color.colorPrimary));
        btnNormal.setTextColor(getResources().getColor(R.color.white));

    }

    /**
     * change toolbar content
     */

    /**
     * initialize listeners for all clickable views
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnContest:

//                btnContest.setBackgroundResource(R.drawable.bg_left_selected);
//                btnNormal.setBackgroundResource(R.drawable.bg_right_unselected);
//                btnContest.setTextColor(getResources().getColor(R.color.white));
//                btnNormal.setTextColor(getResources().getColor(R.color.colorPrimary));

                MyPageCreator1 myPageCreator1 = new MyPageCreator1();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, myPageCreator1).addToBackStack(null).commit();

                break;
            case R.id.btnNormal:
//                btnContest.setBackgroundResource(R.drawable.bg_left_unselected);
//                btnNormal.setBackgroundResource(R.drawable.bg_right_selected);
//                btnContest.setTextColor(getResources().getColor(R.color.colorPrimary));
//                btnNormal.setTextColor(getResources().getColor(R.color.white));

//                MyPageCreator2 myPageCreator2 = new MyPageCreator2();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, myPageCreator2).addToBackStack(null).commit();

                break;

            case R.id.imagesettings2:
                SettingsFragment newsettingsfragment = new SettingsFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, newsettingsfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


        }
    }
}

