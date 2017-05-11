package com.android.shooter_x;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Akshat Arora on 10-05-2017.
 */
public class gunshooter extends Activity implements View.OnTouchListener {
    MyBringBackSurface oursurfaceView;
    float x,fx,animy=0;Bitmap bitmap,circle;int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oursurfaceView=new MyBringBackSurface(this);
        setContentView(oursurfaceView);
        x=0;
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.gun);
        circle=BitmapFactory.decodeResource(getResources(),R.drawable.rhombus);
        oursurfaceView.setOnTouchListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        oursurfaceView.Pause();

    }
    @Override
    protected void onResume(){
        super.onResume();
        oursurfaceView.Resume();
    }

    @Override
    public boolean onTouch(View view,MotionEvent event) {
        super.onTouchEvent(event);
        x=event.getX();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_UP:
                fx=event.getX();
                break;}

        return true;
    }
    public class MyBringBackSurface extends SurfaceView implements  Runnable {
        SurfaceHolder surfaceHolder;
        Thread thread=null;
        boolean isrunning=false;
        public MyBringBackSurface(Context context){
            super(context);
            surfaceHolder=getHolder();

        }
        public void  Pause(){
            isrunning=false;
            while(true){
                try{
                    thread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;

            } thread=null;
        }
        public void Resume(){
            isrunning=true;
            Thread thread=new Thread(this);
            thread.start();
        }
        @Override
        public void run() {
            while (isrunning) {
                if (!surfaceHolder.getSurface().isValid())
                    continue;
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);height=canvas.getHeight();
                canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2, canvas.getHeight() - bitmap.getHeight(), null);
                if (x != 0 ) {
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-50, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-100, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-150, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-200, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-250, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-300, null);

                }
                if (fx != 0) {
                    canvas.drawBitmap(circle,x-circle.getWidth()/2,height-circle.getHeight()-bitmap.getHeight()-animy,null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-50, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-100, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-150, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-200, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-250, null);
                    canvas.drawBitmap(circle, x - circle.getWidth() / 2, height-circle.getHeight()-bitmap.getHeight()-animy-300, null);

                }  if(animy<height-bitmap.getHeight()-circle.getHeight()) animy += 20;//you can control speed  of the fire here
                else animy=0;
                surfaceHolder.unlockCanvasAndPost(canvas);


            }
        }}
}
