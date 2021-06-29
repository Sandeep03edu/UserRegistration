package com.example.userregistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;


public class AddUsersFragment extends Fragment {
    private EditText name;
    private EditText phoneNum;
    private Button btnSave;
    private DataDatabase dataDatabase;

    public static final String EXTRA_NAME = "com.example.userregistration.EXTRA_NAME";
    public static final String EXTRA_PHONE_NUMBER = "com.example.userregistration.EXTRA_PHONE_NUMBER";
    public static final String EXTRA_ID = "com.example.userregistration.EXTRA_ID";

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_users, container, false);

        name = view.findViewById(R.id.name);
        phoneNum = view.findViewById(R.id.phone_num);
        btnSave = view.findViewById(R.id.btn_save);

        dataDatabase= DataDatabase.getInstance(getContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InputName = name.getText().toString();

                if(InputName.trim().isEmpty() || phoneNum.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "All Fields are required!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                long InputPhoneNum = Long.parseLong(phoneNum.getText().toString());

                Data newData = new Data(InputName, InputPhoneNum);
                WeakReference<AddUsersFragment> weakReference= new WeakReference<AddUsersFragment>(AddUsersFragment.this);
                new DataInsertAsyncTask(){
                    @Override
                    public void doInBackground() {
                        super.doInBackground();
                        weakReference.get().dataDatabase.DataDao().insert(newData);
                    }
                }.execute();
                Toast.makeText(getContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public abstract static class DataInsertAsyncTask {
        public DataInsertAsyncTask() {  }
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