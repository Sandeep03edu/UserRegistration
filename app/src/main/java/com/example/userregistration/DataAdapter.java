package com.example.userregistration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends ListAdapter<Data, DataAdapter.DataHolder> {

    private OnItemClickListener listener;
    protected DataAdapter() {
        super(Diff_CallBack);
    }

    public static final DiffUtil.ItemCallback<Data> Diff_CallBack = new DiffUtil.ItemCallback<Data>() {
        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getPhoneNum() == newItem.getPhoneNum();
        }
    };

    @NonNull
    @Override
    public DataAdapter.DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.datas_item, parent, false);
        return new DataHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataHolder holder, int position) {
        Data currentData = getItem(position);
        holder.textViewName.setText(currentData.getName());
        holder.textViewPhoneNum.setText(String.valueOf(currentData.getPhoneNum()));
    }

    public Data getDataAt(int position) {
        return getItem(position);
    }

    class DataHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewPhoneNum;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPhoneNum = itemView.findViewById(R.id.text_view_phone_num);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(listener!=null && pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(pos));
                    }
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Data data);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
