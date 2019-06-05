package com.shenzhen.zlayout_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/*
*    测试。。*/

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        findViewById(R.id.btnFlow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
              //  String []  srt =  CoordinateMgr.getInstance().getCityLngAndLat(LauncherActivity.this,"阜阳");
              //  Log.d("测试结果","经度："+srt[0]+"  维度: "+srt[1]);

            }
        });

    }
}
