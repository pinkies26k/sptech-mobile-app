package com.test.sptech.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Models.MobileDataUsageYearly;
import com.test.sptech.Models.QuarterDataVol;
import com.test.sptech.R;

import java.util.List;

public class ItemDataWIthImageAdapter extends RecyclerView.Adapter<ItemDataWIthImageAdapter.ViewHolder>{

    private List<MobileDataUsageYearly> yearlyList;
    private Context mContext;
    private OnItemSelectListener mListener;

    public ItemDataWIthImageAdapter(List<MobileDataUsageYearly> yearlyList, OnItemSelectListener mListener) {
        this.yearlyList = yearlyList;
        this.mListener = mListener;
    }

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
        // also change the image to double arrows
        if(item.hasDecreaseInDataVol()){
            holder.rlImageBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDisplayItemSelected(item.getQuarterList());
                }
            });

            holder.rlImageBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));
            holder.ivArrow.setImageResource(R.drawable.ic_double_arrows);

            // set the description to be visible
            holder.tvDescription.setVisibility(View.VISIBLE);
            holder.tvDescription.setText(mContext.getString(R.string.description, item.getAffectedQuarters()));


        }else{
            holder.rlImageBackground.setOnClickListener(null);
            holder.rlImageBackground.setBackgroundColor(Color.parseColor("#80000000"));
            holder.ivArrow.setImageResource(R.drawable.ic_green_arrow);
            holder.tvDescription.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return yearlyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvYear, tvDescription;
        RelativeLayout rlImageBackground;
        ImageView ivArrow;

        private ViewHolder(View itemView) {
            super(itemView);

            tvYear = itemView.findViewById(R.id.tvYear);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rlImageBackground = itemView.findViewById(R.id.rlImageBackground);
            ivArrow = itemView.findViewById(R.id.ivArrow);

            mContext = itemView.getContext();
        }
    }

    public interface OnItemSelectListener {
        void onDisplayItemSelected(List<QuarterDataVol> quarterDataVolList);
    }
}
