package com.allthelucky.jsonutils.sample;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.allthelucky.jsonutils.JSONObjectPaser;
import com.allthelucky.jsonutils.sample.info.Info;
import com.example.androidjsonparser.R;

/**
 * test client
 * 
 * @author steven-pan
 * 
 */
public class MainActivity extends Activity {

	private static String jsonStr = "[{\"name\":\"hello\",\"age\":1,\"address\":{\"address\":\"number 1\",\"postcode\":434000},\"datas\":[{\"width\":480,\"length\":800,\"shape\":\"retangle sape\"},{\"width\":540,\"length\":960,\"shape\":\"retangle sape\"}]}]";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		test();
	}

	public void test() {
		((TextView) findViewById(R.id.textView1)).setText("json:\n"+jsonStr);
		JSONObjectPaser parser = new JSONObjectPaser();
		try {

			JSONArray result = new JSONArray(jsonStr);
			Object object = parser.parse(result, Info.class);
			((TextView) findViewById(R.id.textView2)).setText("output:\n"+object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
