package com.enuke.unicon.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.enuke.unicon.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements View.OnClickListener {

    private Button btnContest;
    private Button btnNormal;
    private LinearLayout llLike;
    private LinearLayout llComment;
    private LinearLayout llShare;
    private LinearLayout llBookmark;
    private VideoView videoView;
    private String path = "http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4";
    private TextView tvVideoDescription;
    private ProgressBar progressBar;
    private ImageView closeNavigation;
    private LinearLayout llFacebook;
    private LinearLayout llTwitter;
    private LinearLayout llInstagram;
    private LinearLayout llStories;
    private LinearLayout llBand;
    private LinearLayout llMore;
    private LinearLayout llVideoReport;
    private BottomSheetDialog dialog;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        findViews(view);
        return view;
    }



    /***
     * This method register all views
     *
     */
    private void findViews(View view) {
        btnContest = view.findViewById(R.id.btnContest);
        btnNormal = view.findViewById(R.id.btnNormal);
        llLike = view.findViewById(R.id.llLike);
        llComment = view.findViewById(R.id.llComment);
        llShare = view.findViewById(R.id.llShare);
        llBookmark = view.findViewById(R.id.llBookmark);
        videoView = view.findViewById(R.id.videoView);
        btnContest.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        llLike.setOnClickListener(this);
        llComment.setOnClickListener(this);
        llShare.setOnClickListener(this);
        llBookmark.setOnClickListener(this);
        tvVideoDescription = view.findViewById(R.id.tvVideoDescription);
        progressBar = view.findViewById(R.id.progressbar);
        String koDes = "<font style=\"font-size:14px; color: #fff;\">이미지 설명 자리 표시 자 <span style=\"color:red;\">#해시 태그</span>는 다음과 같습니다. 비디오 설명 자리 표시 자. 해시 태그는 다음과 같습니다. 동영상 설명 <span style=\"color:red;\">#자리 표시 자</span>. <span style=\"color:red;\">#해시 태그</span>는 다음과 같습니다.</font>";
        tvVideoDescription.setText(Html.fromHtml(koDes));

        findDialogView();
    }

    /***
     * This method register all Sharing Sheet Views
     *
     */
    private void findDialogView() {
        dialog = new BottomSheetDialog(getActivity(), R.style.BottomSharingSheet);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.sharing_bottom_sheet, null);
        dialog.setContentView(dialogView);
        closeNavigation = dialogView.findViewById(R.id.imgClose);
        llFacebook = dialogView.findViewById(R.id.llFacebook);
        llTwitter = dialogView.findViewById(R.id.llemail);
        llInstagram = dialogView.findViewById(R.id.lltalk);
        llStories = dialogView.findViewById(R.id.llGoogle);
        llBand = dialogView.findViewById(R.id.llBand);
        llMore = dialogView.findViewById(R.id.llMore);
        llVideoReport = dialogView.findViewById(R.id.llVideoReport);
        closeNavigation.setOnClickListener(this);
        llFacebook.setOnClickListener(this);
        llTwitter.setOnClickListener(this);
        llInstagram.setOnClickListener(this);
        llStories.setOnClickListener(this);
        llBand.setOnClickListener(this);
        llMore.setOnClickListener(this);
        llVideoReport.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        if (videoView != null) {
            Uri uri = Uri.parse(path);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
            //Video Loop
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    videoView.start(); //need to make transition seamless.
                }
            });
            progressBar.setVisibility(View.VISIBLE);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.start();
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int arg1,
                                                       int arg2) {
                            // TODO Auto-generated method stub
                            progressBar.setVisibility(View.GONE);
                            mp.start();
                        }
                    });
                }
            });
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContest:

                btnContest.setBackgroundResource(R.drawable.bg_left_selected);
                btnNormal.setBackgroundResource(R.drawable.bg_right_unselected);
                btnContest.setTextColor(getResources().getColor(R.color.white));
                btnNormal.setTextColor(getResources().getColor(R.color.colorPrimary));

                break;
            case R.id.btnNormal:
                btnContest.setBackgroundResource(R.drawable.bg_left_unselected);
                btnNormal.setBackgroundResource(R.drawable.bg_right_selected);
                btnContest.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnNormal.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.llLike:
                Toast.makeText(getActivity(), getResources().getString(R.string.like), Toast.LENGTH_SHORT).show();
                break;
            case R.id.llComment:
                Toast.makeText(getActivity(), getResources().getString(R.string.comment), Toast.LENGTH_SHORT).show();
                break;
            case R.id.llShare:
                dialog.show();
                break;
            case R.id.llBookmark:
                Toast.makeText(getActivity(), getResources().getString(R.string.bookmark), Toast.LENGTH_SHORT).show();
                break;
            case R.id.llFacebook:
                dialog.hide();
                showDialog();
                break;
            case R.id.lltalk:
                dialog.hide();
                showDialog();
                break;
            case R.id.llGoogle:
                dialog.hide();
                showDialog();
                break;
            case R.id.llemail:
                dialog.hide();
                showDialog();
                break;
            case R.id.llBand:
                dialog.hide();
                showDialog();
                break;
            case R.id.llMore:
                dialog.hide();
                break;
            case R.id.llVideoReport:
                dialog.hide();
                break;
            case R.id.imgClose:
                dialog.hide();
                break;

        }
    }

    /**
     * function to show dialog after share
     */
    private void showDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.dialog_after_share, null);
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
