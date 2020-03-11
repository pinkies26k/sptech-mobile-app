package com.test.sptech.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Models.MobileDataUsageYearly;
import com.test.sptech.R;

import java.util.List;

public class ItemDataAdapter extends RecyclerView.Adapter<ItemDataAdapter.ViewHolder>{

    private List<MobileDataUsageYearly> yearlyList;

    public ItemDataAdapter(List<MobileDataUsageYearly> yearlyList) {
        this.yearlyList = yearlyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yearly_data_usage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MobileDataUsageYearly item = yearlyList.get(position);

        holder.tvYear.setText(item.getYearStr());
        holder.tvDataUsage.setText(item.getVolumeOfMobileDataStr());
    }

    @Override
    public int getItemCount() {
        return yearlyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvYear, tvDataUsage;


        public ViewHolder(View itemView) {
            super(itemView);

            tvYear = itemView.findViewById(R.id.tvYear);
            tvDataUsage = itemView.findViewById(R.id.tvDataUsage);

        }
    }
}
