package com.example.prac1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class MyService extends Service {
    public MyService() {
    }

    private WindowManager windowManager;
    private View overlayView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.CENTER;

        overlayView = LayoutInflater.from(this).inflate(R.layout.notification_item, null);

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        params.x = 100;
        params.y = 100;

        windowManager.addView(overlayView, params);

        windowManager.updateViewLayout(overlayView, params);

        overlayView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            stopSelf();

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
    }
}
