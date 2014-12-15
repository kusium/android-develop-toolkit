package com.example.baidumap.overlaydemo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
    void showSpot(Context context, MapView mapView, int resId, GeoPoint gepPoint) {
        SpotOverlay spotOverlay = new SpotOverlay(context, resId, gepPoint);
        mapView.getOverlays().add(spotOverlay);
        mapView.setVisibility(View.VISIBLE);
        mapView.invalidate();
    }

    void showList(Context context, MapView mapView, Drawable drawable, List<ListItemInfo> list) {
        ListItemizedOverlay listItemizedOverlay = new ListItemizedOverlay(drawable, list);
        mapView.getOverlays().add(listItemizedOverlay);
        mapView.setVisibility(View.VISIBLE);
        mapView.invalidate();
    }

}
