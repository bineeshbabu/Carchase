package com.phacsin.carchase;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phacsin.carchase.details.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GD on 9/11/2016.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private List<CarDetails> carList;
    Activity activity;
    TextView compare_text;
    RelativeLayout compare_layout;
    List<Integer> CompareList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        Button compare_add;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            compare_add = (Button) view.findViewById(R.id.compare_btn_add);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, DetailActivity.class));
                }
            });

            compare_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!compare_add.isSelected())
                    {
                        compare_add.setSelected(true);
                        if(CompareList.isEmpty())
                        {
                            compare_layout.setVisibility(View.VISIBLE);
                            compare_text.setText("1 car added");
                            CompareList.add(getLayoutPosition());
                        }
                        else if(CompareList.size()==1)
                        {
                            compare_text.setText("2 cars added");
                            CompareList.add(getLayoutPosition());
                        }
                        else
                            compare_add.setSelected(false);
                    }
                    else
                    {
                        compare_add.setSelected(false);
                        if(CompareList.size()==2)
                        {
                            compare_text.setText("1 car added");
                            for(int i=0;i<CompareList.size();i++)
                                if(CompareList.get(i)==getLayoutPosition())
                                    CompareList.remove(i);
                        }
                        else if(CompareList.size()==1)
                        {
                            for(int i=0;i<CompareList.size();i++)
                                if(CompareList.get(i)==getLayoutPosition())
                                    CompareList.remove(i);
                            compare_layout.setVisibility(View.GONE);
                        }
                    }

                }
            });

        }
    }


    public CarAdapter(List<CarDetails> carList,Activity activity) {
        this.carList = carList;
        this.activity = activity;
        compare_layout = (RelativeLayout) activity.findViewById(R.id.compare_rellayout);
        compare_text = (TextView) activity.findViewById(R.id.compare_text);
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
