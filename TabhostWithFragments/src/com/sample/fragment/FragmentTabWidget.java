package com.sample.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentTabWidget extends LinearLayout {
	private static final String TEST_ITEMS[] = new String[] { "ITEMA", "ITEMB",
			"ITEMC", "ITEMD" };

	public FragmentTabWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.init();
	}

	public FragmentTabWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	public FragmentTabWidget(Context context) {
		super(context);
		this.init();
	}

	void init() {
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		List<View> list = new ArrayList<View>();
		for (String item : TEST_ITEMS) {
			TextView textView = new TextView(getContext());
			textView.setText(item);
			textView.setGravity(Gravity.CENTER);
			list.add(textView);
		}
		this.init(list);
		this.setCurrentTab(0);
	}
	
	void setCurrentTab(int index) {
		int childCount = getChildCount();
		for(int i=0; i< childCount; i++) {
			View view = getChildAt(i);
			if(i==index ) {
				view.setBackgroundColor(Color.BLUE);
			} else {
				view.setBackgroundColor(Color.GREEN);
			}
		}
	}

	void init(List<View> list) {
		for (View item : list) {
			this.addView(item);
		}
	}

	@Override
	public void addView(View child) {
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f);
		lp.setMargins(0, 0, 0, 0);
		lp.gravity = 1;
		child.setLayoutParams(lp);

		super.addView(child);
	}

}
