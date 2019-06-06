package com.shenzhen.test.fengzhaung;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhen.test.R;
import com.shenzhen.test.bean.MoviesHotBean;
import com.shenzhen.test.utils.AppInfoUtil;

import java.util.List;

public class MoviesHotAdapter extends BaseRecycleViewAdapter<MoviesHotBean> {

    private Context context;
    public final int screenWidth;
    public LinearLayout liner_content;
    public Button btn_buy;
    public int position;
    public TextView tv_movies_name;

    public MoviesHotAdapter(Context context, List<MoviesHotBean> datas) {
        super(context, datas, R.layout.movies_item);
        this.context = context;
        this.position = position;
        screenWidth = AppInfoUtil.getScreenWidth(context);

    }

    /*
     * 初始化控件*/
    @Override
    protected void findById(BaseViewHolder holder) {
        liner_content = holder.getView(R.id.liner_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (screenWidth / (2.2)), +(int) (screenWidth / (3.1)));
        liner_content.setLayoutParams(params);
        tv_movies_name = holder.getView(R.id.tv_movies_name);
        btn_buy = holder.getView(R.id.btn_buy);


        //采用了类型转化的方式查找
    }

    /*
         绑定数据
        * */
    @Override
    protected void bindViewHold(final BaseViewHolder holder, final MoviesHotBean data, final int position) {
        tv_movies_name.setText(data.getName());
        if (data.getType() == 0) {

            btn_buy.setText("购买");

        } else {
            btn_buy.setText("预售");

        }


//        liner_content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
//                onItemClickListner.onItemClickListner(liner_content, position);
//            }
//        });
//        liner_content.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                onItemLongClickListner.onItemLongClickListner(liner_content, position);
//                return true;//true 代表长按结束后短按的不接受，false 长按结束后短按也接受
//            }
//        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
                onItemClickListner.onItemClickListner(btn_buy, position);
            }
        });
        btn_buy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListner.onItemLongClickListner(btn_buy, position);
                return true;//true 代表长按结束后短按的不接受，false 长按结束后短按也接受
            }
        });
    }
}
