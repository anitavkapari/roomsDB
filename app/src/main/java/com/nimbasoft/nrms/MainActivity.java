package com.nimbasoft.nrms;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;


import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            final MainActivity sPlashScreen = this;
            

            final ImageView splashImageView
                    = (ImageView) findViewById(R.id.SplashImageView);
            splashImageView.setBackgroundResource(R.drawable.flag);
            final AnimationDrawable frameAnimation
                    =(AnimationDrawable)splashImageView.getBackground();


            splashImageView.post(new Runnable()
            {
                @Override
                public void run()
                {
                    frameAnimation.start();
                }
            });

            mSplashThread =  new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        synchronized(this)
                        {
                            wait(4000);
                        }
                    }
                    catch(InterruptedException ex)
                    {
                    }

                    finish();

                    Intent intent = new Intent();
                    intent.setClass(sPlashScreen, LoginActivity.class);
                    startActivity(intent);
                }
            };
            mSplashThread.start();
        }


    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread)
            {
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}