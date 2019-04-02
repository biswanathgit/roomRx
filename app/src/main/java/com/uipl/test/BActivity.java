package com.uipl.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.uipl.test.localDB.AppDatabase;
import com.uipl.test.localDB.Country;

import java.util.List;

public class BActivity extends BaseActivity {
    public static final String TAG = BActivity.class.getSimpleName();

    AppDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d(TAG, "onCreate: B");
        Toast.makeText(this, "onCreate: B", Toast.LENGTH_SHORT).show();
        db = AppDatabase.getInMemoryDatabase(this);

        setScreenTitle("Activity B");
        //db.countryDao().loadAllCountries();

        new GetCountry().execute("test");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: B");
        Toast.makeText(this, "onStart: B", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: B");
        Toast.makeText(this, "onPause: B", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: B");
        Toast.makeText(this, "onRestart: B", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: B");
        Toast.makeText(this, "onResume: B", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: B");
        Toast.makeText(this, "onStop: B", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: B");
        Toast.makeText(this, "onDestroy: B", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public class GetCountry extends AsyncTask<String,String,List<Country>>{

        @Override
        protected List<Country> doInBackground(String... strings) {

            return db.countryDao().loadAllCountries();
        }

        @Override
        protected void onPostExecute(List<Country> countries) {
            super.onPostExecute(countries);
            if(countries==null)
              System.out.println("@@@ testing countries count : null" );
            else {
                System.out.println("@@@ testing countries count : " + countries.size() );
            }
            //System.out.println("@@@@@@@@@@@@@@@@ first countries :" + countries.get(0).getCountryName() );
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
