package com.shenzhen.test;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RegexActivity extends AppCompatActivity {

    private Button btn_time, btn_b;
    private MyTime myTime;

    // cbc 6217856100069098959
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regex);
        //  * 0次或任意次，可以理解为{0,}的简写，常见用法有：
        // 1. .* 贪婪模式，匹配优先，匹配除换行外的任意字符0次以上
        // 2. .*? 非贪婪模式，非匹配优先，匹配除换行外的任意字符0次以上
        // 3. <a href="/[^"]*" 在href="/"的双引号中间，匹配非"的字符0次以上
        // \d 代表任意数字 \\d 前面的\代表转义

      /*  String regex = "[a-z0-9]{32}";
        String str = "1b28b4da44f8492da18c2daeb926dca0";*/
        String regex = "[1][358]\\d{9}";
        String str = "15151969999";

        Log.i("TAG", "正则判定结果 ：" + str.matches(regex));
        initViewdata();
    }

    private void initViewdata() {
        btn_time = findViewById(R.id.btn_time);
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTime = new MyTime(10000+500, 1000);
                myTime.start();
            }
        });
        btn_b = findViewById(R.id.btn_b);
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTime != null) {
                    myTime.cancel();
                    myTime.onFinish();

                }
            }
        });

    }

    class MyTime extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_time.setEnabled(false);
            btn_time.setText("倒计时" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            btn_time.setEnabled(true);
            btn_time.setText("验证倒计时");

        }

    }

    public interface OnCancelListener {
        public void oncancel();
    }

}
