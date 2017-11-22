package com.inzenjer.forthepeople.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inzenjer.forthepeople.R;
import com.inzenjer.forthepeople.models.complaints;

import java.util.List;

/**
 * Created by Ramdas on 11/8/2017.
 */

public class complaintAdapter extends RecyclerView.Adapter<complaintAdapter.MyViewHolder> {

    private List<complaints> complaint_list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, id, constituency;
        TextView tittle, complaint_des, status;

        public MyViewHolder(View view) {
            super(view);
            tittle = (TextView) view.findViewById(R.id.complaint_tittle_id);
            complaint_des = (TextView) view.findViewById(R.id.Details_id);
            status = (TextView) view.findViewById(R.id.status_id);

        }
    }


    public complaintAdapter(List<complaints> complaint_list) {
        this.complaint_list = complaint_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_list_inflator, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        complaints ml = complaint_list.get(position);
        holder.tittle.setText(ml.getName());
        holder.complaint_des.setText(ml.getComplaint_des());
        holder.status.setText(ml.getStatus());

    }

    @Override
    public int getItemCount() {
        return complaint_list.size();
    }
}
