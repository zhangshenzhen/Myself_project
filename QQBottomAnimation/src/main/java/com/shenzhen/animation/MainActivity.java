package com.shenzhen.animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shenzhen.animation.QQMenu;
import com.shenzhen.animation.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout line;
    private ImageView img;
    private boolean isCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);

        final QQMenu QQMenu = (QQMenu) findViewById(R.id.avater_container);

        QQMenu.setImgages(R.drawable.skin_tab_icon_conversation_normal
                , R.drawable.skin_tab_icon_conversation_selected
                , R.drawable.rvq, R.drawable.rvr);
        QQMenu.setOnMenuClickListener(new QQMenu.OnMenuClickListener() {
            @Override
            public void onItemClick(View view) {
                transMoveAnimation(img);
                Toast.makeText(MainActivity.this, "Click "+ QQMenu.isHasClick(), Toast.LENGTH_SHORT).show();
            }
        });


         /* test mtest = new test();
          mtest.startmain();*/
    }


    private void transMoveAnimation(ImageView img) {
        AnimationSet animationSet = new AnimationSet(true);

        //第一种方式
//        TranslateAnimation translateAnimation = (TranslateAnimation)
//        AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);
//        img.startAnimation(translateAnimation);

        //第二种方式
        /**
         * TranslateAnimation第一种构造
         * @param fromXDelta X方向开始的位置，取值类型是float，单位是px像素
         * @param toXDelta X方向结束的位置，取值类型是float，单位是px像素
         * @param fromYDelta Y方向开始的位置，同上
         * @param toYDelta Y方向结束的位置，同上
         */
        TranslateAnimation translateAnimation1 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0.f,
                TranslateAnimation.ABSOLUTE, 500, TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, getWindowManager().getDefaultDisplay().getHeight()-300);
        /**
         * TranslateAnimation第二种构造 在第一种构造的基础上增加了，移动距离的取值方式，通过Type来约束
         * @param fromXType 用来约束pivotXValue的取值。取值有三种：Animation.ABSOLUTE，Animation.RELATIVE_TO_SELF，Animation.RELATIVE_TO_PARENT
         * Type：Animation.ABSOLUTE：绝对，如果设置这种类型，后面pivotXValue取值就必须是像素点；比如：在X方向上移动自己宽度的距离，fromXValue的取值是mIvTranslate.getWidth()
         *            Animation.RELATIVE_TO_SELF：相对于控件自己，设置这种类型，后面pivotXValue取值就会去拿这个取值是乘上控件本身的宽度；比如：在X方向上移动自己宽度的距离，fromXValue的取值是1.0f
         *            Animation.RELATIVE_TO_PARENT：相对于它父容器（这个父容器是指包括这个这个做动画控件的外一层控件）， 原理同上，
         * @param fromXValue 配合fromXType使用，原理在上面
         * @param toXType 原理同上
         * @param toXValue 原理同上
         * @param fromYType 原理同上
         * @param fromYValue 原理同上
         * @param toYType 原理同上
         * @param toYValue 原理同上
         */

         translateAnimation1.setDuration(3000);
        //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
         translateAnimation1.setFillAfter(true); //设置动画结束之后的状态是否是动画开始时的状态，true，表示是保持动画开始时的状态
         translateAnimation1.setFillBefore(true); //设置动画的重复模式：反转REVERSE和重新开始RESTART
         translateAnimation1.setRepeatMode(ScaleAnimation.REVERSE); //设置动画播放次数
         translateAnimation1.setRepeatCount(ScaleAnimation.INFINITE); //开始动画
        // img.startAnimation(translateAnimation1);
         translateAnimation1.setFillEnabled(true);//位移属性问题

//       img.startAnimation(animationSet);

        //旋转动画
        RotateAnimation rotate = new RotateAnimation(0f, 1080f, Animation.RELATIVE_TO_SELF,
                          //0.5f 代表中心    1.0f代表右上角
                0.6f, Animation.RELATIVE_TO_SELF, 0.6f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        //设置动画持续周期
        rotate.setDuration(3000);
        // 设置重复次数
         rotate.setRepeatCount(-1);
        // 动画执行完后是否停留在执行完的状态
         rotate.setFillAfter(true);
        // 执行前的等待时间
         rotate.setStartOffset(10);
       // img.setAnimation(rotate);
        /* 一边位移一遍旋转时 要先添加旋转动画 再添加位移动画*/
         animationSet.addAnimation(rotate);
         animationSet.addAnimation(translateAnimation1);//放到动画集合
         img.startAnimation(animationSet);
        if(isCancle){
        // translateAnimation1.cancel();
         rotate.cancel();
         translateAnimation1.cancel();
         isCancle = false;
        }else {
         isCancle = true;
        }
    }



 /*   class test implements  Runnable{
        public test(){}

        private String num;

        public test(int num){
            this.num = num+"";
        }
        public void startmain(){
        int[] nums = {11,3,0,3998,5455,1,1152,990,77,35,97,22};
        for (int i = 0; i <nums.length ; i++) {
            new Thread(new test(nums[i])).start();
           }
        }
        @Override
        public void run() {
            try {
                Thread.sleep(Integer.parseInt(num));
                System.out.println("woca ...."+ num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
