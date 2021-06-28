package com.example.userregistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddUsersFragment extends Fragment {
    private EditText name;
    private EditText phoneNum;
    private Button btnSave;

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        return view;
    }

    private void saveNote() {
        String InputName = name.getText().toString();
        long InputPhoneNum = Long.parseLong(phoneNum.getText().toString());

        if (InputName.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, InputName);
        data.putExtra(EXTRA_PHONE_NUMBER, InputPhoneNum);

        int id = getActivity().getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        getActivity().setResult(Activity.RESULT_OK, data);
        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
//        getActivity().finish();
    }
}