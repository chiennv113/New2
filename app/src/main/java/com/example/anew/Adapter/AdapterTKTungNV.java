package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelTKTheoNV.Phone;
import com.example.anew.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterTKTungNV extends RecyclerView.Adapter<AdapterTKTungNV.ViewHolder> {
    private List<Phone> modelThongKeTheoNVAdmins;
    private Context context;

    public AdapterTKTungNV(List<Phone> modelThongKeTheoNVAdmins, Context context) {
        this.modelThongKeTheoNVAdmins = modelThongKeTheoNVAdmins;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_chitiet_nv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phone phone = modelThongKeTheoNVAdmins.get(position);
        holder.tvDanhGia.setText(phone.getCustomerFeel().getName());
        holder.tvGhiChu.setText("Ghi chú: "+phone.getNote());
        holder.tvND.setText("Nội dung: "+phone.getContent());
        holder.tvNguoiNhan.setText(phone.getCustomerId().getFullname());
        Date d = new Date((long) phone.getCallTime() * 1000);
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvTG.setText(f.format(d));
    }

    @Override
    public int getItemCount() {
        return modelThongKeTheoNVAdmins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNguoiNhan, tvND, tvGhiChu, tvDanhGia, tvTG;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDanhGia = itemView.findViewById(R.id.tvDanhGia);
            tvNguoiNhan = itemView.findViewById(R.id.tvNguoiNhan);
            tvND = itemView.findViewById(R.id.tvNoiDung);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
            tvTG = itemView.findViewById(R.id.tvThoiGian);
        }
    }

    public void updateData(List<Phone> list) {
        modelThongKeTheoNVAdmins.clear();
        modelThongKeTheoNVAdmins.addAll(list);
        notifyDataSetChanged();
    }
}