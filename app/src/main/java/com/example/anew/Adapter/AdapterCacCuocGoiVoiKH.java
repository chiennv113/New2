package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelSearchCu.Phonecall;
import com.example.anew.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterCacCuocGoiVoiKH extends RecyclerView.Adapter<AdapterCacCuocGoiVoiKH.ViewHolder> {
    private Context context;
    private List<Phonecall> modelphonecalls;

    public AdapterCacCuocGoiVoiKH(Context context, List<Phonecall> phonecalls) {
        this.context = context;
        this.modelphonecalls = phonecalls;
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
        Phonecall phonecall = modelphonecalls.get(position);
        Date d = new Date((long) phonecall.getCallTime() * 1000);
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        holder.tvThoiGian.setText(f.format(d));

        String nd1 = (phonecall.getContent()).replace("<p>", "");
        String nd2 = nd1.replace("</p>", "");
        holder.tvNoiDung.setText("Nội dung: " + nd2);

        holder.tvNguoiGoi.setText("Người gọi: " + phonecall.getCallerId().getFullname());
        holder.tvGhiChu.setText(("Ghi chú: " + phonecall.getNote()).replace("null", ""));
    }

    @Override
    public int getItemCount() {
        return modelphonecalls.size();
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

    public void updateData(List<Phonecall> phonecalls) {
        modelphonecalls.clear();
        modelphonecalls.addAll(phonecalls);
        notifyDataSetChanged();
    }
}
