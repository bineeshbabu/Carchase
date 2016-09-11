package com.phacsin.carchase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phacsin.carchase.details.DetailActivity;

import java.util.List;

/**
 * Created by GD on 9/11/2016.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private List<CarDetails> carList;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(context, DetailActivity.class));
                }
            });
        }
    }


    public CarAdapter(List<CarDetails> carList,Context context) {
        this.carList = carList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_car, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CarDetails carDetails = carList.get(position);
        holder.name.setText(carDetails.name);
        /*String URL = partnerList.get(position);
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.imageView.setImageBitmap(bitmap);
                holder.indicatorView.setVisibility(View.GONE);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                holder.indicatorView.setVisibility(View.GONE);

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.with(context).load(URL).resize(1280,720).onlyScaleDown().into(target);*/
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
