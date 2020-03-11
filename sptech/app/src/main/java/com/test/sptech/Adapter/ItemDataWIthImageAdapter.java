package com.test.sptech.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Models.MobileDataUsageYearly;
import com.test.sptech.R;

import java.util.List;

public class ItemDataWIthImageAdapter extends RecyclerView.Adapter<ItemDataWIthImageAdapter.ViewHolder>{

    private List<MobileDataUsageYearly> yearlyList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemDataWIthImageAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_clickable_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MobileDataUsageYearly item = yearlyList.get(position);

        holder.tvYear.setText(item.getYearStr());

        // set background to yellow if any quarter in a year demonstrates a decrease in volume data

        // set the description to be visible
    }

    @Override
    public int getItemCount() {
        return yearlyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvYear, tvDescription;
        RelativeLayout rlImageBackground;


        private ViewHolder(View itemView) {
            super(itemView);

            tvYear = itemView.findViewById(R.id.tvYear);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rlImageBackground = itemView.findViewById(R.id.rlImageBackground);

        }
    }
}
