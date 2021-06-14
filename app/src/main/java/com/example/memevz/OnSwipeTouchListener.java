package com.example.memevz;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener (Context ctx){
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private float diffY;
        private float diffX;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                diffY = e2.getY() - e1.getY();
                diffX = e2.getX() - e1.getX();
                result = checkHorizontal(velocityX, diffX, diffY);
                if (!result) {
                    checkVertical(velocityY, diffY);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    private boolean checkVertical(float velocityY, float diffY) {
        if (Math.abs(diffY) > GestureListener.SWIPE_THRESHOLD && Math.abs(velocityY) > GestureListener.SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) {
                onSwipeBottom();
            } else {
                onSwipeTop();
            }
            return true;
        }
        return false;
    }

    private boolean checkHorizontal(float velocityX, float diffX, float diffY) {
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > GestureListener.SWIPE_THRESHOLD && Math.abs(velocityX) > GestureListener.SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                return true;
            }
        }
        return false;
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}