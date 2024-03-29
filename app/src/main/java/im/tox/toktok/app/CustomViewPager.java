package im.tox.toktok.app;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public final class CustomViewPager extends ViewPager {

    private boolean isSwipeEnable = true;

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isSwipeEnable && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isSwipeEnable && super.onInterceptTouchEvent(event);
    }

    public void setSwipingEnabled(boolean b) {
        isSwipeEnable = b;
    }

}
