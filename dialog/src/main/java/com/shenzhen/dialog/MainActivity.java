package com.shenzhen.dialog;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            PayForFinishDialog dialog = new PayForFinishDialog(MainActivity.this,5);
            dialog.showDialog();
            }
        });
    }
}
