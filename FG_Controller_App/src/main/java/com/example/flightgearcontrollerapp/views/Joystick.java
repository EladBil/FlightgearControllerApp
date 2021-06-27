package com.example.flightgearcontrollerapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


import java.util.jar.Attributes;


public class Joystick extends SurfaceView implements View.OnTouchListener,SurfaceHolder.Callback {
    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;
    public JoystickListener joystickCallback;
    private final int ratio = 5;

    public void setupDimensions(){
        centerX = (float)getWidth() / 2;
        centerY = (float)getHeight() / 2;
        baseRadius = (float)Math.min(getWidth(), getHeight()) / 2;
        baseRadius *= 0.7;
        hatRadius = (float)Math.min(getWidth(), getHeight()) / 5;
        hatRadius *= 0.8;
    }

    public Joystick(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            joystickCallback = (JoystickListener) context;
        }
        joystickViewDrawSettings();
    }
    public Joystick(Context context, AttributeSet attributes, int style) {
        super(context, (AttributeSet) attributes, style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        joystickViewDrawSettings();
    }

    public Joystick(Context context, AttributeSet attributes) {
        super(context, (AttributeSet) attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        joystickViewDrawSettings();
    }

    private void joystickViewDrawSettings() {
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true); //necessary
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    public void drawJoystick(float newX,float newY){
        if (getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            colors.setARGB(170,10,20,30); //joystick base color
            myCanvas.drawCircle(centerX,centerY,baseRadius,colors);
            colors.setARGB(230,30,150,110);//joystick it self colors
            myCanvas.drawCircle(newX,newY,hatRadius,colors);
            getHolder().unlockCanvasAndPost(myCanvas);//write to the surfaceview
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX,centerY);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if(v.equals(this)){
            if(e.getAction() != e.ACTION_UP){
                float displacement = (float) Math.sqrt((Math.pow(e.getX()-centerX,2))+Math.pow(e.getY()-centerY,2));
                if (displacement < baseRadius) {
                    drawJoystick(e.getX(), e.getY());
                    joystickCallback.onJoystickMoved((e.getX() - centerX)/baseRadius,(e.getY() - centerY)/baseRadius,getId());
                }
                else{
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (e.getX() - centerX) * ratio;
                    float constrainedY = centerY + (e.getY() - centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    joystickCallback.onJoystickMoved((constrainedX - centerX)/baseRadius,(constrainedY - centerY)/baseRadius,getId());
                }
            }
            else{
                drawJoystick(centerX,centerY);
                joystickCallback.onJoystickMoved(0,0,getId());
            }

        }
        return true;
    }

    public interface JoystickListener
    {
        void onJoystickMoved(double xPercent, double yPercent, int source);
    }
}


