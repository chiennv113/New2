package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCacCuocGoiVoiKH extends RecyclerView.Adapter<AdapterCacCuocGoiVoiKH.ViewHolder> {
    private Context context;
    private List<String> arrayList;

    public AdapterCacCuocGoiVoiKH(Context context, List<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tong_hop_cuoc_goi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvThoiGian.setText(arrayList.get(position));
        holder.tvNoiDung.setText(arrayList.get(position));
        holder.tvNguoiGoi.setText(arrayList.get(position));
        holder.tvGhiChu.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNguoiGoi, tvNoiDung, tvGhiChu, tvThoiGian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChuInTH);
            tvNguoiGoi = itemView.findViewById(R.id.tvNguoiGoiInTH);
            tvNoiDung = itemView.findViewById(R.id.tvNDInTH);
            tvThoiGian = itemView.findViewById(R.id.tvTGInTH);
        }
    }
}
