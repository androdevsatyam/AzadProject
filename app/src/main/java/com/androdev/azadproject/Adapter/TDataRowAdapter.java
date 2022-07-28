package com.androdev.azadproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androdev.azadproject.Model.Data;
import com.androdev.azadproject.R;
import com.androdev.azadproject.databinding.TDataViewBinding;

import java.util.ArrayList;

public class TDataRowAdapter extends RecyclerView.Adapter<TDataRowAdapter.DataView> {

    Context context;
    ArrayList<Data.TData> mainData;

    public TDataRowAdapter(Context context, ArrayList<Data.TData> mainData) {
        this.context = context;
        this.mainData = mainData;
    }

    @NonNull
    @Override
    public DataView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TDataViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.t_data_view, parent, false);
        return new DataView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataView holder, int position) {
        holder.binding.tid.setText(mainData.get(position).getTid());
        holder.binding.amount.setText(mainData.get(position).getAmount());
        holder.binding.narration.setText(mainData.get(position).getNarration());
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public class DataView extends RecyclerView.ViewHolder {

        TDataViewBinding binding;

        public DataView(TDataViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
