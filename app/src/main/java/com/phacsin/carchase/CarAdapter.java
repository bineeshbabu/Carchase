package com.phacsin.carchase;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
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
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
        ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            imageView = (ImageView) view.findViewById(R.id.car_image);
            compare_add = (Button) view.findViewById(R.id.compare_btn_add);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    imageView.buildDrawingCache();
                    Bitmap image= imageView.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable("imagebitmap", image);
                    extras.putString("name",name.getText().toString());
                    extras.putString("price",price.getText().toString());
                    extras.putString("id",carList.get(getLayoutPosition()).id);
                    intent.putExtras(extras);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                        Pair<View, String> p1 = Pair.create((View)imageView, "car_image");
                        Pair<View, String> p2 = Pair.create((View)name, "name");
                        Pair<View, String> p3 = Pair.create((View)price, "price");
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(activity, p1,p2,p3);
                        activity.startActivity(intent, options.toBundle());
                    }
                    else
                        activity.startActivity(intent);
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
        holder.name.setText(carDetails.make + " " + carDetails.name);
        holder.price.setText("₹ " + carDetails.price + " Lakhs");
        String URL = carList.get(position).image;
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.with(activity).load(URL).resize(1280,720).onlyScaleDown().into(target);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
