package com.rey.material.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Rey on 8/19/2015.
 */
public class PaddingDrawable extends Drawable implements Drawable.Callback {

    private Drawable mDrawable;

    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    public PaddingDrawable(@NonNull Drawable drawable) {
        setWrappedDrawable(drawable);
    }

    public void setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        mPaddingLeft = paddingLeft;
        mPaddingTop = paddingTop;
        mPaddingRight = paddingRight;
        mPaddingBottom = paddingBottom;
    }

    public int getPaddingLeft() {
        return mPaddingLeft;
    }

    public int getPaddingTop() {
        return mPaddingTop;
    }

    public int getPaddingRight() {
        return mPaddingRight;
    }

    public int getPaddingBottom() {
        return mPaddingBottom;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mDrawable.draw(canvas);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mDrawable.setBounds(bounds.left + mPaddingLeft, bounds.top + mPaddingTop, bounds.right - mPaddingRight, bounds.bottom - mPaddingBottom);
    }

    @Override
    public int getChangingConfigurations() {
        return mDrawable.getChangingConfigurations();
    }

    @Override
    public void setChangingConfigurations(int configs) {
        mDrawable.setChangingConfigurations(configs);
    }

    @Override
    public void setDither(boolean dither) {
        mDrawable.setDither(dither);
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        mDrawable.setFilterBitmap(filter);
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mDrawable.setColorFilter(cf);
    }

    @Override
    public boolean isStateful() {
        return mDrawable.isStateful();
    }

    @Override
    public boolean setState(@NonNull final int[] stateSet) {
        return mDrawable.setState(stateSet);
    }

    @NonNull
    @Override
    public int[] getState() {
        return mDrawable.getState();
    }

    public void jumpToCurrentState() {
        mDrawable.jumpToCurrentState();
    }

    @NonNull
    @Override
    public Drawable getCurrent() {
        return mDrawable.getCurrent();
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        return super.setVisible(visible, restart) || mDrawable.setVisible(visible, restart);
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public Region getTransparentRegion() {
        return mDrawable.getTransparentRegion();
    }

    @Override
    public int getIntrinsicWidth() {
        return mDrawable.getIntrinsicWidth() + mPaddingLeft + mPaddingRight;
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight() + mPaddingTop + mPaddingBottom;
    }

    @Override
    public int getMinimumWidth() {
        return mDrawable.getMinimumWidth() + mPaddingLeft + mPaddingRight;
    }

    @Override
    public int getMinimumHeight() {
        return mDrawable.getMinimumHeight() + mPaddingTop + mPaddingBottom;
    }

    @Override
    public boolean getPadding(@NonNull Rect padding) {
        boolean hasPadding = mDrawable.getPadding(padding);
        if (hasPadding) {
            padding.left += mPaddingLeft;
            padding.top += mPaddingTop;
            padding.right += mPaddingRight;
            padding.bottom += mPaddingBottom;
        } else {
            padding.set(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            hasPadding = mPaddingLeft != 0 || mPaddingTop != 0 || mPaddingRight != 0 || mPaddingBottom != 0;
        }

        return hasPadding;
    }

    /**
     * {@inheritDoc}
     */
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    /**
     * {@inheritDoc}
     */
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        scheduleSelf(what, when);
    }

    /**
     * {@inheritDoc}
     */
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        unscheduleSelf(what);
    }

    @Override
    protected boolean onLevelChange(int level) {
        return mDrawable.setLevel(level);
    }

    @Override
    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(mDrawable);
    }

    @Override
    public void setAutoMirrored(boolean mirrored) {
        DrawableCompat.setAutoMirrored(mDrawable, mirrored);
    }

    @Override
    public void setTint(int tint) {
        DrawableCompat.setTint(mDrawable, tint);
    }

    @Override
    public void setTintList(ColorStateList tint) {
        DrawableCompat.setTintList(mDrawable, tint);
    }

    @Override
    public void setTintMode(@NonNull PorterDuff.Mode tintMode) {
        DrawableCompat.setTintMode(mDrawable, tintMode);
    }

    @Override
    public void setHotspot(float x, float y) {
        DrawableCompat.setHotspot(mDrawable, x, y);
    }

    @Override
    public void setHotspotBounds(int left, int top, int right, int bottom) {
        DrawableCompat.setHotspotBounds(mDrawable, left, top, right, bottom);
    }

    public Drawable getWrappedDrawable() {
        return mDrawable;
    }

    public void setWrappedDrawable(@NonNull Drawable drawable) {
        mDrawable.setCallback(null);

        mDrawable = drawable;

        drawable.setCallback(this);

        onBoundsChange(getBounds());
        invalidateSelf();
    }
}
