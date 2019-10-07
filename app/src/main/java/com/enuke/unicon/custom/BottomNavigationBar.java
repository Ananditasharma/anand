package com.enuke.unicon.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.enuke.unicon.R;

/**
 * this class handle the custom bottom navigation bar
 *
 */
public class BottomNavigationBar extends LinearLayout {

    private View feedIndicator, channelIndicator, alarmIndicator, myPageIndicator;
    private ImageView feedImage, channelImage, recordImage, alarmImage, myPageImage;

    public BottomNavigationBar(Context context) {
        super(context);
        initializeViews(context);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bottom_navigation, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        feedIndicator = findViewById(R.id.feedIndicator);
        channelIndicator = findViewById(R.id.channelIndicator);
        alarmIndicator = findViewById(R.id.alarmIndicator);
        myPageIndicator = findViewById(R.id.myPageIndicator);
        feedImage = findViewById(R.id.feedImage);
        channelImage = findViewById(R.id.channelImage);
        recordImage = findViewById(R.id.recordImage);
        alarmImage = findViewById(R.id.alarmImage);
        myPageImage = findViewById(R.id.myPageImage);

    }

    public View getFeedView() {
        return feedImage;
    }

    public View getChannelView() {
        return channelImage;
    }

    public View getRecordView() {
        return recordImage;
    }

    public View getAlarmView() {
        return alarmImage;
    }

    public View getMyPageView() {
        return myPageImage;
    }

    /**
     * changing menu state as selected/unselected
     *
     * @param view
     */
    public void setMenu(View view) {

        feedIndicator.setVisibility(GONE);
        channelIndicator.setVisibility(GONE);
        alarmIndicator.setVisibility(GONE);
        myPageIndicator.setVisibility(GONE);
        feedImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_feed_unselected));
        channelImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_channel_unselected));
        alarmImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_alarm_unselected));
        myPageImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_mypage_unselected));




        if (view.getId() == feedImage.getId()) {
            feedIndicator.setVisibility(VISIBLE);
            feedImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_feed_selected));
        } else if (view.getId() == channelImage.getId()) {
            channelIndicator.setVisibility(VISIBLE);
            channelImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_channel_selected));
        } else if (view.getId() == recordImage.getId()) {

        } else if (view.getId() == alarmImage.getId()) {
            alarmIndicator.setVisibility(VISIBLE);
            alarmImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_alarm_selected));
        } else if (view.getId() == myPageImage.getId()) {
            myPageIndicator.setVisibility(VISIBLE);
            myPageImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_mypage_selected));
        }
    }
}
