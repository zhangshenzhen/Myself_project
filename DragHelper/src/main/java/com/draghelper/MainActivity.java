package com.draghelper;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.draghelper.listener.OnScrollListener;
import com.draghelper.swipe.SwipeLayout;


public class MainActivity extends Activity {

    private SwipeLayout swipeLayout;
    private SwipeLayout swipeLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeLayout1 = findViewById(R.id.main);


        swipeLayout1.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onMoveScrollListener( SwipeLayout swipeLayout) {
                swipeLayout.move(MainActivity.this);
            }
        });
     }
}
