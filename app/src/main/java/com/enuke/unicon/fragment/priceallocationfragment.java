package com.enuke.unicon.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.enuke.unicon.utils.Utils;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.enuke.unicon.activity.ChangingPriceAllocationActivity;
import com.enuke.unicon.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class priceallocationfragment extends Fragment   implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private TextView tvTitle;
    private LinearLayout llBack;
    private SeekBar sbContestFirstPlace, sbContestSecondPlace, sbContestThirdPlace;
    private TextView tvMainPrizeWon, tvMainPrizeWonText, tvPercentFirstPlace, tvPercentSecondPlace, tvPercentThirdPlace, tvPrizeWonFirstPlace, tvPrizeWonSecondPlace, tvPrizeWonThirdPlace;
    private int mainPrize = 5000000;
    private CardView btnSave;
    private BottomSheetDialog dialog;

    public static Fragment newInstance() {
        priceallocationfragment fragment = new priceallocationfragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_changing_price_allocation, container, false);
        findViews(view);
        setToolbar(view);
        return view;
    }

    private void findViews(View view) {
        sbContestFirstPlace = view.findViewById(R.id.sbContestFirstPlace);
        sbContestSecondPlace = view.findViewById(R.id.sbContestSecondPlace);
        sbContestThirdPlace = view.findViewById(R.id.sbContestThirdPlace);
        tvPercentFirstPlace = view.findViewById(R.id.tvPercentFirstPlace);
        tvPercentSecondPlace = view.findViewById(R.id.tvPercentSecondPlace);
        tvPercentThirdPlace = view.findViewById(R.id.tvPercentThirdPlace);
        tvMainPrizeWon = view.findViewById(R.id.tvMainPrizeWon);
        tvMainPrizeWonText = view.findViewById(R.id.tvMainPrizeWonText);
        tvPrizeWonFirstPlace = view.findViewById(R.id.tvPrizeWonFirstPlace);
        tvPrizeWonSecondPlace = view.findViewById(R.id.tvPrizeWonSecondPlace);
        tvPrizeWonThirdPlace = view.findViewById(R.id.tvPrizeWonThirdPlace);
        btnSave = view.findViewById(R.id.btnSave);
        tvMainPrizeWon.setText(String.valueOf(mainPrize));

        //  llBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        sbContestFirstPlace.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        sbContestSecondPlace.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        sbContestThirdPlace.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);


    }

    private void setToolbar(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        llBack = view.findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.changing_prize_allocation));
    }

    private void setTextX(SeekBar seekBar, int progress) {
        int max = seekBar.getMax();
        int offset = seekBar.getThumbOffset();
        float percent = ((float) progress) / (float) max;
        int width = seekBar.getWidth() - 2 * offset;
        int answer;
        switch (seekBar.getId()) {

            case R.id.sbContestFirstPlace:
                answer = ((int) (width * percent + offset - tvPercentFirstPlace.getWidth() / 2));
                tvPercentFirstPlace.setX(answer);
                tvPrizeWonFirstPlace.setText((String.valueOf(mainPrize * progress / 100)));
                if (progress < 50) {
                    tvPercentFirstPlace.setText("   " + progress + "%");
                } else
                    tvPercentFirstPlace.setText(progress + "%");
                break;

            case R.id.sbContestSecondPlace:
                answer = ((int) (width * percent + offset - tvPercentSecondPlace.getWidth() / 2));
                tvPercentSecondPlace.setX(answer);
                tvPrizeWonSecondPlace.setText((String.valueOf(mainPrize * progress / 100)));
                if (progress < 50) {
                    tvPercentSecondPlace.setText("   " + progress + "%");
                } else
                    tvPercentSecondPlace.setText(progress + "%");
                break;

            case R.id.sbContestThirdPlace:
                answer = ((int) (width * percent + offset - tvPercentThirdPlace.getWidth() / 2));
                tvPercentThirdPlace.setX(answer);
                tvPrizeWonThirdPlace.setText((String.valueOf(mainPrize * progress / 100)));
                if (progress < 50) {
                    tvPercentThirdPlace.setText("   " + progress + "%");
                } else
                    tvPercentThirdPlace.setText(progress + "%");
                break;
        }


    }

    /**
     * show dialog on error
     */
    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_after_prize_allocation, null);
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

    /**
     * seekbar listener
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setTextX(seekBar, progress);
    }

    /**
     * seekbar listener
     *
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * seekbar listener
     *
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    /**
     * validating input
     *
     * @return
     */
    private boolean validate() {
        return sbContestFirstPlace.getProgress() + sbContestSecondPlace.getProgress() + sbContestThirdPlace.getProgress() == 100;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.llBack1:

                //onBackPressed();
                break;
            case R.id.btnSave:
                if (!validate()) {
                    showDialog();
                }
                break;
        }

    }
}


