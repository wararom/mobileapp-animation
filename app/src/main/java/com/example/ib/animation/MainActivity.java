package com.example.ib.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable mAnimation;
    ImageView img;
    int x=0,y=0;
    int w,h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        w = img.getWidth();
        h = img.getHeight();
        BitmapDrawable[] frame = new BitmapDrawable[9];
        int i=0;
        for(i=1; i<=8; i++){
            frame[i] = (BitmapDrawable)getResources().getDrawable(
                    getResources().getIdentifier((String)"f"+i, "drawable", this.getPackageName()) );
        }
        int reasonableDuration = 200;
        mAnimation = new AnimationDrawable();
        for(i=1; i<=8; i++){
            mAnimation.addFrame(frame[i], reasonableDuration);
        }
        img.setImageDrawable(mAnimation);

        final Button strbtn = (Button) findViewById(R.id.strbtn);
        strbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAnimation.start();
                mAnimation.setOneShot(false);
            }
        });
        final Button stpbtn = (Button) findViewById(R.id.stpbtn);
        stpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAnimation.stop();
            }
        });

//        TranslateAnimation tAnimation = new TranslateAnimation(-280, 260, 500, 0);
//        tAnimation.setDuration(1000);
//        tAnimation.setFillAfter(true);
//        tAnimation.setRepeatCount(Animation.INFINITE);
//        tAnimation.setRepeatMode(Animation.REVERSE);
//        img.setAnimation(tAnimation);

        RotateAnimation rAnim = new RotateAnimation(0f, 270f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rAnim.setStartOffset(0);
        rAnim.setDuration(1000);
        rAnim.setFillAfter(true);
        rAnim.setRepeatCount(Animation.INFINITE);
        rAnim.setRepeatMode(Animation.RESTART);
        img.setAnimation(rAnim);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            TranslateAnimation tAnimation =
                    new TranslateAnimation(x, ((int)event.getX()-(w/2)),
                            y, ((int)event.getY()-h));
            x = (int)event.getX()-(w/2);
            y = (int)event.getY()-h;
            tAnimation.setDuration(1000);
            tAnimation.setFillAfter(true);
            tAnimation.setRepeatCount(0);
            tAnimation.setRepeatMode(Animation.RESTART);
            img.startAnimation(tAnimation);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
