package com.test.sptech.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Models.QuarterDataVol;
import com.test.sptech.R;

import java.util.List;

public class QuarterDetailsDialogAdapter extends RecyclerView.Adapter<QuarterDetailsDialogAdapter.ViewHolder>{

    private List<QuarterDataVol> quarterDataVolList;
    private Context mContext;

    public QuarterDetailsDialogAdapter(List<QuarterDataVol> quarterDataVolList) {
        this.quarterDataVolList = quarterDataVolList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quater_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final QuarterDataVol item = quarterDataVolList.get(position);

        String qDetails = item.getQuarter().concat(" : ".concat(item.getVolumeOfMobileDataStr()));
        holder.tvQuarterDetails.setText(qDetails);

        holder.tvQuarterDetails.setTextColor(item.hasDecreaseInDataVol() ?
                ContextCompat.getColor(mContext, android.R.color.holo_red_dark) :
                ContextCompat.getColor(mContext, android.R.color.black));

    }

    @Override
    public int getItemCount() {
        return quarterDataVolList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvQuarterDetails;

        public ViewHolder(View itemView) {
            super(itemView);

            tvQuarterDetails = itemView.findViewById(R.id.tvQuarterDetails);
            mContext = itemView.getContext();
        }
    }
}
