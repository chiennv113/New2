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

public class AdapterTkTheoNV extends RecyclerView.Adapter<AdapterTkTheoNV.ViewHolder> {
    private List<ModelThongKeTheoNVAdmin> modelThongKeTheoNVAdmins;
    private Context context;

    public AdapterTkTheoNV(List<ModelThongKeTheoNVAdmin> modelThongKeTheoNVAdmins, Context context) {
        this.modelThongKeTheoNVAdmins = modelThongKeTheoNVAdmins;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tk_theo_nv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelThongKeTheoNVAdmin modelListPhoneCall = modelThongKeTheoNVAdmins.get(position);
        holder.email.setText(modelListPhoneCall.getEmail());
        holder.name.setText(modelListPhoneCall.getFullname());
        holder.soCuocGoi.setText(String.valueOf(modelListPhoneCall.getStatistics().getPhone().size()));
        holder.khmoi.setText(String.valueOf(modelListPhoneCall.getStatistics().getCustomernew().size()));
        holder.khcu.setText(String.valueOf(modelListPhoneCall.getStatistics().getCustomerold().size()));
    }

    @Override
    public int getItemCount() {
        return modelThongKeTheoNVAdmins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView email, name, soCuocGoi, khcu, khmoi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tvEmailTkNV);
            name = itemView.findViewById(R.id.tvNameTkNV);
            soCuocGoi = itemView.findViewById(R.id.tvSoCuocGoiTkNV);
            khcu = itemView.findViewById(R.id.tvKHCuTkNV);
            khmoi = itemView.findViewById(R.id.tvKHMoiTkNV);
        }
    }
}
