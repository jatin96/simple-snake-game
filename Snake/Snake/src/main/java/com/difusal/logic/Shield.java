package com.difusal.logic;

import android.graphics.Point;
import android.util.Log;

public class Shield extends SpecialElement {
    private static final String TAG = GamePanel.class.getSimpleName();

    // maximum duration to display shield in snake moves
    private static final int MAX_DURATION = 30;

    public Shield(Point fieldDimensions, Snake snake, int radius) {
        super(fieldDimensions, snake, radius, MAX_DURATION);

        type = GameElementType.SHIELD;

        Log.v(TAG, "Shield created");
    }
}
