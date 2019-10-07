package com.enuke.unicon.adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.creator_video;

import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.creator_video;
import com.enuke.unicon.fragment.MyPageCreator1;

import java.util.ArrayList;

public class MypageAdapter extends RecyclerView.Adapter<MypageAdapter.MyViewHolder> {
    private Context context;
    ArrayList<Boolean> list;
    private MypageAdapter.BookMarkListener bookMarkListener;

    public MypageAdapter(Context context, ArrayList<Boolean> list, MypageAdapter.BookMarkListener bookMarkListener) {
        this.context = context;
        this.list = list;
        this.bookMarkListener = bookMarkListener;
    }

    /**
     * holder object creation
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MypageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mypage, parent, false);

        return new MypageAdapter.MyViewHolder(itemView);
    }

    /**
     * bind view with data
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final     MypageAdapter.MyViewHolder holder, final int position) {
        final boolean currentValue = list.get(position);

            holder.imgBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.dot));


       holder.imgBookmark.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {

                   AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                   LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   View dialogView = inflater.inflate(R.layout.mypage_opt, null);
                   dialogBuilder.setView(dialogView);

                   //Show the dialog
                   final AlertDialog alert = dialogBuilder.show();
                   alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                   alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                   alert.setCancelable(true);
                   alert.getWindow().setGravity(Gravity.BOTTOM);
                   Button btn1 = dialogView.findViewById(R.id.button1);
                   Button btn2 = dialogView.findViewById(R.id.button2);
                   CardView btnok = dialogView.findViewById(R.id.btnok);
                   btn1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           context.startActivity(new Intent(context, creator_video.class));
                       }
                   });
                   btnok.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           try{
//                                       context.startActivity(new Intent(context, ActivityFragment.class));
                               alert.dismiss();
                           }catch (Exception e){
                               Log.e("","");
                           }
                       }
                   });
                   btn2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                           View dialogView = inflater.inflate(R.layout.delete_warning, null);
                           dialogBuilder.setView(dialogView);

                           //Show the dialog
                           final AlertDialog alert = dialogBuilder.show();
                           alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                           alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                           alert.setCancelable(true);

                           TextView btncancel = dialogView.findViewById(R.id.btncancel);
                           TextView btnconfirm = dialogView.findViewById(R.id.btnconfirm);

                           btncancel.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   try{
//                                       context.startActivity(new Intent(context, ActivityFragment.class));
                                       alert.dismiss();
                                   }catch (Exception e){
                                       Log.e("","");
                                   }
                               }
                           });

                           btnconfirm.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   try{
//                                       context.startActivity(new Intent(context, ActivityFragment.class));
                                       alert.dismiss();
                                   }catch (Exception e){
                                       Log.e("","");
                                   }

                               }
                           });

                       }
                   });
               }catch (Exception e){
                   Log.e("as","as" + e.getMessage());
               }
           }


       });

}



    /**
     * item count for list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * holder object for item
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBookmark;
        private RelativeLayout rl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBookmark = itemView.findViewById(R.id.imgdots);
            rl = itemView.findViewById(R.id.rl);

        }
    }

    public interface BookMarkListener {
        public void onBookmarkClick(int position, boolean isSelected);
    }
}
