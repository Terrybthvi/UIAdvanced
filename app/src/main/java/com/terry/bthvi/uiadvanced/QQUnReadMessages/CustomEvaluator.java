package com.terry.bthvi.uiadvanced.QQUnReadMessages;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Copyright: Copyright (c) 2018
 * Author: bthvi
 * Date: 2019/3/28 11:32
 */
public class CustomEvaluator implements TypeEvaluator<PointF> {

  @Override
  public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
    float x = startValue.x + fraction * (endValue.x - startValue.x);
    float y = startValue.y + fraction * (endValue.y - startValue.y);
    return new PointF(x, y);
  }
}
