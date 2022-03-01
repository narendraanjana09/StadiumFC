package com.nsa.stadiumfc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nsa.stadiumfc.R;
import com.nsa.stadiumfc.databinding.TableLayoutItemBinding;
import com.nsa.stadiumfc.models.AttendanceModel;

import java.util.List;

public class AttendanceAdapters extends RecyclerView.Adapter<AttendanceAdapters.ViewHolder> {
    private List<AttendanceModel> list;

    public AttendanceAdapters(List<AttendanceModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TableLayoutItemBinding binding=TableLayoutItemBinding
                .bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_layout_item,parent,false));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TableLayoutItemBinding binding;
        public ViewHolder(@NonNull TableLayoutItemBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }

        public void setData(AttendanceModel attendanceModel) {
            binding.nameTv.setText(attendanceModel.getName());
            binding.dateTv.setText(attendanceModel.getDate());
            binding.timeTv.setText(attendanceModel.getTime());
        }
    }
}
