package com.hello.hackers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageSlider extends PagerAdapter {
    Context context;
    int[] images;
    public ImageSlider(Context context,int[] images) {
        this.context = context;
        this.images=images;
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
      LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layoutslider,container,false);
        ImageView img=view.findViewById(R.id.slider_img);
        img.setImageResource(images[position]);

        container.addView(view);


        return view;


    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

       container.removeView((LinearLayout)object);

    }
}
