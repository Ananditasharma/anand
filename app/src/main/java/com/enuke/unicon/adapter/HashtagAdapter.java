package com.enuke.unicon.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.channel_creator_contest;

import java.util.ArrayList;

public class HashtagAdapter extends RecyclerView.Adapter<HashtagAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Boolean> list;
    private BookMarkListener bookMarkListener;

    public HashtagAdapter(Context context, ArrayList<Boolean> list, BookMarkListener bookMarkListener) {
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_participation1, parent, false);

        return new MyViewHolder(itemView);
    }


    /**
     * bind view with data
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final boolean currentValue = list.get(position);
        if (currentValue) {
            holder.imgBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_active));
        } else {
            holder.imgBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_inactive));
        }

        holder.imgBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookMarkListener != null) {
                    bookMarkListener.onBookmarkClick(position, !currentValue);
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBookmark = itemView.findViewById(R.id.imgBookmark);
        }
    }

    public interface BookMarkListener {
        public void onBookmarkClick(int position, boolean isSelected);
    }
}
