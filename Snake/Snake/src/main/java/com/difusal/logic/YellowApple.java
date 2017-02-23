package com.difusal.logic;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

public class YellowApple extends Apple {
    private static final int SCORE = 50;

    public YellowApple(Point fieldDimensions, Snake snake, int radius) {
        super(fieldDimensions, snake, radius, SCORE, Color.YELLOW);
        Log.v("YellowApple", "Yellow apple created");
    }
}
