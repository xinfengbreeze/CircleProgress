package com.example.mcanvas;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by langchou on 8/19/16.
 */
public class CircleProgressbar  extends View {
    private int beginArc = 0;
    private int endArc =0;
    public CircleProgressbar(Context context) {
        super(context);
    }

    public CircleProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int  h = getMeasuredHeight();
        int  w = getMeasuredWidth();
        int strokeWidth = 50;
        int textWidth = 1;
        Paint p  =  new Paint();
        p.setAntiAlias(true);

//        p.setARGB(100,42,201,52);

        p.setStyle(Paint.Style.STROKE);
//        p.setStrokeCap(Paint.Cap.ROUND);
        p.setTextSize(textWidth);

        p.setStrokeWidth(strokeWidth);
        canvas.save();
        canvas.drawColor(Color.BLACK);
        p.setColor(Color.GRAY);
        canvas.drawArc(new RectF(strokeWidth/2-1,strokeWidth/2-1,h-strokeWidth/2-1,w-strokeWidth/2-1),0,360,false,p);
        p.setColor(Color.GREEN);
        canvas.drawArc(new RectF(strokeWidth/2-1,strokeWidth/2-1,h-strokeWidth/2-1,w-strokeWidth/2-1),beginArc,endArc,false,p);

        Paint pt  = new Paint();
        pt.setColor(Color.GREEN);
        pt.setTextSize(strokeWidth*3);
        pt.setAntiAlias(true);
        String s = endArc*100/360+"";
        Rect r = new Rect();
        pt.getTextBounds(s,0,s.length(),r);
        int rw = r.width();
        int rh = r.height();
        canvas.drawText(s,w/2-rw/2,h/2+rh/2,pt);

        Paint minp = new Paint();
        minp.setColor(Color.WHITE);
        minp.setTextSize(strokeWidth);
        minp.setAntiAlias(true);
        canvas.drawText("分",w/2+rw/2+10,h/2,minp);



        canvas.restore();


    }


    public void play(int beginArc, int endArc){
        ObjectAnimator  ob  = ObjectAnimator.ofInt(this,"ProgressValue",endArc,beginArc,endArc);
        ob.setDuration(2000);
        ob.setInterpolator(new AccelerateDecelerateInterpolator());
        ob.start();
    }

    public void setProgressValue(int position){
        endArc  = position;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }
}
