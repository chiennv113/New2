package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelTKTheoNV.ModelThongKeTheoNVAdmin;
import com.example.anew.R;

import java.util.List;

public class AdapterItemNV extends RecyclerView.Adapter<AdapterItemNV.ViewHolder> {

    private List<ModelThongKeTheoNVAdmin> modelThongKeTheoNVAdmins;
    private Context context;

    public AdapterItemNV(List<ModelThongKeTheoNVAdmin> modelThongKeTheoNVAdmins, Context context) {
        this.modelThongKeTheoNVAdmins = modelThongKeTheoNVAdmins;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_chitiet_nv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemNV.ViewHolder holder, int position) {
        holder.tvNguoiNhan.setText(modelThongKeTheoNVAdmins.get(14).getStatistics().getPhone().get(position).getCustomerId().getFullname());

    }

    @Override
    public int getItemCount() {
        return modelThongKeTheoNVAdmins.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNguoiNhan, tvNoiDung, tvDanhGia, tvGhiChu, tvThoiGian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNguoiNhan = itemView.findViewById(R.id.tvNguoiNhan);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvDanhGia = itemView.findViewById(R.id.tvDanhGia);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
        }
    }
}
