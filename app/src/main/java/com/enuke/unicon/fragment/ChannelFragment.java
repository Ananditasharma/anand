package com.enuke.unicon.fragment;
import android.content.ClipDescription;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.enuke.unicon.R;
import com.enuke.unicon.adapter.ClosedContestAdapter;
import com.enuke.unicon.adapter.ContestUnderJudgingAdapter;
import com.enuke.unicon.adapter.FeaturedCreatorAdapter;
import com.enuke.unicon.adapter.OngoingContestAdapter;
import com.enuke.unicon.adapter.RefferalClientAdapter;
import com.enuke.unicon.adapter.ViewPagerAdapter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private LinearLayout Linear;
    private Button btnMyContest;
    private Button btnFullContest;
    private ViewPager viewPager;
    private LinearLayout viewPagerCountDots;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter viewPagerAdapter;
    private View viewStatusBar;
    private RecyclerView recyclerFeaturedCreator;
    private RecyclerView recyclerRefferalClient;
    private RecyclerView recyclerOngoingContest;
    private RecyclerView recyclerContestUnderJudging;
    private RecyclerView recyclerClosedContest;

    private FeaturedCreatorAdapter featuredCreatorAdapter;
    private RefferalClientAdapter refferalClientAdapter;
    private OngoingContestAdapter ongoingContestAdapter;
    private ContestUnderJudgingAdapter contestUnderJudgingAdapter;
    private ClosedContestAdapter closedContestAdapter;

    private int statusBarHeight = 0;
    private int[] mImageResources = {
            R.drawable.pager_image,
            R.drawable.pager_image,
            R.drawable.pager_image,
            R.drawable.pager_image,
            R.drawable.pager_image,
    };

    public ChannelFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance() {
        ChannelFragment fragment = new ChannelFragment();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
      //  boolean index = args.getBoolean("flag_client", true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
        findViews(view);
        return view;
    }


    /***
     * This method register all views
     *
     */
    private void findViews(View view) {
        viewStatusBar = view.findViewById(R.id.viewStatusBar);
        viewStatusBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
        viewPager = view.findViewById(R.id.viewPager);
        viewPagerCountDots = view.findViewById(R.id.viewPagerCountDots);
        btnMyContest = view.findViewById(R.id.btnContest);
        btnFullContest = view.findViewById(R.id.btnNormal);
        recyclerFeaturedCreator = view.findViewById(R.id.recyclerFeaturedCreator);
        recyclerRefferalClient = view.findViewById(R.id.recyclerRefferalClient);
        recyclerOngoingContest = view.findViewById(R.id.recyclerOngoingContest);
        recyclerContestUnderJudging = view.findViewById(R.id.recyclerContestUnderJudging);
        recyclerClosedContest = view.findViewById(R.id.recyclerClosedContest);
         Linear = view.findViewById(R.id.ll1);
        viewPagerAdapter = new ViewPagerAdapter(getActivity(), mImageResources);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
        setUiPageViewController();

        recyclerFeaturedCreator.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerRefferalClient.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerOngoingContest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerContestUnderJudging.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerClosedContest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

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


        featuredCreatorAdapter = new FeaturedCreatorAdapter(getActivity(), list);
        recyclerFeaturedCreator.setAdapter(featuredCreatorAdapter);

        refferalClientAdapter = new RefferalClientAdapter(getActivity(), list);
        recyclerRefferalClient.setAdapter(refferalClientAdapter);

        ongoingContestAdapter = new OngoingContestAdapter(getActivity(), list);
        recyclerOngoingContest.setAdapter(ongoingContestAdapter);

        contestUnderJudgingAdapter = new ContestUnderJudgingAdapter(getActivity(), list);
        recyclerContestUnderJudging.setAdapter(contestUnderJudgingAdapter);

        closedContestAdapter = new ClosedContestAdapter(getActivity(), list);
        recyclerClosedContest.setAdapter(closedContestAdapter);

        btnMyContest.setOnClickListener(this);
        btnFullContest.setOnClickListener(this);

        myContestSelected();
    }



    /**
     * handle dots on pager item selection
     */
    private void setUiPageViewController() {

        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int count = 0; count < dotsCount; count++) {
            dots[count] = new ImageView(getActivity());
            dots[count].setImageDrawable(getResources().getDrawable(R.drawable.ic_circle_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            viewPagerCountDots.addView(dots[count], params);
        }


        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.ic_circle_selected));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int count = 0; count < dotsCount; count++) {
            dots[count].setImageDrawable(getResources().getDrawable(R.drawable.ic_circle_unselected));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.ic_circle_selected));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContest:
                myContestSelected();
                break;

            case R.id.btnNormal:
                fullContestSelected();
                break;
        }
    }

    /**
     * function to select myContest
     */
    private void myContestSelected(){
        Linear.setVisibility(View.GONE);
        btnMyContest.setText(getString(R.string.my_contest));
        btnFullContest.setText(getString(R.string.full_contest));
        btnMyContest.setBackgroundResource(R.drawable.bg_left_selected);
        btnFullContest.setBackgroundResource(R.drawable.bg_right_unselected);
        btnMyContest.setTextColor(getResources().getColor(R.color.white));
        btnFullContest.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    /**
     * function to select fullContest
     */
    private void fullContestSelected(){
        Linear.setVisibility(View.VISIBLE);
        btnMyContest.setBackgroundResource(R.drawable.bg_left_unselected);
        btnFullContest.setBackgroundResource(R.drawable.bg_right_selected);
        btnMyContest.setTextColor(getResources().getColor(R.color.colorPrimary));
        btnFullContest.setTextColor(getResources().getColor(R.color.white));
    }

}