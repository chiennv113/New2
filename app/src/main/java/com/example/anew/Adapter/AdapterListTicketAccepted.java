package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListTicketAccepted.Datum;
import com.example.anew.R;
import com.example.anew.helper.IClickListTicketAccepted;

import java.util.List;

public class AdapterListTicketAccepted extends RecyclerView.Adapter<AdapterListTicketAccepted.ViewHolder> {

    List<Datum> data;
    private Context context;
    private IClickListTicketAccepted iClickListTicketAccepted, iClickTranfer;

    public AdapterListTicketAccepted(List<Datum> data, Context context, IClickListTicketAccepted iClickListTicketAccepted) {
        this.data = data;
        this.context = context;
        this.iClickListTicketAccepted = iClickListTicketAccepted;
    }


    @NonNull
    @Override
    public AdapterListTicketAccepted.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listticket_accepted, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListTicketAccepted.ViewHolder holder, int position) {
        Datum datum = data.get(position);
        holder.tvTieude.setText("Tieu de: " + datum.getTitle());
        holder.tvLoai.setText("Loai: " + datum.getType());
        holder.tvTrangthai.setText("Trang thai: " + datum.getStatus());
        holder.viewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListTicketAccepted.onClickView(datum.getId());
            }
        });
        holder.tranferTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListTicketAccepted.onClickTranfer(datum.getId(), holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTieude, tvLoai, tvTrangthai;
        private ImageView viewTicket, tranferTicket;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTieude = itemView.findViewById(R.id.tv_Tieude);
            tvLoai = itemView.findViewById(R.id.tv_LoaiTicket);
            tvTrangthai = itemView.findViewById(R.id.tv_Trangthai);
            viewTicket = itemView.findViewById(R.id.viewTicket);
            tranferTicket = itemView.findViewById(R.id.tranferTicket);

        }
    }

    public void updateData(List<Datum> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }
}
