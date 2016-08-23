package com.example.mcanvas;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by langchou on 8/19/16.
 */
public class RingProgressbar extends View {
//    private int beginArc = 0;//开始角度
//    private int endArc =0;//结束角度
    private int ringWidth = 50;//圆环的宽度
    private int ringColor = Color.GREEN;//圆环进度条的颜色
    private int ringBackColor =Color.GRAY;//圆环的背景颜色
    private int backColor = Color.TRANSPARENT;//整个控件的背景颜色
    private int maxValue =360;//最大值
    private int minValue =0;//最小值
    private boolean showValueFlag = false; //显示数值的标志位  false 不显示  true 显示
    private int valueColor = Color.GREEN;//数值的颜色
    private int valueSize = 100;//数值的尺寸
    private String  unit; //单位
    private int unitColor = Color.GREEN;//单位的颜色
    private int unitSize = 50;//单位的尺寸
    private int progressValue;


    //圆弧的轮廓 矩形
    private RectF  ringRectF  = new RectF();

    Paint ringPaint  =  new Paint();//画圆环的笔
    Paint numberPaint  = new Paint();//画数字的笔
    Paint unitPaint = new Paint();//画单位的笔
    Rect tempRect = new Rect();//用于测量文字的尺寸的 矩形

    public RingProgressbar(Context context) {
        super(context);
    }

    public RingProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RingProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int  h = getMeasuredHeight();
        int  w = getMeasuredWidth();
        ringWidth =w/12;
        valueSize = ringWidth *2;
        unitSize = ringWidth;


        float per = 360.0f/(maxValue - minValue); //度数计算比例

        canvas.save();//保存画布

        canvas.drawColor(backColor);//画布背景

        ringPaint.setAntiAlias(true);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(ringWidth);

        ringRectF.set(ringWidth/2,ringWidth/2,getMeasuredHeight()-ringWidth/2,getMeasuredWidth()-ringWidth/2);

        ringPaint.setColor(ringBackColor);
        canvas.drawArc(ringRectF,0,360,false,ringPaint);//画圆环的背景
        ringPaint.setColor(ringColor);
        Log.i("p",(int)(per * progressValue)+"：："+per);
        canvas.drawArc(ringRectF,0,(int)(per * progressValue),false,ringPaint);//画圆环的值  false  表示不从圆心填实


        if(!showValueFlag){//不显示数字值
            canvas.restore();
            return;
        }

        numberPaint.setAntiAlias(true);
        numberPaint.setColor(valueColor);
        numberPaint.setTextSize(valueSize);
        String s = progressValue+"";

        numberPaint.getTextBounds(s,0,s.length(), tempRect);
        int rw = tempRect.width();
        int rh = tempRect.height();
        canvas.drawText(s,w/2-rw/2,h/2+rh/2,numberPaint);


        if(TextUtils.isEmpty(unit)){//单位为 空或者空字符则不显示
            canvas.restore();
            return;
        }

        unitPaint.setAntiAlias(true);
        unitPaint.setColor(unitColor);
        unitPaint.setTextSize(unitSize);
        canvas.drawText(unit,w/2+rw/2+10,h/2,unitPaint);

        canvas.restore();
    }


    public void play(int progressValue){
        ObjectAnimator  ob  = ObjectAnimator.ofInt(this,"ProgressValue",this.progressValue,0,progressValue);
        ob.setDuration(2000);
        ob.setInterpolator(new AccelerateDecelerateInterpolator());
        ob.start();
    }

    public void setProgressValue(int position){
        progressValue  = position;
        invalidate();
    }

    public int getProgressValue(){
        return progressValue;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }


    public int getRingWidth(){
        return  ringWidth;
    }

    public void setRingWidth(int ringWidth) {
        this.ringWidth = ringWidth;
        ringRectF  = new RectF(ringWidth/2,ringWidth/2,getMeasuredHeight()-ringWidth/2,getMeasuredWidth()-ringWidth/2);
    }

    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }

    public int getRingBackColor() {
        return ringBackColor;
    }

    public void setRingBackColor(int ringBackColor) {
        this.ringBackColor = ringBackColor;
    }

    public int getRingColor() {
        return ringColor;
    }

    public void setRingColor(int ringColor) {
        this.ringColor = ringColor;
    }

    public void showNumberFlag(boolean flag){
        showValueFlag = flag;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getValueColor() {
        return valueColor;
    }

    public void setValueColor(int valueColor) {
        this.valueColor = valueColor;
    }

    public int getValueSize() {
        return valueSize;
    }

    public void setValueSize(int valueSize) {
        this.valueSize = valueSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnitColor() {
        return unitColor;
    }

    public void setUnitColor(int unitColor) {
        this.unitColor = unitColor;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    public RectF getRingRectF() {
        return ringRectF;
    }

    public void setRingRectF(RectF ringRectF) {
        this.ringRectF = ringRectF;
    }
}
