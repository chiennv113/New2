package com.example.anew.utills;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;

public class CustomTouchListener implements View.OnTouchListener {
    int down;
    int up;

    public CustomTouchListener(@ColorInt int down,@ColorInt int up) {
        this.down = down;
        this.up = up;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ((TextView) view).setTextColor(down); //white
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                ((TextView) view).setTextColor(up); //black
                break;
        }
        return false;
    }

}
