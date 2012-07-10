package com.fuzzydev.gitfit;

import java.util.ArrayList;

import com.fuzzydev.gitfit.structures.UpdateItem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GFNotificationView extends View {
    
	Bitmap imgBitmap;
	int imageResource;
	String notificationMessage;
	Paint paint;
	
	public GFNotificationView(Context context, int imageResource, String notificationMessage){
		super(context);
		this.imageResource = imageResource;
		this.notificationMessage = notificationMessage;
		imgBitmap = BitmapFactory.decodeResource(getResources(),imageResource);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		//int px = 100 * (dm.densityDpi / 160);
		imgBitmap = Bitmap.createScaledBitmap(imgBitmap, 150, 170, true);
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextSize(20);
		postInvalidate();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(imgBitmap, 0, 0, null);
		canvas.drawText(notificationMessage,imgBitmap.getWidth(),this.getHeight()/2,paint);
	}
}
