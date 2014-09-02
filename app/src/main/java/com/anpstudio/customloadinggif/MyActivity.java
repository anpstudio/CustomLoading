package com.anpstudio.customloadinggif;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MyActivity extends Activity {

    private AnimationDrawable mFrameAnimation;
    private ImageView mImageLoading;
    private LinearLayout mContLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mContLoading = (LinearLayout) findViewById(R.id.contLoading);
        mImageLoading = (ImageView) findViewById(R.id.loadingView);
        mImageLoading.setBackgroundResource(R.drawable.loading);

        // Get the background, which has been compiled to an AnimationDrawable object.
        mFrameAnimation = (AnimationDrawable) mImageLoading.getBackground();

        new LongOperation().execute("");
    }


    /**
     * Asyntask to simulate a background job
     */
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //Start  Loading Animation
            mContLoading.setVisibility(View.VISIBLE);
            mFrameAnimation.start();
        }

        @Override
        protected String doInBackground(String... params) {
            //ToDo your Network Job/Request etc. here
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            //ToDo with result you got from Task
            //Stop Loading Animation
            mFrameAnimation.stop();
            mContLoading.setVisibility(View.GONE);

        }
    }



}
