package com.enuke.unicon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.enuke.unicon.R;
import com.enuke.unicon.activity.FeedActivity;
import com.enuke.unicon.fragment.ApplicationWithdrawalFragment;

import java.util.ArrayList;

public class FinancialDetailListAdapter extends RecyclerView.Adapter<FinancialDetailListAdapter.MyViewHolder> {

    private ApplicationWithdrawalFragment applicationWithdrawalFragment;
    private Context context;
    private ArrayList<String> list;

    public FinancialDetailListAdapter(Context context, ArrayList<String> list,ApplicationWithdrawalFragment applicationWithdrawalFragment) {
        this.context = context;
        this.list = list;
        this.applicationWithdrawalFragment = applicationWithdrawalFragment;

    }

//    public FinancialDetailListAdapter (ApplicationWithdrawalFragment applicationWithdrawalFragment) {
//        this.applicationWithdrawalFragment = applicationWithdrawalFragment;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.financial_detail_item, parent, false);
                return new MyViewHolder(itemView);
    }

    /**
     * bind view with data
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.cardViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*change view for financial card click*/
                if(!applicationWithdrawalFragment.financialCardCheck) {
                    holder.radioButton.setVisibility(View.GONE);
                    if (holder.ll_card_detail_view.getVisibility() != View.VISIBLE)
                        holder.ll_card_detail_view.setVisibility(View.VISIBLE);
                    else
                        holder.ll_card_detail_view.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * item count for list
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
        CardView cardViewAdapter;
        LinearLayout ll_card_detail_view;
        RadioButton radioButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewAdapter = itemView.findViewById(R.id.cardViewAdapter);
            ll_card_detail_view = itemView.findViewById(R.id.ll_card_detail_view);
            radioButton = itemView.findViewById(R.id.radioButton1);

            if(applicationWithdrawalFragment.financialCardCheck){
                ll_card_detail_view.setVisibility(View.GONE);
                radioButton.setVisibility(View.VISIBLE);
            }
        }
    }
}
