package com.shenzhen.test.movies_activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenzhen.test.R;
import com.shenzhen.test.StagerPhotoBean;
import com.shenzhen.test.bean.DirectBean;
import com.shenzhen.test.fengzhaung.BaseRecycleViewAdapter;
import com.shenzhen.test.fengzhaung.DirectAdapter;
import com.shenzhen.test.fengzhaung.StagePhotoAdapter;
import com.shenzhen.test.util.GridSpacingItemDecoration;
import com.shenzhen.test.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class DescriableActivity extends BaseActivity implements BaseRecycleViewAdapter.OnItemClickListner{

    public RecyclerView recy_direct,recy_stage_photo;
    public List<DirectBean> directBeanlist;
    public List<StagerPhotoBean> stagerPhotoBeanslist;
    public ImageView img_video;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_desc;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        recy_direct = findViewById(R.id.recy_direct);
        recy_direct.setFocusable(false);
        TextView tv_direct = findViewById(R.id.tv_direct);
        tv_direct.setText("导演&主演");
        //剧照
        recy_stage_photo = findViewById(R.id.recy_stage_photo);
        img_video = findViewById(R.id.img_video);

    }

    @Override
    protected void initData() {
        // recy_direct
        directBeanlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                directBeanlist.add(new DirectBean("哈利波特", "导演", ""));
            } else {
                directBeanlist.add(new DirectBean("哈利波特", "主演", ""));
            }
        }

        stagerPhotoBeanslist = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            stagerPhotoBeanslist.add(new StagerPhotoBean(""));
        }
        img_video.setOnClickListener(this);
    }

    @Override
    protected void initBindData() {
        /*横向 单行*/
//        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
//        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置Manager 和横向1行
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        //设置布局管理器
        //设置间距 方式一
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(getActivity(), 10));//5
        recy_direct.addItemDecoration(gridSpacingItemDecoration);

        recy_direct.setLayoutManager(manager);
        DirectAdapter adapter = new DirectAdapter(this, directBeanlist);
        recy_direct.setAdapter(adapter);
        adapter.setOnItemClickListner(this);//设置监听
        // adapter.setOnItemLongClickListner(this);

      //剧照
        StaggeredGridLayoutManager stagemanager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recy_stage_photo.addItemDecoration(gridSpacingItemDecoration);
        recy_stage_photo.setLayoutManager(stagemanager);
        StagePhotoAdapter stagePhotoAdapter = new StagePhotoAdapter(this, stagerPhotoBeanslist);
        recy_stage_photo.setAdapter(stagePhotoAdapter);
        stagePhotoAdapter.setOnItemClickListner(this);//设置监听

    }
    /*
    * Recyclerview条目的点击事件*/
    @Override
     public void onItemClickListner(View v, int position) {

     }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_video:
                //传递参需要改变
                startActivity(PreviewedVidoActivity.class);
        }
    }
}
