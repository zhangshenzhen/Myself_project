package com.draghelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.draghelper.R;
import com.draghelper.bean.Item_Bean;
import com.draghelper.listener.OnQQDragListener;
import com.draghelper.onSwipeListener;
import com.draghelper.swipe.QSwipeLayout;
import com.draghelper.swipe.SwipeLayout;

import java.util.List;

public class MyDragHelperAdapter  extends BaseAdapter{
    private Context context;
    private List<Item_Bean> beanList;
    private LayoutInflater inflater;
    private int curent =-1;

    public MyDragHelperAdapter(Context context, List<Item_Bean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public int getCount() {
        return beanList.size()>0 ? beanList.size():0;
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View covertView, ViewGroup viewGroup) {
      ViewHolder holder = null;
      if(covertView == null){
          holder = new ViewHolder();
          covertView = View.inflate(context, R.layout.darghelper_item,null);
          holder.QswipeLayout =(QSwipeLayout) covertView.findViewById(R.id.line);
          holder.imageView=(ImageView)covertView.findViewById(R.id.image);
          holder.type=(TextView)covertView.findViewById(R.id.type);
          holder.content=(TextView)covertView.findViewById(R.id.content);
          holder.time=(TextView)covertView.findViewById(R.id.time);
          holder.zhhiding=(TextView)covertView.findViewById(R.id.zhiding);
          holder.biaojiweiyidu=(TextView)covertView.findViewById(R.id.biaojiweiyidu);
          holder.delete=(TextView)covertView.findViewById(R.id.delete);
          covertView.setTag(holder);
      }  else {
          holder=(ViewHolder)covertView.getTag();
      }
        holder.imageView.setImageBitmap(beanList.get(i).getPicture());
        holder.type.setText(beanList.get(i).getType());
        holder.content.setText(beanList.get(i).getContent());
        holder.time.setText(beanList.get(i).getTime());

       event( context ,holder,i);

       return covertView;
    }

    private void event(final Context context, ViewHolder holder, final int position) {
        holder.biaojiweiyidu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Toast.makeText(context, "标记为已读", Toast.LENGTH_SHORT).show();

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"已经删除",Toast.LENGTH_SHORT).show();
            }
        });
        holder.zhhiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "已经置顶", Toast.LENGTH_SHORT).show();
            }
        });


         holder.QswipeLayout.setOnSwipeListener(new onSwipeListener() {
         @Override
         public void onSwipOtherItem(boolean b, QSwipeLayout qSwipeLayout) {

          }
      });
        holder.QswipeLayout.setOnQQDragListener(new OnQQDragListener() {
            @Override
            public void onMoveDragListener(QSwipeLayout swipeLayout) {
                swipeLayout.dragView(context);
            }
        });

    }

    class ViewHolder{
        public   QSwipeLayout QswipeLayout;
        public ImageView imageView;
        public TextView type,content,time,zhhiding,biaojiweiyidu,delete;

    }
}
