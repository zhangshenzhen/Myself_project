package com.shenzhen.test.movies_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.shenzhen.test.R;
import com.shenzhen.test.bean.MoviesHotBean;
import com.shenzhen.test.fengzhaung.BaseRecycleViewAdapter;
import com.shenzhen.test.fengzhaung.MoviesHotAdapter;
import com.shenzhen.test.movies_activity.DescriableActivity;
import com.shenzhen.test.movies_activity.SelectMovieHouseActivity;
import com.shenzhen.test.util.GridSpacingItemDecoration;
import com.shenzhen.test.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class HotShowFragment extends BaseFragment implements BaseRecycleViewAdapter.OnItemClickListner, BaseRecycleViewAdapter.OnItemLongClickListner {
    private Context context;
    public RecyclerView recy;
    public List<MoviesHotBean> list;
    public int index;

    public static HotShowFragment getInstance(int index) {
        HotShowFragment fragment = new HotShowFragment();
        fragment.index = index;
        return fragment;
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        context = getActivity();
//        view = inflater.inflate(R.layout.fragment_movie_hot,  container, false);
//        return view;
//    }

    @Override
    public int initLayout() {
        return R.layout.fragment_movie_hot;
    }

    @Override
    public void beforeInitView() {
        list = new ArrayList<>();
        if (index == 0) {
            for (int i = 0; i < 10; i++) {
                list.add(new MoviesHotBean("冲出银河系" + i, 0));
            }
        }else if(index == 1){
            for (int i = 0; i < 3; i++) {
                list.add(new MoviesHotBean("拯救宇宙" + i, 1));
            }
        }
    }

    @Override
    public void initView() {
        recy = view.findViewById(R.id.recy);

    }

    @Override
    public void initData() {

    }

    @Override
    public void bindListener() {

        /*横向 单行*/
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置Manager 和横向1行
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        //设置布局管理器
        //设置间距 方式一
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(getActivity(), 10));//5
        recy.addItemDecoration(gridSpacingItemDecoration);

        recy.setLayoutManager(manager);
        MoviesHotAdapter adapter = new MoviesHotAdapter(getActivity(), list);
        recy.setAdapter(adapter);
        adapter.setOnItemClickListner(this);//设置监听
        adapter.setOnItemLongClickListner(this);
    }

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void onItemClickListner(View v, int position) {
        switch (v.getId()) {
            case R.id.btn_buy:
                Toast.makeText(mActivity, "购买 ：" + position, Toast.LENGTH_SHORT).show();
                if (list.get(position).getType()==0){
                startActivity(new Intent(mActivity, DescriableActivity.class));
                }else {
                startActivity(new Intent(mActivity, SelectMovieHouseActivity.class));
                }
                break;

        }
    }

    @Override
    public void onItemLongClickListner(View v, int position) {

    }
}
