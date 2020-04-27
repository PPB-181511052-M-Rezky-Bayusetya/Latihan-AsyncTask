package com.rezkyb.asynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE = "CurrentText";
    private TextView mTextView = null;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=(TextView)findViewById(R.id.textView1);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        progressBar.setMax(10);

        if(savedInstanceState!= null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        SimpleAsyncTask task = new SimpleAsyncTask(mTextView,progressBar);
        task.execute();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString(TEXT_STATE,mTextView.getText().toString());
    }
}
