package com.shenzhen.scrollview_inner_slide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhen.scrollview_inner_slide.R;

import java.util.List;

public class BannerPager extends PagerAdapter {
    private List<Integer> viewpagerpicture;
    public BannerPager(Context context,List<Integer> viewpagerpicture) {
        this.context = context;
        this.viewpagerpicture = viewpagerpicture;
    }

    private Context context;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

         position  = position%viewpagerpicture.size();//否者会越界

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(viewpagerpicture.get(position));
        ((ViewPager) container).addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
