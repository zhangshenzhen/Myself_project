package com.shenzhen.getadressbyjingweidu;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView tv_adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         tv_adress = findViewById(R.id.tv_adress);

        /*Android基础之AsyncTask的doInBackground方法参数详解
        //https://blog.csdn.net/lxg2015/article/details/52143987
        * */
          MyAsyncExtue myAsyncExtue = new MyAsyncExtue();
          myAsyncExtue.execute();


    }
    /*根据经纬度异步获取位置此信息
     * */
     double latitude = 31.666;
     double longitude = 118.888;
    private class MyAsyncExtue extends AsyncTask<Void ,Void, String> {
        @Override
        protected String doInBackground(Void... locations) {
            String baidu = "http://api.map.baidu.com/geocoder?output=json&location="
                    +latitude+","+longitude+
                    "&ak=esNPFDwwsXWtsQfw4NMNmur1";
            HttpClient client = new DefaultHttpClient();
            StringBuilder stringBuilder = new StringBuilder();
            HttpGet httpGet = new HttpGet(baidu);

            try {
                HttpResponse response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String b;
                while ((b = bufferedReader.readLine()) != null){
                    stringBuilder.append(b+"\n");
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("str", s.toString());
            tv_adress.setText(s.toString());
            getCityName(s.toString());
        }
    }

    private void getCityName(String s) {
        try {
            JSONObject object = new JSONObject(s);
            String address = object.optString("result");
            JSONObject objdress = new JSONObject(address);
            String objcity = objdress.optString("addressComponent");
            JSONObject city = new JSONObject(objcity);
            Log.e("str城市", city.optString("city"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
