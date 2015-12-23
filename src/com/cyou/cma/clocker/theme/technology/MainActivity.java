
package com.cyou.cma.clocker.theme.technology;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private boolean useXml = false;
    private CLockScreen mCLockScreen;
    private TimeView mTimeView;
    private ImageView mImageViewCall;
    private ImageView mImageViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // requestWindowFeature(Window.f)
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Drawable mBackground = new
        // BitmapDrawable(ImageUtil.getCoreBitmap(this, R.drawable.background));
        // mBackground =
        // context.getResources().getDrawable(android.R.drawable.ic_menu_send);
        // ViewCompat.setBackground(this, mBackground);
        // getWindow().setBackgroundDrawable(mBackground);
        if (useXml) {
            setContentView(R.layout.lockscreen);
            mCLockScreen = (CLockScreen) findViewById(R.id.lockview);
            mTimeView = (TimeView) findViewById(R.id.timeview);
            mImageViewCall = (ImageView) findViewById(R.id.call_imageview);
            mImageViewMessage = (ImageView) findViewById(R.id.message_imageview);
            mImageViewCall.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You pressed the call imageView",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            // CLockScreen view = new CLockScreen(this, null);
            // TimeView view = new TimeView(this, null);
            // setContentView(view);
            View view = LayoutInflater.from(this).inflate(R.layout.lockscreen, null);
            setContentView(view);
        }

        // mCLockScreen.setOnTouchListener(new OnTouchListener() {
        //
        // @Override
        // public boolean onTouch(View v, MotionEvent event) {
        // int action = event.getAction();
        // switch (action) {
        // case MotionEvent.ACTION_DOWN:
        // mTimeView.setVisibility(View.GONE);
        // break;
        // case MotionEvent.ACTION_UP:
        // mTimeView.setVisibility(View.VISIBLE);
        // break;
        // default:
        // break;
        // }
        // return true;
        // }
        // });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
