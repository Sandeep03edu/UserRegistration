package com.example.userregistration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private DataRepository repository;
    private LiveData<List<Data>> allDatas;

    public DataViewModel(@NonNull Application application) {
        super(application);
        repository = new  DataRepository(application);
        allDatas = repository.getAllDatas();
    }

    public void insert(Data data){
        repository.insert(data);
    }
    public void update(Data data){
        repository.update(data);
    }
    public void delete(Data data){
        repository.delete(data);
    }
    public void deleteAllDatas(){
        repository.deleteAllNodes();
    }
    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }
}
