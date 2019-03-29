package com.terry.bthvi.uiadvanced.colorfilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Copyright: Copyright (c) 2018
 * Project: 滤镜Filter
 * Author: bthvi
 * Date: 2019/3/19 16:12
 */
public class ColorFilterMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ColorFilterView(this));
    }
}
