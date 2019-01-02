package com.shenzhen.payfor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity.okgo" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_payfor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

       /* OkGo.<String>post("http://123.57.232.188:19096/api/hybCfProject/getRecommendProject")
                .tag(this)
                .params("pkregister", "18a13afb007b4662982f3bc85702503c")//pkregister=7a30cbe6695a4e239fe727fc1241889a
                .params("type", "13")
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                      Log.d(TAG,"onSuccess = "+ response.body().toString());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                     Log.d(TAG,"onError = "+ response.body().toString());
                }
             });*/
           //测试一
           /*  UpgradeDialog dialog = new UpgradeDialog(MainActivity.this, "123.apk","123;54676;");
             dialog.showDialog();
            });*/

           //测试二
         //   PayForFinishDialog pay = new PayForFinishDialog(MainActivity.this);
          //  pay.showDialog();

            //可以弹出两个dialog;
          //  PayForFinishDialog pay2 = new PayForFinishDialog(MainActivity.this);
         //   pay2.showDialog();
          }
     });
   }
}
