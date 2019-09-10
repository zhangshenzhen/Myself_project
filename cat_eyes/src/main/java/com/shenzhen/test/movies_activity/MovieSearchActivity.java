package com.shenzhen.test.movies_activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shenzhen.test.R;
import com.shenzhen.test.bean.MoviesHotBean;
import com.shenzhen.test.flowmanager.CommonAdapter;
import com.shenzhen.test.flowmanager.FlowViewHolder;
import com.shenzhen.test.layputmanager.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchActivity extends BaseActivity {

    public List<MoviesHotBean> mDatas;
    public RecyclerView rcy_flow;

    @Override
    protected int getLayoutId() {
        return R.layout.movie_search_activity;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        rcy_flow = findViewById(R.id.rcy_flow);
    }

    @Override
    protected void initData() {

        mDatas = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                mDatas.add(new MoviesHotBean("拯救电话费",i));
                mDatas.add(new MoviesHotBean("四五线上的误会额",i++));
                mDatas.add(new MoviesHotBean("天使按时吃饭的",i++));
                mDatas.add(new MoviesHotBean("担任过",i++));
                mDatas.add(new MoviesHotBean("按时打算发",i++));


            }



    }

    @Override
    protected void initBindData() {
        CommonAdapter commonAdapter =   new CommonAdapter<MoviesHotBean>(this, mDatas, R.layout.item_flow) {
            @Override
            public void convert(FlowViewHolder holder, MoviesHotBean testBean) {
                //需要点点击流式条目时再处理
                Log.d("zxt", "convert() called with: holder = [" + holder + "], testBean = [" + testBean + "]");
                  holder.setText(R.id.tv, testBean.getName() + testBean.getType());
//                holder.setOnClickListener(R.id.tv, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.e("TAG1", "onClick() called with: v = [" + v + "]");
//                    }
//                });
            }
        };
        FlowLayoutManager flowLayout = new FlowLayoutManager();
      // rcy_flow.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        rcy_flow.setLayoutManager(flowLayout);//自己写的流式布局
        rcy_flow.setAdapter(commonAdapter);

        rcy_flow.addOnScrollListener(new RecyclerView.OnScrollListener() { @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        } });

    }

    @Override
    public void onClick(View v) {

    }
}
