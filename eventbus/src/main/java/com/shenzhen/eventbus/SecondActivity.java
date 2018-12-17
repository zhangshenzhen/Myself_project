package com.shenzhen.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.shenzhen.eventbus.R;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by lx on 2017/4/24.
 */

public class SecondActivity extends Activity {
    private Button btn_FirstEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn_FirstEvent = (Button) findViewById(R.id.btn_first_event);
        btn_FirstEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
           /* EventBus.getDefault().post(
                         new com.eventbus.eventbus.EventTest("EventTest btn clicked"));*/
                /*post()参数对象要和接受者参数对一直*/
             /*   EventBus.getDefault().post(
                        new EventTest2("EventTest btn clicked"));*/

                /*    EventBus.getDefault().post( new String());*/

                /*
                 * 测试先发送后接受*/
                EventBus.getDefault().post(new EventTestAfter("EventTest btn clicked"));
                new Handler().postDelayed(runnable,3000);
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SecondActivity.this,ThreadActivity.class));
        }
    };

}
