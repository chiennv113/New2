package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListTicket.Datum;
import com.example.anew.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterListTicket extends RecyclerView.Adapter<AdapterListTicket.ViewHolder> {
    List<Datum> data;
    private Context context;

    public AdapterListTicket(List<Datum> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum datum = data.get(position);
        holder.tvStatus.setText("Trạng thái: " + datum.getStatus());
        holder.tvType.setText("Loại: " + datum.getType());
        holder.tvTittle.setText("Tiêu đề: " + datum.getTitle());
        holder.tvProduct.setText("Sản phẩm: " + datum.getProduct().getName());
        String a = (datum.getContents()).replace("<p>", "");
        String b = a.replace("</p>", "");
        holder.tvContent.setText("Nội dung: " + b);

        Date d = new Date((long) datum.getCreateTime() * 1000);
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvCreatTime.setText("Ngày tạo: " + f.format(d));

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click View", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click Accept", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTittle, tvProduct, tvContent, tvType, tvStatus, tvCreatTime;
        private ImageView imgAccept, imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContentInListTicket);
            tvCreatTime = itemView.findViewById(R.id.tvCreateTimeInListTicket);
            tvProduct = itemView.findViewById(R.id.tvProductInListTicket);
            tvTittle = itemView.findViewById(R.id.tvTitletInListTicket);
            tvType = itemView.findViewById(R.id.tvTypeInListTicket);
            tvStatus = itemView.findViewById(R.id.tvStatusInListTicket);
            imgAccept = itemView.findViewById(R.id.imgAccept);
            imgView = itemView.findViewById(R.id.imgView);
        }
    }

    public void updateData(List<Datum> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }
}
