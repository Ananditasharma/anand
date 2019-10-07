package com.enuke.unicon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.ContestDetailsActivity;

import java.util.ArrayList;

public class ContestUnderJudgingAdapter extends RecyclerView.Adapter<ContestUnderJudgingAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> list;

    public ContestUnderJudgingAdapter(Context context, ArrayList<String> list) {
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
                .inflate(R.layout.contest_under_judging, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * bind view with data
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ContestDetailsActivity.class));
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

        private LinearLayout llMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            llMain = itemView.findViewById(R.id.llMain);
        }
    }
}
