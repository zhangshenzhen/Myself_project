package com.shenzhen.test.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;

    public GridSpacingItemDecoration(int spanCount, int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column
      //position 第一个0  左边不需要间隔
        if(column == 0){
            outRect.top = spacing;
            outRect.right = spacing;
            outRect.bottom = spacing;
        }else{
            outRect.left = spacing;
            outRect.top = spacing;
            outRect.right = spacing;
            outRect.bottom = spacing;
        }
    }

}
