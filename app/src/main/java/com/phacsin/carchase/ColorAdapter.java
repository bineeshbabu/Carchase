package com.phacsin.carchase;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by GD on 9/15/2016.
 */
public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {

    private List<ColorDetails> colorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView color_name;
        CardView color_value;
        public MyViewHolder(View view) {
            super(view);
            color_name = (TextView) view.findViewById(R.id.color_name);
            color_value = (CardView) view.findViewById(R.id.color_btn);
        }
    }


    public ColorAdapter(List<ColorDetails> colorList) {
        this.colorList = colorList;
        Log.d("color_size",String.valueOf(colorList.size()));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ColorDetails colorDetails = colorList.get(position);
        holder.color_name.setText(colorDetails.name);
        holder.color_value.setCardBackgroundColor(Color.parseColor("#"+colorDetails.value));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }
}