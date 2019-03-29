package com.terry.bthvi.uiadvanced.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.terry.bthvi.uiadvanced.R;


/**
 * Copyright: Copyright (c) 2018
 * Author: bthvi
 * Date: 2019/3/26 19:00
 */
public class PathEffectView extends View {
  private Paint mPaint;
  private Path path;
  public PathEffectView(Context context) {
    super(context);
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setStrokeWidth(3);
    mPaint.setAntiAlias(true);
    mPaint.setColor(getResources().getColor(R.color.colorPrimary));
    path = new Path();
  }

  public PathEffectView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setStrokeWidth(10);
    mPaint.setAntiAlias(true);
    mPaint.setColor(getResources().getColor(R.color.colorPrimary));
  }

  @Override
  public void onDraw(Canvas canvas){
    drawDashLine(canvas);
  }



  private void drawDashLine(Canvas canvas) {
    DashPathEffect effect = new DashPathEffect(new float[]{10,30},20);
    mPaint.setPathEffect(effect);
    canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,mPaint);
  }
}
