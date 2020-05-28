package com.example.match.Compent;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

public class RListView extends ListView {
    public RListView(Context context) {
        super(context);
    }

    public RListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
