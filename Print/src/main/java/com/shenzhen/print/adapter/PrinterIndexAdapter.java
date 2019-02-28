package com.shenzhen.print.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PrinterIndexAdapter extends ArrayAdapter<String> {
	private Context mContext;
	private String[] mStringArray;

	public PrinterIndexAdapter(Context context, String[] stringArray) {
		super(context, android.R.layout.simple_spinner_item, stringArray);
		this.mContext = context;
		this.mStringArray = stringArray;
	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		//修改Spinner展开后的字体颜色
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
		}

		//此处text1是Spinner默认的用来显示文字的TextView
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextSize(22f);
		tv.setTextColor(Color.BLACK);

		return convertView;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 修改Spinner选择后结果的字体颜色
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
		}

		//此处text1是Spinner默认的用来显示文字的TextView
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextSize(18f);
		tv.setTextColor(Color.BLUE);
		return convertView;
	}

}
