package com.enuke.unicon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.enuke.unicon.activity.optionActivity;
import com.enuke.unicon.fragment.OptionFragment;
import com.enuke.unicon.fragment.creatorFragment;
import com.enuke.unicon.R;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class creatorjoinAdapter extends RecyclerView.Adapter<creatorjoinAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Boolean> list;



    public creatorjoinAdapter(Context context, ArrayList <Boolean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.creator_join, parent, false);

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
        holder.ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context,optionActivity.class));

            }
        });
        holder.ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             context.startActivity(new Intent(context,optionActivity.class));
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

        private LinearLayout ll1;
        private LinearLayout ll2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           ll1 = itemView.findViewById(R.id.linear1);
           ll2 = itemView.findViewById(R.id.linear2);
        }
    }
}


