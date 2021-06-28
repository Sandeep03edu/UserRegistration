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

    public void insert(Data Data){
        repository.insert(Data);
    }
    public void update(Data Data){
        repository.update(Data);
    }
    public void delete(Data Data){
        repository.delete(Data);
    }
    public void deleteAllDatas(){
        repository.deleteAllNodes();
    }
    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }
}
