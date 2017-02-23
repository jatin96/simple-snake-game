package com.difusal.logic;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayDeque;

public class Snake {
    private static final String TAG = GamePanel.class.getSimpleName();

    /**
     * Number of speed steps.
     * Speed will be increased in equal steps until full speed is not reached.
     */
    private final static int SPEED_STEPS = 30;
    private final static int SLOWED_TIME_MOVE_DELAY = MainThread.getFps() / 5;

    private ArrayDeque<Cell> cells;
    private Cell previousTail;
    private int radius;

    private double finalMoveDelay;
    private double moveDelay, moveDelayInc;
    private boolean speedNeedsToBeIncremented;

    // clock stuff
    private boolean timeSlowed = false;
    private double savedDelay;
    private int clockCounter;

    // shield stuff
    private boolean hasShield = false;

    private Direction direction;
    private int life;
    private int score;

    private boolean useBitmaps;

    public Snake(int radius, boolean useBitmaps) {
        this.radius = radius;

        // clear cells container
        cells = new ArrayDeque<Cell>();

        cells.addLast(new Cell(3, 2, radius));
        cells.addLast(new Cell(2, 2, radius));
        cells.addLast(new Cell(1, 2, radius));

        double initialMoveDelay = moveDelay = SLOWED_TIME_MOVE_DELAY;
        finalMoveDelay = initialMoveDelay / 3;
        moveDelayInc = (initialMoveDelay - finalMoveDelay) / SPEED_STEPS;
        speedNeedsToBeIncremented = false;

        direction = Direction.RIGHT;
        life = 100;
        score = 0;

        this.useBitmaps = useBitmaps;
    }

    public void move() {
        // get snake head location
        Point head = getHead().getLocation();

        // add a new cell in front of the head in the current direction
        switch (direction) {
            case UP:
                cells.addFirst(new Cell(head.x, head.y - 1, radius));
                break;
            case DOWN:
                cells.addFirst(new Cell(head.x, head.y + 1, radius));
                break;
            case LEFT:
                cells.addFirst(new Cell(head.x - 1, head.y, radius));
                break;
            case RIGHT:
                cells.addFirst(new Cell(head.x + 1, head.y, radius));
                break;
        }

        // remove last cell and temporarily save it
        previousTail = cells.removeLast();

        checkIfAteItself();
    }

    private void checkIfAteItself() {
        for (Cell cell : cells)
            if (cell != getHead() && cell.getLocation().equals(getHead().getLocation()))
                if (hasShield()) {
                    setHasShield(false);
                    Log.i(TAG, "Shield lost");
                } else
                    kill();
    }

    public boolean ate(GameElement element) {
        return getHead().getLocation().equals(element.getLocation());
    }

    public void incSize() {
        cells.addLast(new Cell(previousTail));
    }

    public ArrayDeque<Cell> getCells() {
        return cells;
    }

    public Cell getHead() {
        return cells.peekFirst();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMovingHorizontally() {
        return direction.isHorizontal();
    }

    public boolean isMovingVertically() {
        return !isMovingHorizontally();
    }

    public int getMoveDelay() {
        return (int) Math.round(moveDelay);
    }

    public boolean speedNeedsToBeIncremented() {
        return speedNeedsToBeIncremented;
    }

    public void enableSpeedNeedsToBeIncrementedFlag() {
        speedNeedsToBeIncremented = true;
    }

    public void increaseSpeed() {
        if (!timeSlowed) {
            moveDelay -= moveDelayInc;

            if (moveDelay < finalMoveDelay)
                moveDelay = finalMoveDelay;

            speedNeedsToBeIncremented = false;
        }
    }

    public void startClock() {
        if (!timeSlowed)
            savedDelay = moveDelay;
        moveDelay = SLOWED_TIME_MOVE_DELAY;

        clockCounter = Clock.getEffectDuration();
        timeSlowed = true;

        Log.i(TAG, "Time slowed down");
    }

    public void updateClock() {
        if (timeSlowed) {
            clockCounter--;

            if (clockCounter == 0) {
                timeSlowed = false;
                moveDelay = savedDelay;
                Log.i(TAG, "Time resumed to normal speed");
            }
        }
    }

    public int getSlowedTimeRemaining() {
        return clockCounter;
    }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public boolean hasShield() {
        return hasShield;
    }

    public boolean isDead() {
        return life == 0;
    }

    public void kill() {
        life = 0;
    }

    public void revive() {
        life = 100;
    }

    public int getScore() {
        return score;
    }

    public void incScore(int score) {
        Log.v("Snake", "Current score: " + this.score + " + " + score);
        this.score += score;
    }

    public boolean isUsingBitmaps() {
        return useBitmaps;
    }
}
