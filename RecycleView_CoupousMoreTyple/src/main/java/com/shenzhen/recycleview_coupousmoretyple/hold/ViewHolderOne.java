package com.shenzhen.recycleview_coupousmoretyple.hold;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.recycleview_coupousmoretyple.R;
import com.shenzhen.recycleview_coupousmoretyple.TypleAdapter;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewHolder;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.utils.DensityUtil;


public class ViewHolderOne extends BaseViewHolder<ResultDataBean> {
    public ViewHolderOne(@NonNull View itemView) {
        super(itemView);
    }
    @Override
    public void setUpView(Context context,final ResultDataBean modle, int position, TypleAdapter adpter) {
       final TextView tv = (TextView) getView(R.id.tv_siut_content);
       LinearLayout line_suit = (LinearLayout) getView(R.id.Line_suit);

        tv.setText(modle.getContent());

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tv.getContext(), modle.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
        //视图树监听
        boolean[] isInitView = {false};
        ViewTreeObserver viewTreeObserver = tv.getViewTreeObserver();
        TreeListener treeListener = new TreeListener(context,isInitView,tv,line_suit,position);
        viewTreeObserver.addOnPreDrawListener(treeListener);

    }

    private class TreeListener implements ViewTreeObserver.OnPreDrawListener {
        private   boolean[] isInitView = {false};
        private int position;
        private TextView tv ;
        private LinearLayout.LayoutParams params;
        private Context context;
        private LinearLayout line_suit;

        public TreeListener(Context context, boolean[] isInitView, TextView tv, LinearLayout line_suit, int position) {
            this.isInitView = isInitView;
            this.position = position;
            this.tv = tv;
            this.context = context;
            this.line_suit = line_suit;
        }

        @Override
        public boolean onPreDraw() {
            if (!isInitView[0]) {
                int lineCount = tv.getLineCount();
                Log.i("","位置 ：" + position + " 内容文本行数：" + lineCount);
                if (lineCount == 1) {
                    params = new LinearLayout.LayoutParams(DensityUtil.dip2px(context,340), DensityUtil.dip2px(context, 120));
                } else {
                    params = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 340), LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                line_suit.setLayoutParams(params); //修改优惠券的宽度，适应文本的大小
                isInitView[0] = true;

                tv.getViewTreeObserver()
                        .removeOnPreDrawListener(this);
            }
            //移除监听
            return true;
        }

    }
}
