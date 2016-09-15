package com.phacsin.carchase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by GD on 9/14/2016.
 */
public class InactiveFilterAdapter extends RecyclerView.Adapter<InactiveFilterAdapter.MyViewHolder> {

    private List<String> filterList;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView filter_name;
        Button compare_add;

        public MyViewHolder(final View view) {
            super(view);
            filter_name = (TextView) view.findViewById(R.id.filter_name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(filter_name.getText().toString().equals("Price")) {
                        Intent intent = new Intent(view.getContext(), FilterPriceResultActivity.class);
                        view.getContext().startActivity(intent);
                    }
                    else if(filter_name.getText().toString().equals("Style"))
                    {
                        Intent intent = new Intent(view.getContext(), FilterStyleResultActivity.class);
                        view.getContext().startActivity(intent);
                    }
                    else if(filter_name.getText().toString().equals("Fuel"))
                    {
                        Intent intent = new Intent(view.getContext(), FilterFuelResultActivity.class);
                        view.getContext().startActivity(intent);
                    }

                }
            });
        }


    }


    public InactiveFilterAdapter(List<String> filterList) {
        this.filterList = filterList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_filter_inactive, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String name = filterList.get(position);
        holder.filter_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }
}