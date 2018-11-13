package com.shenzhen.payfor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_payfor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  NewPayData newPayData = ObjectMapperFactory.createObjectMapper().readValue(result.toString(), NewPayData.class);
                NewAlipay newAlipay = new NewAlipay(MainActivity.this, Alipay_handler);
                newAlipay.setProduct(newPayData.getResultData().getContent());
                newAlipay.startAlipay();*/

            }
        });
    }
}
