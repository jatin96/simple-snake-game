package com.difusal.logic;

import android.graphics.Point;

public class Cell extends GameElement {
    public Cell(int x, int y, int radius) {
        super(radius);
        location = new Point(x, y);
    }

    public Cell(Cell cell) {
        super(cell.getRadius());
        location = new Point(cell.location);
    }
}
