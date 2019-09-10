package com.shenzhen.test.movies_fragment;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.test.R;
import com.shenzhen.test.carrousel.CarrouselLayout;
import com.shenzhen.test.carrousel.OnCarrouselItemClickListener;
import com.shenzhen.test.carrousel.OnCarrouselItemSelectedListener;

public class NoneShowFragment extends BaseFragment {

    private String TAG = "NoneShowFragment";

    private int[] imageres = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4,
            R.mipmap.image5, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8, R.mipmap.image9};
    private boolean[] select;
    public CarrouselLayout carrousel;
    public int width;

    @Override
    public int initLayout() {
        return R.layout.fragment_movie_soon;
    }

    @Override
    public void beforeInitView() {
        select = new boolean[imageres.length];
    }

    @Override
    public void initView() {
        carrousel = (CarrouselLayout) view.findViewById(R.id.carrousel);
       /* for (int res : imageres) {
            addImageView(res); //添加的Imagview 可以化成一个布局addview
        }*/
        for (int i = 0; i < imageres.length; i++) {
            addImageView(imageres[i],i);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindListener() {

    }

    @Override
    public void onClickEvent(View v) {

    }

    /*addview*/
    private void addImageView(int res, int index) {


//        mSeekBarR = (SeekBar) findViewById(R.id.seekBarR);
//        mSeekBarX = (SeekBar) findViewById(R.id.seekBarX);
//        mSeekBarZ = (SeekBar) findViewById(R.id.seekBarZ);
//        mSeekBarTime = (SeekBar) findViewById(R.id.seekBarTime);
//        mCheckbox = (CheckBox) findViewById(R.id.checkbox);
//        mSwitchLeftright = (Switch) findViewById(R.id.switchLeftright);
//        mSeekBarX.setProgress(mSeekBarX.getMax() / 2);
//        mSeekBarZ.setProgress(mSeekBarZ.getMax() / 2);
//
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;

        ImageView image = new ImageView(getActivity());
        image.setImageResource(res);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.width = (int) (width / 2);
        params.height = (width / 3);
        RotateAnimation rotateAnimation = new RotateAnimation(0f,15f, Animation.RELATIVE_TO_SELF, 0f,Animation.RELATIVE_TO_SELF,1f);
        image.startAnimation(rotateAnimation);
       // image.setRotationY( 15);//绕着竖直中心线旋转
        image.setLayoutParams(params);


        carrousel.addView(image);

        carrousel.setR((float) (width / 2.5))//设置R的大小
                .setAutoRotation(false)//是否自动切换
                .setAutoRotationTime(1500);//自动切换的时间  单位毫秒
        int x = 30; //设置x 轴宽度
        float r = 1.f * x / 100* width;
        carrousel.setR(r <= 0 ? 1 : r);
        carrousel.refreshLayout();
    }

    private void addTextView(String text) {
        TextView tv = new TextView(getActivity());
        tv.setText(text);
        tv.setTextSize(50);
        carrousel.addView(tv);
    }

    @Override
    public void onResume() {
        super.onResume();
        //开启自动
        carrousel.resumeAutoRotation();
    }


    @Override
    public void onStop() {
        super.onStop();
        //停止自动
        carrousel.stopAutoRotation();
    }

    private void initLinstener() {
        carrousel.setOnCarrouselItemClickListener(new OnCarrouselItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
                carrousel.setSelectItem(position);
            }

        });
        /**
         * 选中回调
         */
        carrousel.setOnCarrouselItemSelectedListener(new OnCarrouselItemSelectedListener() {
                                                         @Override
                                                         public void selected(View view, int position) {
                                                             Log.v(TAG, "position:" + position);
//               for (int i = 0; i < carrousel.getChildCount(); i++) {
//                 ImageView img = (ImageView) carrousel.getChildAt(i);
//                 ViewGroup.LayoutParams ps = img.getLayoutParams();
//                   ps.height = width/2;
//                   ps.width = (int) (width/2.5);
//                    img.setLayoutParams(ps);
//                   }
//                ImageView image = (ImageView) view;
//                 ViewGroup.LayoutParams ps = image.getLayoutParams();
//                  ps.height = width/2;
//                  ps.width = (int) (width/2.5);
//                  image.setLayoutParams(ps);
                                                         }
                                                     }
        );
        /**
         * 设置旋转事件间隔
         */
//        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//
//                                                {
//                                                    @Override
//                                                    public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
//                                                        long time = (long) (1.0f * progress / seekBar.getMax() * 800);
//                                                        if (time <= 100) time = 100;
//                                                        carrousel.setAutoRotationTime(time);
//                                                    }
//
//                                                    @Override
//                                                    public void onStartTrackingTouch (SeekBar seekBar){
//                                                    }
//
//                                                    @Override
//                                                    public void onStopTrackingTouch (SeekBar seekBar){
//                                                    }
//                                                }
//
//        );
        /**
         * 设置子半径R
         */
//        mSeekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//
//                                             {
//                                                 @Override
//                                                 public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
//                                                     float r = 1.f * progress / seekBar.getMax() * width;
//                                                    carrousel.setR(r <= 0 ? 1 : r);
//                                                   carrousel.refreshLayout();
//                                                 }
//
//                                                 @Override
//                                                 public void onStartTrackingTouch (SeekBar seekBar){
//                                                 }
//
//                                                 @Override
//                                                 public void onStopTrackingTouch (SeekBar seekBar){
//                                                 }
//                                             }
//
//        );
        /**
         * X轴旋转
         */
//        mSeekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//
//                                             {
//                                                 @Override
//                                                 public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
//                                                     carrousel.setRotationX(progress - seekBar.getMax() / 2);
//                                                     carrousel.refreshLayout();
//                                                 }
//
//                                                 @Override
//                                                 public void onStartTrackingTouch (SeekBar seekBar){
//                                                 }
//
//                                                 @Override
//                                                 public void onStopTrackingTouch (SeekBar seekBar){
//                                                 }
//                                             }
//
//        );
        /**
         * Z轴旋转
         */
//        mSeekBarZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//
//                                             {
//                                                 @Override
//                                                 public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
//                                                     carrousel.setRotationZ(progress - seekBar.getMax() / 2);
//                                                     carrousel.refreshLayout();
//                                                 }
//
//                                                 @Override
//                                                 public void onStartTrackingTouch (SeekBar seekBar){
//
//                                                 }
//
//                                                 @Override
//                                                 public void onStopTrackingTouch (SeekBar seekBar){
//                                                 }
//                                             }
//
//        );
        /**
         * 设置是否自动旋转
         */
//        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//
//                                             {
//                                                 @Override
//                                                 public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
//                                                     carrousel.setAutoRotation(isChecked);//启动LoopViewPager自动切换
//                                                 }
//                                             }
//
//        );
        /**
         * 设置向左还是向右自动旋转
         */
//        mSwitchLeftright.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//
//                                                    {
//                                                        @Override
//                                                        public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
//                                                            carrousel.setAutoScrollDirection(isChecked ? CarrouselRotateDirection.anticlockwise
//                                                                    : CarrouselRotateDirection.clockwise);
//                                                        }
//                                                    }
//
//        );
    }

}
