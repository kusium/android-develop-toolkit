package com.sample.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabWidget;

public class VerticalTabWigdet extends TabWidget {

	public VerticalTabWigdet(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
	}

	@Override
	public void addView(View child) {
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
		lp.setMargins(0, 0, 0, 0);
		child.setLayoutParams(lp);

		super.addView(child);
	}

}