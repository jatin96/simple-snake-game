package com.difusal.logic;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

public class RedApple extends Apple {
    private static final int SCORE = 20;

    public RedApple(Point fieldDimensions, Snake snake, int radius) {
        super(fieldDimensions, snake, radius, SCORE, Color.RED);
        Log.v("RedApple", "Red apple created");
    }
}
