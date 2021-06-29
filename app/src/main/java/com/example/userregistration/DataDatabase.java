package com.example.userregistration;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Data.class, version = 1)
public abstract class DataDatabase extends RoomDatabase {
    private static DataDatabase instance;
    public abstract DataDao DataDao();

    public static synchronized DataDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DataDatabase.class, "data_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).BackgroundTask();
        }
    };

    private static class PopulateDbAsyncTask{

        private DataDao dataDao;

        public PopulateDbAsyncTask(DataDatabase db) {
            dataDao = db.DataDao();
        }

        public void BackgroundTask() {
            new DataDatabaseAsyncTask(){
                @Override
                public void doInBackground() {
                    super.doInBackground();
                }
            }.execute();
        }

    }

    public abstract static class DataDatabaseAsyncTask {

        public DataDatabaseAsyncTask() {  }

        public void startBackground() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doInBackground();
                }
            }).start();
        }

        public void execute() {
            startBackground();
        }

        public void doInBackground() {}

        public void onPostExecute() {}

    }

}