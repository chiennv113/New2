package com.example.anew.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListPhoneCallRemind.ModelListPhoneCallRemind;
import com.example.anew.R;
import com.example.anew.helper.IRemoveRemid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterListCallRemind extends RecyclerView.Adapter<AdapterListCallRemind.ViewHolder> {

    private List<ModelListPhoneCallRemind> modelListPhoneCallReminds;
    private Context context;
    private IRemoveRemid mitemClickRv;

    public AdapterListCallRemind(List<ModelListPhoneCallRemind> modelListPhoneCallReminds, Context context, IRemoveRemid itemClickRv) {
        this.modelListPhoneCallReminds = modelListPhoneCallReminds;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_remind_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ModelListPhoneCallRemind modelListPhoneCallRemind = modelListPhoneCallReminds.get(position);
        if (modelListPhoneCallRemind.getCallTo() != null) {
            holder.tvPhone.setText(String.valueOf(modelListPhoneCallRemind.getCallTo().getPhone1()));
            holder.tvName.setText(modelListPhoneCallRemind.getCallTo().getFullname());

            Date d = new Date((long) modelListPhoneCallRemind.getRemindTime() * 1000);
            DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            holder.tvRemindDate.setText(f.format(d));
        }

        holder.imgDel.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xóa nhắc nhở");
            builder.setMessage("Bạn có muốn xóa không?");
            builder.setCancelable(false);
            builder.setPositiveButton("Xóa", (dialog, which) -> {
                mitemClickRv.onItemClick(holder.getAdapterPosition(), modelListPhoneCallRemind.getId());
                Log.e("GGG", "onClick: " + modelListPhoneCallRemind.getId());
                dialog.dismiss();
            })
                    .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });


    }

    @Override
    public int getItemCount() {
        return modelListPhoneCallReminds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone, tvRemindDate;
        private ImageView imgDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvRemindDate = itemView.findViewById(R.id.tv_time_remind);
            imgDel = itemView.findViewById(R.id.imgDel);
        }
    }

    public void updateData(List<ModelListPhoneCallRemind> list) {
        modelListPhoneCallReminds.clear();
        modelListPhoneCallReminds.addAll(list);
        notifyDataSetChanged();
    }
}
