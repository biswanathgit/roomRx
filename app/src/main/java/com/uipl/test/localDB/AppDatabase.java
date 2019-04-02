package com.uipl.test.localDB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Country.class, State.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sINSTANCE;
    private static final String DATABASE_NAME = "StudioBooking.db";

    public abstract CountryDao countryDao();
    public abstract StateDao stateDao();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (sINSTANCE == null) {
            copyAttachedDatabase(context, DATABASE_NAME);
            sINSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,DATABASE_NAME)
                    // To simplify the codelab, allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.


//                    .allowMainThreadQueries()
//                     .addCallback(new Callback() {
//                         @Override
//                         public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                             super.onCreate(db);
//                             new PopulateDbAsync(sINSTANCE).execute();
//                         }
//                     })
//                    .build();

                    .addMigrations(AppDatabase.MIGRATION_1_2)
                    //.allowMainThreadQueries()
                    .build();
        }
        return sINSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    //region copy db
    private static void copyAttachedDatabase(Context context, String databaseName) {
        final File dbPath = context.getDatabasePath(databaseName);

        // If the database already exists, return
//        if (dbPath.exists()) {
//            Log.d("testing", "dbPath.exists() == " +dbPath.exists());
//            return;
//        }

        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs();

        // Try to copy database file
        try {
            final InputStream inputStream = context.getAssets().open(databaseName);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            Log.d("testing", "output file size " + buffer.length);
            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
                Log.d("testing", "copying db ...");
            }
            Log.d("testing", "copying file");
            output.flush();
            output.close();
            inputStream.close();
            Log.d("testing", "copying complete...");
        }
        catch (IOException e) {
            Log.d("testing", "Failed to open file", e);
            e.printStackTrace();
        }
    }
    //endregion

    //region data population
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final StateDao stateDao;
        private final CountryDao countryDao;


        public PopulateDbAsync(AppDatabase instance) {
            stateDao = instance.stateDao();
            countryDao = instance.countryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.insertCountry(new Country(2,"Albania",	"AL",""));
            countryDao.insertCountry(new Country(3,	"Algeria","DZ",""));
            countryDao.insertCountry(new Country(4	,"American Samoa",	"AS",""));
            countryDao.insertCountry(new Country(5	,"Andorra",	"AD",""));
            countryDao.insertCountry(new Country(6,"India","IN",""));
            countryDao.insertCountry(new Country(7,	"United States",	"US",""));

            stateDao.insertState(new State(1,	"01",	"IN",	"Andaman and Nicobar Islands"));
            stateDao.insertState(new State(2,	"02",	"IN",	"Andhra Pradesh"));
            stateDao.insertState(new State(3,	"03",	"IN",	"Assam"));
            stateDao.insertState(new State(4,	"04",	"IN",	"Chandigarh"));
            stateDao.insertState(new State(5,	"05",	"IN",	"Dadra and Nagar Haveli"));
            stateDao.insertState(new State(6,	"06",	"IN",	"West Bengal"));
            return null;
        }
    }
    //endregion
}