package com.difusal.logic;

import android.graphics.Point;

public class SpecialElement extends GameElement {
    // maximum duration to display clock in snake moves
    private static int MAX_DURATION;

    // counter
    private int counter;

    private boolean hasExpired;

    public SpecialElement(Point fieldDimensions, Snake snake, int radius, int maxDuration) {
        super(radius);
        newRandomLocation(fieldDimensions, snake);
        MAX_DURATION = maxDuration;
        restartCounter();
    }

    public void incCounter() {
        counter++;

        if (counter >= MAX_DURATION) {
            hasExpired = true;
        }
    }

    public void restartCounter() {
        counter = 0;
    }

    public boolean hasExpired() {
        return hasExpired;
    }
}
