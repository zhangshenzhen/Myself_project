package com.shenzhen.xutilsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.com.pateo.cpsp.service.R;


/*
* ImageOptions属性

.setFadeIn(true); //淡入效果

.setCircular(true); //设置图片显示为圆形

.setSquare(true); //设置图片显示为正方形

.setCrop(true).setSize(200,200); //设置大小

.setAnimation(animation); //设置动画

.setFailureDrawable(Drawable failureDrawable); //设置加载失败的动画

.setFailureDrawableId(int failureDrawable); //以资源id设置加载失败的动画

.setLoadingDrawable(Drawable loadingDrawable); //设置加载中的动画

.setLoadingDrawableId(int loadingDrawable); //以资源id设置加载中的动画

.setIgnoreGif(false); //忽略Gif图片

.setParamsBuilder(ParamsBuilder paramsBuilder); //在网络请求中添加一些参数

.setRaduis(int raduis); //设置拐角弧度

.setUseMemCache(true); //设置使用MemCache，默认true
* */
public class ImageActivity extends AppCompatActivity {

    private ImageView imgv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        imgv = findViewById(R.id.imgv);

    }

    /*
    //bind的几个方法
    方法一：
    void bind(ImageView view, String url);
    方法二：
    void bind(ImageView view, String url, ImageOptions options);
    方法三：
    void bind(ImageView view, String url, Callback.CommonCallback<Drawable> callback);
    方法四：
    void bind(ImageView view, String url, ImageOptions options, Callback.CommonCallback<Drawable> callback);
    Callback.Cancelable loadDrawable(String url, ImageOptions options, Callback.CommonCallback<Drawable> callback);
    Callback.Cancelable loadFile(String url, ImageOptions options, Callback.CacheCallback<File> callback);
    * */
    public void ImageRadius(View view) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(120, 120)
                .setRadius(DensityUtil.dip2px(5))
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
        //http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_aa677667a4c84fab9ef8bc26e2673055.jpg
        //http://pic6.nipic.com/20100418/4581549_084724004690_2.jpg
        x.image().bind(imgv, "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_aa677667a4c84fab9ef8bc26e2673055.jpg", imageOptions);

    }

    public void ImageCircular(View view) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(120, 120)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setUseMemCache(true)
                .setCircular(true) // 设置成圆形图片
                .setFadeIn(true) //淡入效果
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .build();
        //http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_aa677667a4c84fab9ef8bc26e2673055.jpg
        //http://pic6.nipic.com/20100418/4581549_084724004690_2.jpg
        x.image().bind(imgv, "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_aa677667a4c84fab9ef8bc26e2673055.jpg", imageOptions);

    }

}
