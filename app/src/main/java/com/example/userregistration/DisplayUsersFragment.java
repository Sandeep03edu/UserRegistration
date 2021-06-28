package com.example.userregistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class DisplayUsersFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    private DataViewModel dataViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_display_users, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        DataAdapter adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);

        dataViewModel = ViewModelProviders.of(requireActivity()).get(DataViewModel.class);
        dataViewModel.getAllDatas().observe((LifecycleOwner) getContext(), new Observer<List<Data>>() {
            @Override
            public void onChanged(@Nullable List<Data> Datas) {
                adapter.submitList(Datas);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                dataViewModel.delete(adapter.getDataAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Data deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        return view;
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);

            String InputName = options.getString(AddUsersFragment.EXTRA_NAME);
            long InputPhoneNumber = options.getLong(AddUsersFragment.EXTRA_PHONE_NUMBER);

            Data data = new Data(InputName, InputPhoneNumber);
            dataViewModel.insert(data);
            Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
    }

}