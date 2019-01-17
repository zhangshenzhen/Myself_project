package com.shenzhen.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener,PopupWindow.OnDismissListener {
    private Button button1;//单个popupwindow点击按钮

    private TextView tv2;//下拉 popupwindow点击按钮

    private ListView listview;

    private PopupWindowAdapter adapter;

    private PopupWindow typePopupWindow;

    private boolean isPopShow = false;

    List<String> list;
    private TextView tv;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.button1);
        tv2=(TextView) findViewById(R.id.tv2);
        linear = findViewById(R.id.linear);
        linear.setClickable(true);//这个要添加；linear 取代子控件的点击事件
        tv = findViewById(R.id.tv);
        listview=new ListView(this);
        listview.setOnItemClickListener(this);
        adapter = new PopupWindowAdapter(this);
        List<String> list =getList() ;
        adapter.setData(list);
        button1.setOnClickListener(new SubmitBtn1());
        //tv2的点击事件
         //  tv.setOnClickListener(new SubmitBtn2());
        //或选其一
        linear.setOnClickListener(new SubmitBtn2());
    }
    public class SubmitBtn1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            showPopupWindow(v);
        }
    }

    public class SubmitBtn2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            listview.setAdapter(adapter);
            initTypePopup(linear.getWidth());
            if(typePopupWindow != null && !isPopShow) {
                typePopupWindow.showAsDropDown(linear, 2, 0);
                isPopShow = true;
            }
        }

    }//

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.popupwindow, null);
        // 设置按钮的点击事件
        Button button = (Button) contentView.findViewById(R.id.btn_pop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button is pressed",
                        Toast.LENGTH_SHORT).show();
                onDismiss();
            }
        });
        //这里从新设置了popupWindow的width和heigth,就会以此处为标准
        PopupWindow popupWindow = new PopupWindow(contentView,
        ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            // 这里如果返回true的话，touch事件将被拦截
            // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框 ，我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_white_bg));
        //相对某个控件的位置（正左下方），无偏移
        //popupWindow.showAsDropDown(view);//相对某个控件的位置（正左下方），无偏移
        //相对某个控件的位置，有偏移;xoff表示x轴的偏移，正值表示向左，负值表示向右；yoff表示相对y轴的偏移，正值是向下，负值是向上；
        //popupWindow.showAsDropDown(View anchor, int xoff, int yoff)：
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }



    //自定义一个popupwindow;

   /*
    附加参数说明
    https://www.cnblogs.com/jzyhywxz/p/7039503.html */
    private void initTypePopup(int width) {
        typePopupWindow = new PopupWindow(listview, width,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        Drawable drawable = getResources().getDrawable(R.drawable.shape_white_bg);
        typePopupWindow.setBackgroundDrawable(drawable);
        typePopupWindow.setOutsideTouchable(true);//点击popupwindow外部消失
        typePopupWindow.setFocusable(true);//获取焦点
        typePopupWindow.setOnDismissListener(this);//加上上面两句让破popupwindow自动消失
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(MainActivity.this, "您选择了"+adapter.getItem(position), Toast.LENGTH_SHORT).show();//显示选择的内容
        tv.setText(adapter.getItem(position)+"");
        typePopupWindow.dismiss();//关闭popupwindow窗口;

    }
    //定义一个list集合
    private List<String> getList(){
        list=new ArrayList<String>();
        for(int i=0;i<300;i++){
            list.add("spinner"+i);
        }
        return list;
    }

    @Override
    public void onDismiss() {
        isPopShow = false;
    }
}
