package com.shenzhen.eventbus;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



 /*
 * */

public class ThreadActivity extends Activity {

    Button btn;
    TextView tv;
    private FloatingActionButton fab;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        //注册EventBus
       // EventBus.getDefault().register(this);
       // MainActivity.postEvent();

    }

    /*
    * 事件接受者的方法参类型数要和时间发送者一致
    * */
    /*  取消传递 只有在threadMode = ThreadMode.POSTING 才可使用；否则 使用后该订阅方法接受不到时间
    *   EventBus.getDefault().cancelEventDelivery(event) ;
    * */
   @Subscribe(threadMode = ThreadMode.POSTING,priority = 10)
    public void Event22(com.eventbus.eventbus.EventTest event) {
        String msg = "onEventMainThread收到了消息：666" + event.getmMsg();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
       // EventBus.getDefault().cancelEventDelivery(event) ; //拦截了
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

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Event66(EventTestAfter after) {
        String msg = "onEventMainThread收到了消息：9999999" ;
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
        /*取消注册
        * */
        EventBus.getDefault().unregister(this);
    }
}

