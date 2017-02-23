package com.difusal.logic;

import android.graphics.Point;
import android.util.Log;

import java.util.Random;

public class GameElement {
    private static final String TAG = GamePanel.class.getSimpleName();

    protected Point location;
    protected int radius;
    protected GameElementType type;

    public enum GameElementType {
        APPLE, CLOCK, SHIELD
    }

    public GameElement(int radius) {
        location = new Point();
        this.radius = radius;
    }

    public Point getLocation() {
        return location;
    }

    public void newRandomLocation(Point fieldDimensions, Snake snake) {
        Random random = new Random();
        Point p = new Point();

        boolean valid;
        do {
            valid = true;

            p.x = random.nextInt(fieldDimensions.x - 2) + 1;
            p.y = random.nextInt(fieldDimensions.y - 2) + 1;

            for (Cell cell : snake.getCells())
                if (cell.getLocation().equals(p))
                    valid = false;
        } while (!valid);

        Log.d(TAG, "New element at: " + p.x + ", " + p.y);
        location = p;
    }

    public int getRadius() {
        return radius;
    }

    public GameElementType getType() {
        return type;
    }
}
