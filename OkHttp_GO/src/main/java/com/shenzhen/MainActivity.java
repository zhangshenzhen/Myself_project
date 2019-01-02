package com.shenzhen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="数据" ;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                       Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                       //GetSuccessDialog dialog = new GetSuccessDialog(MainActivity.this);
                       // dialog.showDialog();
                            Toase(MainActivity.this);
                        }
                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /*Button 的点击事件*/
    public void LoadData(View view){
        OkGo.<String>post("http://jk.zhihuisf.com:8687/api/hybApplicationConfig/getBanner")
                .tag(this)
                .params("type","14")
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
                });
    }

    public void Toase(Context context){
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.success_dialog,null);
        toast.setView(v);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER ,0,0);
        toast.show();

      /*  boolean[] isInitView = {false};
        ViewTreeObserver viewTreeObserver = button.getViewTreeObserver();
        TreeListener treeListener = new TreeListener(isInitView,0);
        viewTreeObserver.addOnPreDrawListener(treeListener);*/

    }


    public class TreeListener implements ViewTreeObserver.OnPreDrawListener{
     private     boolean[] isInitView = {false};

        public TreeListener( boolean[] isInitView,int pssition) {
            this.pssition = pssition;
            this.isInitView = isInitView;
        }

        private   int pssition ;

        @Override
        public boolean onPreDraw() {
            return false;
        }
    }
}
