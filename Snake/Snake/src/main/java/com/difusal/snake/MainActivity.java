package com.difusal.snake;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.difusal.logic.GamePanel;
import com.difusal.logic.MainThread;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // request to turn the title OFF
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        // make it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gamePanel = new GamePanel(this);
        setContentView(gamePanel);

        Log.d(TAG, "View added");
    }

    @Override
    public void onRestart() {
        Log.d(TAG, "Restarting...");

        if (gamePanel != null)
            gamePanel.initGame();

        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Pausing...");
        MainThread.setRunning(false);
        super.onPause();
    }
}
