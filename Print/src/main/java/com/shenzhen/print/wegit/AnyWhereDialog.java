package com.shenzhen.print.wegit;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class AnyWhereDialog extends Dialog {
	private static int default_width = 400; //
	private static int default_height = 200; //

	Context mContext;
	EditText mAttr = null;
	EditText mPrice = null;
	Button mconfirm = null;
 
	public AnyWhereDialog(Context context, int layout, int style, int directory, boolean flag) {
		this(context, default_width, default_height, layout, 0, 0, style, directory, flag);
	}

	public AnyWhereDialog(Context mContext, int width, int height, int x, int y,
                          int layout, int style, int directory, boolean flag) {
		super(mContext, style);

		this.mContext = mContext;
		// set content
		setContentView(layout);
		// set window params
		Window window = getWindow();
		window.setWindowAnimations(style);
		WindowManager.LayoutParams params = window.getAttributes();
		// set width,height by density and gravity
		float density = getDensity(mContext);
		params.width = (int) (width * density);
		params.height = (int) (height * density);
		params.gravity = directory;
		params.alpha = 1.0f;
		params.dimAmount = 0.5f;
		params.x = x;
		params.y = y;
		setCanceledOnTouchOutside(flag);
		window.setAttributes(params);
	}

	private float getDensity(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.density;
	}
}
