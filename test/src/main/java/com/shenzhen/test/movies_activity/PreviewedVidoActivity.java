package com.shenzhen.test.movies_activity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.shenzhen.test.R;

import static com.shenzhen.test.utils.DensityUtil.dip2px;

public class PreviewedVidoActivity extends BaseActivity {

    private static final String TAG = "PreviewedVidopath";
    public VideoView videoView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_previewed_play;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        videoView = findViewById(R.id.video);
    }

    @Override
    protected void initData() {
        //网络视频
        String videoUrl2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM" + "/Camera/VID20171111120620.mp4";
        Log.d(TAG, videoUrl2);
        Uri uri = Uri.parse(videoUrl2);

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //准备完成回调
        videoView.setOnPreparedListener(new PrepareCompletionListener());
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
        videoView.setZOrderOnTop(true);
        //设置视频路径
        //videoView.setVideoURI(uri); //网络
        videoView.setVideoPath(videoUrl2);//本地
    }

    @Override
    protected void initBindData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,dip2px(getContext(),235f));
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            videoView.setLayoutParams(params);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            videoView.setLayoutParams(new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        }

    }

    class PrepareCompletionListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Toast.makeText(PreviewedVidoActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
            //开始播放视频
            videoView.start();
        }
    }

    /*
     * 播放完成的监听*/
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(PreviewedVidoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
