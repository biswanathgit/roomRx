package com.uipl.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uipl.test.localDB.AppDatabase;

public class AActivity extends BaseActivity {
    public static final String TAG = AActivity.class.getSimpleName();
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initlizeViews();

        Log.d(TAG, "onCreate: A");
        Toast.makeText(this, "onCreate: A", Toast.LENGTH_SHORT).show();

        textView = findViewById(R.id.textViewA);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AActivity.this,BActivity.class));
            }
        });

//        AppDatabase.getInMemoryDatabase(this).countryDao().loadAllCountries();
//        AppDatabase.getInMemoryDatabase(this).countryDao().loadAllCountries();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.getInMemoryDatabase(AActivity.this).countryDao().loadAllCountries();
                return null;
            }
        }.execute();
    }

    private void initlizeViews() {
        setScreenTitle("test");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        Toast.makeText(this, "onStart: A", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        Toast.makeText(this, "onPause: A", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: A");
        Toast.makeText(this, "onRestart: A", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: A");
        Toast.makeText(this, "onResume: A", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: A");
        Toast.makeText(this, "onStop: A", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: A");
        Toast.makeText(this, "onDestroy: A", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
