package com.rezkyb.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Integer, String>{

    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv){
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        try{
            publishProgress(s);
            Thread.sleep(s);
//            publishProgress(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return "Awake at last after sleeping for "+ s +" milliseconds";
    }

    protected void onPostExecute(String result){
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... s) {
        mTextView.get().setText("Sleeping for "+ s[0]+ " ms");
    }
}
