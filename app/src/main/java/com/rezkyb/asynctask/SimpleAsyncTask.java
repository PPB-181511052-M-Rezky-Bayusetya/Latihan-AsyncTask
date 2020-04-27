package com.rezkyb.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Integer, String>{

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    int sleep_time;

    SimpleAsyncTask(TextView tv, ProgressBar pb){
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Random r = new Random();
        int n = r.nextInt(11);

        sleep_time = n * 200;

        mProgressBar.get().setMax(sleep_time);
        mProgressBar.get().setProgress(0);
    }

    @Override
    protected String doInBackground(Void... voids) {
        int n=sleep_time/200;
            try {
                for(int i =1;i <= n; i++) {
                    Thread.sleep(200);
                    publishProgress(i * 200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return "Awake at last after sleeping for "+ sleep_time +" milliseconds";
    }

    protected void onPostExecute(String result){
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... s) {
        mTextView.get().setText("Sleeping for "+ s[0]+ " ms");
        mProgressBar.get().setProgress(s[0]);
    }
}
