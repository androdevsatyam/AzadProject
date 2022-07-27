package com.androdev.azadproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androdev.azadproject.Model.Data;
import com.androdev.azadproject.R;
import com.androdev.azadproject.UI.DashBoard;
import com.androdev.azadproject.databinding.DataViewBinding;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataView> {

    Context context;
    ArrayList<Data> mainData;

    public DataAdapter(Context context, ArrayList<Data> mainData) {
        this.context = context;
        this.mainData = mainData;
    }

    @NonNull
    @Override
    public DataView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.data_view, parent, false);
        return new DataView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataView holder, int position) {
        holder.binding.mid.setText(mainData.get(position).getMid());
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public class DataView extends RecyclerView.ViewHolder {

        DataViewBinding binding;

        public DataView(DataViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
