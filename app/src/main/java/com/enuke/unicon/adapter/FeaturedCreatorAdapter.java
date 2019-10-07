package com.enuke.unicon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.creator_contestActivity;

import java.util.ArrayList;

public class FeaturedCreatorAdapter extends RecyclerView.Adapter<FeaturedCreatorAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> list;

    public FeaturedCreatorAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
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
                .inflate(R.layout.featured_creator_item, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * bind view with data
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull FeaturedCreatorAdapter.MyViewHolder holder, int position) {
        holder.llCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, creator_contestActivity.class));
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

        private CardView llCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            llCard = itemView.findViewById(R.id.llcard);
        }
    }
}
