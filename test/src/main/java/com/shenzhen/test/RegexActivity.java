package com.shenzhen.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class RegexActivity extends AppCompatActivity {
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

        Log.i("TAG","正则表达式结果 ："+ str.matches(regex));

    }
}
