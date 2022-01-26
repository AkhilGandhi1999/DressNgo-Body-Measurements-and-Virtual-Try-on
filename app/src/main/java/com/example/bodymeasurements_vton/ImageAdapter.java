package com.example.bodymeasurements_vton;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=null;

        CardView cardview = null;
        if (convertView == null) {
            cardview=new CardView(mContext);

//cardview.setCardBackgroundColor(Color.BLUE);
            imageView = new ImageView(mContext);
       //     cardview.addView(imageView);
            cardview.setCardElevation(100);
            cardview.setPadding(8, 8, 8, 8);
            cardview.setRadius(20);
            cardview.setLayoutParams(new CardView.LayoutParams(500, 600));
            imageView.setImageResource(mThumbIds[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            cardview.addView(imageView,500,600);
           // imageView.setPadding(5,5,5,5);
           // imageView.setPadding(2, 2, 2, 2);

            CardView finalCardview = cardview;
            ImageView finalImageView = imageView;

            final Integer[] clothfile = {0};
       cardview.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    finalImageView.setPadding(8, 8, 8, 8);

                    Drawable highlight = ResourcesCompat.getDrawable(finalImageView.getResources(), R.drawable.title_logo, null);
                    Drawable highlight1 = ResourcesCompat.getDrawable(finalImageView.getResources(), R.drawable.title_logo_1, null);
                    finalImageView.setBackground(highlight);
                 //   Toast.makeText(mContext.getApplicationContext(),finalCardview.getId(),Toast.LENGTH_SHORT).show();
                }
            });

            //  imageView.setScaleType(ImageView.ScaleType.FIT_XY);
   
            
           // imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            cardview = (CardView) convertView;
        }
     //   imageView.setImageResource(mThumbIds[position]);
        //cardview.addView(imageView,400,500);
       // cardview.setImageResource(mThumbIds[position]);

        return cardview;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
           R.drawable.im1,R.drawable.im2,R.drawable.im3,R.drawable.im4,R.drawable.im6, R.drawable.im1,R.drawable.im2,R.drawable.im3,R.drawable.im4,R.drawable.im6

    };
}