package com.terry.bthvi.uiadvanced.QQUnReadMessages;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Copyright: Copyright (c) 2018
 * Project: 仿QQ未读消息
 * Author: bthvi
 * Date: 2019/2/19 10:44
 */
public class QQUnReadMessageView extends View {
  private final static String TAG = "QQUnReadMessageView>>";
  //大圆半径
  private int largerCircleR = 100;
  //小圆半径
  private float littleCircleR = 100;
  //最小半径
  private float minCircleR = 30;
  //大圆圆心位置
  private PointF pointA = new PointF(150f,150f);
  //小圆圆心位置
  private PointF pointB = new PointF(380f,480f);
  //圆心间的距离
  private double distance = 0;
  //最大拖动距离
  private static final float MAXDRAGVALUE = 600f;
  //画笔
  private Paint mPaint = new Paint();

  private int curState = ConstantViewState.STATE_IDEL;

  public QQUnReadMessageView(Context context) {
    super(context);
  }

  public QQUnReadMessageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onDraw(Canvas canvas) {

    mPaint.setColor(0xfff00012);
//    mPaint.setStyle(Paint.Style.STROKE);
    //绘制大圆
    canvas.drawCircle(pointA.x,pointA.y,largerCircleR,mPaint);
    //两圆心之间的距离
     distance = Math.sqrt(Math.pow(Math.abs(pointA.x-pointB.x),2)+Math.pow(Math.abs(pointA.y-pointB.y),2));
     if (distance >= MAXDRAGVALUE || curState == ConstantViewState.STATE_DISCONNECT){
       pointB.x = pointA.x;
       pointB.y = pointA.y;
       curState = ConstantViewState.STATE_DISCONNECT;
     }
    //计算小圆半径
    littleCircleR = (float) (1-(distance/MAXDRAGVALUE*0.8)) * largerCircleR*0.8f;
     if (littleCircleR < largerCircleR*0.2f){
       littleCircleR = largerCircleR*0.2f;
     }
    //绘制小圆
    canvas.drawCircle(pointB.x,pointB.y,littleCircleR,mPaint);
    PointF point1,point2,point3,point4;
    if (pointA.x > pointB.x){ //大圆在右边
      //计算角度
      float arcr = (float) Math.asin((pointA.y-pointB.y)/distance);
      //确定小圆左上角点坐标
       point1 = calclutePoint1L(pointB,arcr,littleCircleR);
      //确定小圆左上角点坐标
      point2 = calclutePoint2L(pointB,arcr,littleCircleR);
      //确定大圆左上角点坐标
      point3 = calclutePoint1L(pointA,arcr,largerCircleR);
      //确定大圆左上角点坐标
      point4 = calclutePoint2L(pointA,arcr,largerCircleR);
    }else {//大圆在左边
      //计算角度
      float arcr = (float) Math.asin((pointA.y-pointB.y)/distance);
      //确定小圆左点坐标
      point1 = calclutePoint2R(pointB,arcr,littleCircleR);
      //确定小圆右点坐标
      point2 = calclutePoint1R(pointB,arcr,littleCircleR);
      //确定大圆左点坐标
      point3 = calclutePoint2R(pointA,arcr,largerCircleR);
      //确定大圆右点坐标
      point4 = calclutePoint1R(pointA,arcr,largerCircleR);
    }

    //确定贝塞尔曲线控制点
    PointF pointF = calclutePointBezier(pointA,pointB);
    //绘制
    //绘制小圆直径
    Path mpath = new Path();

    mpath.moveTo(point3.x,point3.y);
    mpath.quadTo(pointF.x,pointF.y,point1.x,point1.y);
    mpath.lineTo(point2.x,point2.y);
    mpath.quadTo(pointF.x,pointF.y,point4.x,point4.y);
    mpath.close();
    canvas.drawPath(mpath,mPaint);
  }


  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        getParent().requestDisallowInterceptTouchEvent(true);
        curState = ConstantViewState.STATE_IDEL;
        break;
       case MotionEvent.ACTION_MOVE:
        pointA.x =  event.getX();
        pointA.y =  event.getY();
        invalidate();
         break;
      case MotionEvent.ACTION_UP:
         pointA.x = pointB.x;
         pointA.y = pointB.y;
        invalidate();
         break;
      default:
           break;
    }
    return true;
  }
  /**
   * 贝塞尔曲线控制点
   * @param pointA
   * @param pointB
   * @return
   */
  private PointF calclutePointBezier(PointF pointA, PointF pointB) {
    PointF point = new PointF(0,0);
    point.x = (float) (pointA.x + (pointB.x-pointA.x)/2);
    point.y = (float) (pointA.y + (pointB.y-pointA.y)/2);
    return point;
  }

  /**
   * 大圆在左侧坐标系
   * @param pointB
   * @param arcr
   * @param circleR
   * @return
   */
  private PointF calclutePoint1L(PointF pointB, float arcr,float circleR) {
    PointF point = new PointF(0,0);
    point.x = (float) (pointB.x - circleR*Math.sin(arcr));
    point.y = (float) (pointB.y + circleR*Math.cos(arcr));
    return point;
  }
  /**
   * 大圆在左侧坐标系
   * @param pointB
   * @param arcr
   * @param circleR
   * @return
   */
  private PointF calclutePoint2L(PointF pointB, float arcr,float circleR) {
    PointF point = new PointF(0,0);
    point.x = (float) (pointB.x + circleR*Math.sin(arcr));
    point.y = (float) (pointB.y - circleR*Math.cos(arcr));
    return point;
  }
  /**
   * 大圆在右侧坐标系
   * @param pointB
   * @param arcr
   * @param circleR
   * @return
   */
  private PointF calclutePoint1R(PointF pointB, float arcr,float circleR) {
    PointF point = new PointF(0,0);
    point.x = (float) (pointB.x + circleR*Math.sin(arcr));
    point.y = (float) (pointB.y + circleR*Math.cos(arcr));
    return point;
  }
  /**
   * 大圆在右侧坐标系
   * @param pointB
   * @param arcr
   * @param circleR
   * @return
   */
  private PointF calclutePoint2R(PointF pointB, float arcr,float circleR) {
    PointF point = new PointF(0,0);
    point.x = (float) (pointB.x - circleR*Math.sin(arcr));
    point.y = (float) (pointB.y - circleR*Math.cos(arcr));
    return point;
  }
}
