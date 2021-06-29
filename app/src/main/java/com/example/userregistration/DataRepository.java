package com.example.userregistration;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {
    private DataDao DataDao;
    private LiveData<List<Data>> allDatas;

    public DataRepository(Application application) {
        DataDatabase database = DataDatabase.getInstance(application);
        DataDao = database.DataDao();
        allDatas = DataDao.getAllDatas();
    }

    public void insert(Data... Datas){
        new DataRepositoryAsyncTask(){
            @Override
            public void doInBackground() {
                DataDao.insert(Datas[0]);
                super.doInBackground();
            }
        }.execute();
    }

    public void update(Data... Datas){
        new DataRepositoryAsyncTask(){
            @Override
            public void doInBackground() {
                DataDao.update(Datas[0]);
                super.doInBackground();
            }
        }.execute();
    }

    public void delete(Data... Datas){
        new DataRepositoryAsyncTask(){
            @Override
            public void doInBackground() {
                DataDao.delete(Datas[0]);
                super.doInBackground();
            }
        }.execute();
    }

    public void deleteAllNodes(Data... Datas){
        new DataRepositoryAsyncTask(){
            @Override
            public void doInBackground() {
                DataDao.deleteAllDatas();
                super.doInBackground();
            }
        }.execute();
    }

    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }

    public abstract static class DataRepositoryAsyncTask {
        public DataRepositoryAsyncTask() {  }
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

