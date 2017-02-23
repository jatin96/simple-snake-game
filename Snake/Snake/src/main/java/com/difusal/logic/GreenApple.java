package com.difusal.logic;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

public class GreenApple extends Apple {
    private static final int SCORE = 10;

    public GreenApple(Point fieldDimensions, Snake snake, int radius) {
        super(fieldDimensions, snake, radius, SCORE, Color.GREEN);
        Log.v("GreenApple", "Green apple created");
    }
}
