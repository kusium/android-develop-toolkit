package com.allthelucky.examples.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * @ClassName SignActivity
 */
public class SignActivity extends Activity {

	private static final String DIR = "/mnt/sdcard/";

	private SignView mSignView;// 绘图

	private int mWidth;// 屏幕宽度
	private int mHeight;// 屏幕高度
	private String mFileName = "";// 签名文件名

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		mSignView = new SignView(this);
		//main.addView(mSignView);
		
		//get bitmap
		Bitmap bitmap = createBill(mSignView.getCachebBitmap());
		saveFile(bitmap);
	}

	private Bitmap createBill(Bitmap sign) {

		Bitmap bitmap = Bitmap.createBitmap(200, 100, Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		canvas.drawBitmap(Bitmap.createScaledBitmap(sign, 200, 100, false), 0, 0, paint);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);
		byte[] b = bos.toByteArray();

		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

	// 保存文件
	private void saveFile(final Bitmap bitmap) {
		FileOutputStream outStream = null;
		try {
			mFileName = DIR+"sigature.png";
			// mFileName = AppConfig.signFilePath + File.separator + "a" +
			// ".png";
			File file = new File(mFileName);
			file.createNewFile();
			outStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
		} catch (Exception e) {
			Log.e("SignActivity", "saveFile error");
		} finally {
			try {
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void onBackPressed() {
		setResult(1);
		finish();
	}

	private class SignView extends View {

		private Paint paint;
		private Canvas cacheCanvas;
		private Bitmap cachebBitmap;
		private Path path;

		public Bitmap getCachebBitmap() {
			return cachebBitmap;
		}

		public SignView(Context context) {
			super(context);
			init();
		}

		private void init() {
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setStrokeWidth(7);
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(0x666666);
			path = new Path();
			cachebBitmap = Bitmap.createBitmap((int) (mWidth * 0.01), (int) (mHeight * 0.01), Config.ARGB_8888);
			cacheCanvas = new Canvas(cachebBitmap);
			cacheCanvas.drawColor(Color.TRANSPARENT);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawBitmap(cachebBitmap, 0, 0, null);
			canvas.drawPath(path, paint);
		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {

			int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
			int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
			if (curW >= w && curH >= h) {
				return;
			}

			if (curW < w)
				curW = w;
			if (curH < h)
				curH = h;

			Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
			Canvas newCanvas = new Canvas();
			newCanvas.setBitmap(newBitmap);
			cacheCanvas.drawColor(Color.TRANSPARENT);
			if (cachebBitmap != null) {
				newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
			}
			cachebBitmap = newBitmap;
			cacheCanvas = newCanvas;
		}

		private float cur_x, cur_y;

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
			float y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				cur_x = x;
				cur_y = y;
				path.moveTo(cur_x, cur_y);
				break;
			}

			case MotionEvent.ACTION_MOVE: {
				path.quadTo(cur_x, cur_y, x, y);
				cur_x = x;
				cur_y = y;
				break;
			}

			case MotionEvent.ACTION_UP: {
				cacheCanvas.drawPath(path, paint);
				path.reset();
				break;
			}
			}
			invalidate();

			return true;
		}
	}

}
