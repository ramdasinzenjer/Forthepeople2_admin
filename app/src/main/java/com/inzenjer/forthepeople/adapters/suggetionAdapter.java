package com.inzenjer.forthepeople.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inzenjer.forthepeople.R;
import com.inzenjer.forthepeople.models.suggetions;

import java.util.List;

/**
 * Created by SUDHEESH on 11/25/2017.
 */

public class suggetionAdapter extends RecyclerView.Adapter<suggetionAdapter.MyViewHolder> {

    private List<suggetions> suggetion_list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, id, constituency;
        TextView tittle, suggetion_des, status;

        public MyViewHolder(View view) {
            super(view);

            tittle = view.findViewById(R.id.suggetion_tittle_id);
            suggetion_des = view.findViewById(R.id.suggetion_Details_id);
            status = view.findViewById(R.id.suggetion_status_id);
        }
    }


    public suggetionAdapter(List<suggetions> suggetion_list) {
        this.suggetion_list = suggetion_list;
    }

    @Override
    public suggetionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggestion_inflator, parent, false);

        return new suggetionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(suggetionAdapter.MyViewHolder holder, int position) {
        suggetions ml = suggetion_list.get(position);

        holder.tittle.setText(ml.getName());
        holder.status.setText(ml.getStatus());
        holder.suggetion_des.setText(ml.getSuggetion_des());
    }

    @Override
    public int getItemCount() {
        return suggetion_list.size();
    }
}
