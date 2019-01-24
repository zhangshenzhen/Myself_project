package com.shenzhen.eventbus;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.eventbus.fragment.FirstFragment;

import org.greenrobot.eventbus.EventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



 /*
 * */

public class MainActivity extends FragmentActivity {

    Button btn;
    TextView tv;
    private FloatingActionButton fab;
    private FrameLayout fr_content;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        EventBus.getDefault().register(this);
        btn = (Button) findViewById(R.id.btn_try);
        tv = (TextView)findViewById(R.id.tv);
        fr_content = findViewById(R.id.fr_content);

        FirstFragment  firstFragment = new FirstFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();//FragmentActivity
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fr_content,firstFragment);
        transaction.commit();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(intent);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Snackbar 是对toast 的扩展品，而不是替代品;
                // Snackbar.make("","")是创建对象，setAction是设置动作
              /* Snackbar.make(v,"data deleted", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // toast("fab is clicked... Data Restored...");
                    }
                }).show();*/
            }
        });
    }

    /*
    * 事件接受者的方法参类型数要和时间发送者一致
    * */
    /*  取消传递 只有在threadMode = ThreadMode.POSTING  并且设置优先级 才可使用；
       否则 使用后该订阅方法接受不到事件
    *  EventBus.getDefault().cancelEventDelivery(event) ;
    * */
   @Subscribe(threadMode = ThreadMode.POSTING,priority = 12)
    public void Event22(com.eventbus.eventbus.EventTest event) {
        String msg = "onEventMainThread收到了消息：666" + event.getmMsg();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        EventBus.getDefault().cancelEventDelivery(event) ; //拦截了
       Log.i("TAG",msg);
    }

   @Subscribe(threadMode = ThreadMode.MAIN,priority = 11)
    public void Event33(com.eventbus.eventbus.EventTest event ) {
        String msg = "onEventMainThread收到了消息：88888888" + event.getmMsg();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Log.i("TAG",msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event44(EventTest2 event ) {
        String msg = "onEventMainThread收到了消息：333333" + event.getmMsg();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        //EventBus.getDefault().cancelEventDelivery(event) ;
        Log.i("TAG",msg);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event55(String s) {
        String msg = "onEventMainThread收到了消息：55555" ;
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        //EventBus.getDefault().cancelEventDelivery(event) ;
        Log.i("TAG",msg);
    }
 /*  public void onEventMainThread(com.eventbus.eventbus.EventTest event){
       String msg = "onEventMainThread收到了消息：666" + event.getmMsg();
       Log.d("harvic", msg);
       tv.setText(msg);
       Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
   }*/

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

   /*
   * 后调用前面post*/
    public static void postEvent(){
       EventBus.getDefault().post(new EventTestAfter("EventTest btn clicked"));
    }
}

