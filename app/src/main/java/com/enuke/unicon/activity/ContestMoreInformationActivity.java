package com.enuke.unicon.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
import com.enuke.unicon.Class.AppSingleton;
import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.model.VideoData;
import com.enuke.unicon.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public class ContestMoreInformationActivity extends AppCompatActivity implements View.OnClickListener {

    final int VIDEO_PICKER_SELECTION_CODE = 101;
    final int IMAGE_PICKER_SELECTION_CODE_ONE = 201;
    final int IMAGE_PICKER_SELECTION_CODE_TWO = 202;
    final int IMAGE_PICKER_SELECTION_CODE_THREE = 203;
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1001;
    final int REQUEST_PERMISSION_SETTING = 2001;

    private TextView tvTitle;
    private LinearLayout llBack;
    private TextInputEditText etContestTitle;
    private View vContestTitle;
    private EditText etContestContent;
    private TextView tvContentLength;
    private TextView tvContestContent;
    private CardView cardVideoThumb;
    private ImageView imgVideoThumb;
    private LinearLayout llAddVideo;
    private ImageView imgCloseVideo;
    private CardView cardImageThumbOne;
    private ImageView imgImageThumbOne;
    private LinearLayout llAddImageOne;
    private ImageView imgCloseImageOne;
    private CardView cardImageThumbTwo;
    private ImageView imgImageThumbTwo;
    private LinearLayout llAddImageTwo;
    private ImageView imgCloseImageTwo;
    private CardView cardImageThumbThree;
    private ImageView imgImageThumbThree;
    private LinearLayout llAddImageThree;
    private ImageView imgCloseImageThree;
    private TextView tvVideoName;
    private TextView tvVideoTime;
    private TextView tvVideoSize;
    private CardView btnReset;
    private CardView btnChange;
    private CardView btnNext;

    private int selectedPermissionCode;
    private VideoData videoData;

    private String contestDetail,contestContent;
    private File guideVideo, guideImage1, guideImage2, guideImage3;
    private String guideVideoFile, guideImage1File, guideImage2File, guideImage3File;
    SQLiteDatabase db;
    private String URL_FOR_CONTEST_INFO = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeLang("KO", this);
        setContentView(R.layout.contest_more_information);
        setToolbar();
        findViews();
        initializeListeners();
        db = (new DataBaseClass(this)).getWritableDatabase();

    }

    /**
     * change toolbar content
     */
    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText(getString(R.string.contest_more_information));
    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        etContestTitle = findViewById(R.id.etContestTitle);
        vContestTitle = findViewById(R.id.vContestTitle);
        etContestContent = findViewById(R.id.etContestContent);
        tvContentLength = findViewById(R.id.tvContentLength);
        tvContestContent = findViewById(R.id.tvContestContent);
        cardVideoThumb = findViewById(R.id.cardVideoThumb);
        imgVideoThumb = findViewById(R.id.imgVideoThumb);
        llAddVideo = findViewById(R.id.llAddVideo);
        imgCloseVideo = findViewById(R.id.imgCloseVideo);
        cardImageThumbOne = findViewById(R.id.cardImageThumbOne);
        imgImageThumbOne = findViewById(R.id.imgImageThumbOne);
        llAddImageOne = findViewById(R.id.llAddImageOne);
        imgCloseImageOne = findViewById(R.id.imgCloseImageOne);
        cardImageThumbTwo = findViewById(R.id.cardImageThumbTwo);
        imgImageThumbTwo = findViewById(R.id.imgImageThumbTwo);
        llAddImageTwo = findViewById(R.id.llAddImageTwo);
        imgCloseImageTwo = findViewById(R.id.imgCloseImageTwo);
        cardImageThumbThree = findViewById(R.id.cardImageThumbThree);
        imgImageThumbThree = findViewById(R.id.imgImageThumbThree);
        llAddImageThree = findViewById(R.id.llAddImageThree);
        imgCloseImageThree = findViewById(R.id.imgCloseImageThree);
        tvVideoName = findViewById(R.id.tvVideoName);
        tvVideoTime = findViewById(R.id.tvVideoTime);
        tvVideoSize = findViewById(R.id.tvVideoSize);
        btnReset = findViewById(R.id.btnReset);
        btnChange = findViewById(R.id.btnChange);
        btnNext = findViewById(R.id.btnNext);
        setLabelText();
    }

    /**
     * provide labels to sections
     */
    private void setLabelText() {
        String contestContentText = getString(R.string.contest_content);
        String contestContentHtml = "<font style=\"font-size:14px; color: #fff;\">" + contestContentText + "<span style=\"color:red;\">*</span></font>";
        tvContestContent.setText(Html.fromHtml(contestContentHtml));
    }

    /**
     * initialize listeners for all clickable views
     */
    private void initializeListeners() {
        llBack.setOnClickListener(this);
        cardVideoThumb.setOnClickListener(this);
        cardImageThumbOne.setOnClickListener(this);
        cardImageThumbTwo.setOnClickListener(this);
        cardImageThumbThree.setOnClickListener(this);
        llAddVideo.setOnClickListener(this);
        llAddImageOne.setOnClickListener(this);
        llAddImageTwo.setOnClickListener(this);
        llAddImageThree.setOnClickListener(this);
        imgCloseVideo.setOnClickListener(this);
        imgCloseImageOne.setOnClickListener(this);
        imgCloseImageTwo.setOnClickListener(this);
        imgCloseImageThree.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        imgVideoThumb.setOnClickListener(this);

        etContestTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Utils.setFocus(ContestMoreInformationActivity.this, vContestTitle, 1);
                } else {
                    Utils.setFocus(ContestMoreInformationActivity.this, vContestTitle, 2);
                }
            }
        });

        etContestContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvContentLength.setText(s.length() + " / " + 100);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack1:
                onBackPressed();
                break;
            case R.id.cardImageThumbOne:
                showImageDialog(imgImageThumbOne.getDrawable());
                break;
            case R.id.cardImageThumbTwo:
                showImageDialog(imgImageThumbTwo.getDrawable());
                break;
            case R.id.cardImageThumbThree:
                showImageDialog(imgImageThumbThree.getDrawable());
                break;
            case R.id.llAddVideo:
                selectedPermissionCode = VIDEO_PICKER_SELECTION_CODE;
                if (checkAppPermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL)) {
                    pickVideo(selectedPermissionCode);
                } else {
                    ActivityCompat.requestPermissions(ContestMoreInformationActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
                }
                break;
            case R.id.llAddImageOne:
                selectedPermissionCode = IMAGE_PICKER_SELECTION_CODE_ONE;
                if (checkAppPermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL)) {
                    pickImage(IMAGE_PICKER_SELECTION_CODE_ONE);
                } else {
                    ActivityCompat.requestPermissions(ContestMoreInformationActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
                }
                break;
            case R.id.llAddImageTwo:
                selectedPermissionCode = IMAGE_PICKER_SELECTION_CODE_TWO;
                if (checkAppPermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL)) {
                    pickImage(IMAGE_PICKER_SELECTION_CODE_TWO);
                } else {
                    ActivityCompat.requestPermissions(ContestMoreInformationActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
                }
                break;
            case R.id.llAddImageThree:
                selectedPermissionCode = IMAGE_PICKER_SELECTION_CODE_THREE;
                if (checkAppPermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL)) {
                    pickImage(IMAGE_PICKER_SELECTION_CODE_THREE);
                } else {

                    ActivityCompat.requestPermissions(ContestMoreInformationActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
                }
                break;
            case R.id.imgCloseVideo:
                cardVideoThumb.setVisibility(View.GONE);
                llAddVideo.setVisibility(View.VISIBLE);
                imgCloseVideo.setVisibility(View.GONE);
                resetVideoData();
                break;
            case R.id.imgCloseImageOne:
                cardImageThumbOne.setVisibility(View.GONE);
                llAddImageOne.setVisibility(View.VISIBLE);
                imgCloseImageOne.setVisibility(View.GONE);
                break;
            case R.id.imgCloseImageTwo:
                cardImageThumbTwo.setVisibility(View.GONE);
                llAddImageTwo.setVisibility(View.VISIBLE);
                imgCloseImageTwo.setVisibility(View.GONE);
                break;
            case R.id.imgCloseImageThree:
                cardImageThumbThree.setVisibility(View.GONE);
                llAddImageThree.setVisibility(View.VISIBLE);
                imgCloseImageThree.setVisibility(View.GONE);
                break;
            case R.id.btnReset:
                cardVideoThumb.setVisibility(View.GONE);
                llAddVideo.setVisibility(View.VISIBLE);
                imgCloseVideo.setVisibility(View.GONE);
                resetVideoData();
                break;
            case R.id.btnChange:
                selectedPermissionCode = VIDEO_PICKER_SELECTION_CODE;
                if (checkAppPermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL)) {
                    pickVideo(selectedPermissionCode);
                } else {
                    ActivityCompat.requestPermissions(ContestMoreInformationActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
                }
                break;
            case R.id.btnNext:
                if(isValid()){
                    updateContestDetailToLDB();
//                    updateContestOnCloud();
                    startActivity(new Intent(ContestMoreInformationActivity.this,RegistrationConfirmationActivity.class));
                }

                break;
            case R.id.imgVideoThumb:
                if (imgCloseVideo.getVisibility() == View.VISIBLE) {
                    Intent mIntent = VideoPlayerActivity.getStartIntent(ContestMoreInformationActivity.this, "", videoData);
                    startActivity(mIntent);
                }
                break;
        }
    }

    /**
     * reset data for video
     */
    private void resetVideoData() {
        tvVideoName.setText(getString(R.string.default_video_name));
        tvVideoTime.setText(getString(R.string.default_video_time));
        tvVideoSize.setText(getString(R.string.default_video_size));
    }

    /**
     * function to show full image dialog
     */
    private void showImageDialog(Drawable drawable) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ContestMoreInformationActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_image_display, null);
        dialogBuilder.setView(dialogView);

        //Show the dialog
        final AlertDialog alert = dialogBuilder.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alert.setCancelable(true);
        ImageView imgFullViewClose = dialogView.findViewById(R.id.imgFullViewClose);

        imgFullViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
            }
        });

        ImageView imgFullView = dialogView.findViewById(R.id.imgFullView);
        imgFullView.setImageDrawable(drawable);
    }

    /**
     * pick image from gallery
     *
     * @param permissionCode
     */
    private void pickImage(int permissionCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, permissionCode);
    }

    /**
     * pick video from gallery
     *
     * @param permissionCode
     */
    private void pickVideo(int permissionCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/mp4");
        startActivityForResult(intent, permissionCode);
    }

    /**
     * check permission is granted or not
     *
     * @param permissionCode
     * @return
     */
    private boolean checkAppPermission(int permissionCode) {
        switch (permissionCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL:
                return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        }
        return false;
    }

    /**
     * activity result for image/video
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedMediaUri = Objects.requireNonNull(data).getData();
            switch (requestCode) {
                case VIDEO_PICKER_SELECTION_CODE:
                    videoData = getFileDetails(selectedMediaUri);
                    if (videoData == null) {
                        Toast.makeText(this, getString(R.string.video_size_limit), Toast.LENGTH_SHORT).show();
                    } else if (Double.parseDouble(videoData.getMbSize()) < 50.00) {
                        resetVideoData();
                        cardVideoThumb.setVisibility(View.VISIBLE);
                        imgCloseVideo.setVisibility(View.VISIBLE);
                        llAddVideo.setVisibility(View.GONE);
                        imgVideoThumb.setImageBitmap(videoData.getThumbnail());
                        tvVideoName.setText(videoData.getFileName());
                        tvVideoSize.setText(videoData.getMbSize() + "mb");
                        tvVideoTime.setText(videoData.getVideoTime());

                    } else {
                        Toast.makeText(this, getString(R.string.video_size_limit), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case IMAGE_PICKER_SELECTION_CODE_ONE:
                    cardImageThumbOne.setVisibility(View.VISIBLE);
                    imgCloseImageOne.setVisibility(View.VISIBLE);
                    llAddImageOne.setVisibility(View.GONE);
                    imgImageThumbOne.setImageURI(selectedMediaUri);
                    break;
                case IMAGE_PICKER_SELECTION_CODE_TWO:
                    cardImageThumbTwo.setVisibility(View.VISIBLE);
                    imgCloseImageTwo.setVisibility(View.VISIBLE);
                    llAddImageTwo.setVisibility(View.GONE);
                    imgImageThumbTwo.setImageURI(selectedMediaUri);
                    break;
                case IMAGE_PICKER_SELECTION_CODE_THREE:
                    cardImageThumbThree.setVisibility(View.VISIBLE);
                    imgCloseImageThree.setVisibility(View.VISIBLE);
                    llAddImageThree.setVisibility(View.GONE);
                    imgImageThumbThree.setImageURI(selectedMediaUri);
                    break;

            }


        }
    }

    /**
     * permission result
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL: {


                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (selectedPermissionCode == VIDEO_PICKER_SELECTION_CODE) {
                        pickVideo(selectedPermissionCode);
                    } else {
                        pickImage(selectedPermissionCode);
                    }
                } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!showRationale) {
                        gotoSetting();
                    }
                }

            }


        }
    }

    /**
     * send user to settings to enable permission
     */
    private void gotoSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ContestMoreInformationActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getString(R.string.need_storage_permission));
        builder.setMessage(getString(R.string.app_need_storage_permission));
        builder.setPositiveButton(getString(R.string.grant), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    /**
     * acccess file details from uri
     *
     * @param returnUri
     * @return
     */
    private VideoData getFileDetails(Uri returnUri) {

        String filePath = Utils.getFilePath(this, returnUri);
        DecimalFormat df = new DecimalFormat("##0.00");
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(this, returnUri);
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMillisec = Long.parseLong(time);
        Date date = new Date(timeInMillisec);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String videoTime = formatter.format(date);
        String[] separated = videoTime.split(":");
        try {
            videoTime = separated[0] + "h:" + separated[1] + "m:" + separated[2] + "s";
        } catch (Exception e) {

        }
        retriever.release();

        Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
        int nameIndex = Objects.requireNonNull(returnCursor).getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);

        returnCursor.moveToFirst();

        String fileName = returnCursor.getString(nameIndex);
        String fullPath = filePath;
        double byteSize = (double) returnCursor.getLong(sizeIndex);
        String mbSize = df.format(byteSize / 1000000);
        int kbSize = (int) (byteSize / 1000);
        Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(fullPath, MediaStore.Images.Thumbnails.MICRO_KIND);
        returnCursor.close();

        return new VideoData(returnUri, fileName, fullPath, String.valueOf(byteSize), mbSize, String.valueOf(kbSize), thumbnail, videoTime);

    }

    /**
     * validate inputs
     *
     * @return
     */
    private boolean isValid() {
        if (TextUtils.isEmpty(etContestTitle.getText())) {
            Toast.makeText(ContestMoreInformationActivity.this, getResources().getString(R.string.please_enter_contest_title), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etContestContent.getText())) {
            Toast.makeText(ContestMoreInformationActivity.this, getResources().getString(R.string.please_enter_contest_content), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void updateContestDetailToLDB(){
        try {
            contestDetail = etContestTitle.getText().toString();
            contestContent = etContestContent.getText().toString();

            ContentValues Contest = new ContentValues();
            Contest.put("contestDetail", contestDetail);
            Contest.put("contestContent", contestContent);
            Contest.put("guideVideoFile", guideVideoFile);
            Contest.put("guideImage1File", guideImage1File);
            Contest.put("guideImage2File", guideImage2File);
            Contest.put("guideImage3File", guideImage3File);
            db.update("ClientContestInfo", Contest, "", null);

        }catch (Exception e){
            Log.e("ContestMoreInfoActivity", "updateContestOnCloud: " + e.getMessage());
        }
    }

    /*updating cloud after saving into ldb*/
    private void updateContestOnCloud(){
        try{
            String cancel_req_tag = "contest";
//        pb_loader.setVisibility(View.VISIBLE);
//            StringRequest strReq = new StringRequest(Request.Method.POST,
//                    URL_FOR_CONTEST_INFO, new Response.Listener<String>() {
//
//                @Override
//                public void onResponse(String response) {
//                    Log.i("", "Response: " + response.toString());
////                pb_loader.setVisibility(View.GONE);
//                    try {
//                        JSONObject jObj = new JSONObject(response);
//                        boolean error = jObj.getBoolean("error");
//
//                        if (!error) {
//                            Intent intent = new Intent(ContestMoreInformationActivity.this, RegistrationConfirmationActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            String errorMsg = jObj.getString("error_msg");
//                            Toast.makeText(getApplicationContext(),
//                                    errorMsg, Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        Log.e("", " res Error: " + e.getMessage());
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("", " Error: " + error.getMessage());
//                    Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
////                pb_loader.setVisibility(View.GONE);
//
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    // Posting params to login url
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("contestDetail", contestDetail);
//                    params.put("contestContent", contestContent);
//                    params.put("guideVideoFile", guideVideoFile);
//                    params.put("guideImage1File", guideImage1File);
//                    params.put("guideImage2File", guideImage2File);
//                    params.put("guideImage3File", guideImage3File);
//                    return params;
//                }
//            };
//            // Adding request to request queue
//            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
        }catch(Exception e){
            Log.e("ContestMoreInfoActivity", "updateContestOnCloud: " + e.getMessage());
        }
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }

}
