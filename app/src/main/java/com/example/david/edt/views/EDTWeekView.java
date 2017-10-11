package com.example.david.edt.views;

import android.content.Context;
import android.util.AttributeSet;

import com.alamkanak.weekview.WeekView;

/**
 * Created by david on 11/10/17.
 */

public class EDTWeekView extends WeekView {

    public EDTWeekView(Context context, AttributeSet set){
        super(context, set);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int h = resolveSizeAndState(MeasureSpec.getSize(w), heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }
}
