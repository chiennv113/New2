package com.example.anew.Adapter;

import android.content.Context;
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
import com.example.anew.helper.ItemClickRv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter_List_Call_Remind extends RecyclerView.Adapter<Adapter_List_Call_Remind.ViewHolder> {

    private List<ModelListPhoneCallRemind> modelListPhoneCallReminds;
    private Context context;
    private ItemClickRv mitemClickRv;

    public Adapter_List_Call_Remind(List<ModelListPhoneCallRemind> modelListPhoneCallReminds, Context context, ItemClickRv itemClickRv) {
        this.modelListPhoneCallReminds = modelListPhoneCallReminds;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rv_list_call_remind, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ModelListPhoneCallRemind modelListPhoneCallRemind = modelListPhoneCallReminds.get(position);
        if (modelListPhoneCallRemind.getCallTo()!= null) {
            holder.tvPhone.setText(String.valueOf(modelListPhoneCallRemind.getCallTo().getPhone1()));
            holder.tvName.setText(modelListPhoneCallRemind.getCallTo().getFullname());
            holder.tvEmail.setText(modelListPhoneCallRemind.getCallTo().getEmail());
            holder.tvContent.setText(modelListPhoneCallRemind.getRemindContent());
            Log.e("TAG", "onBindViewHolder: " + modelListPhoneCallRemind.getRemindTime());

            Date d = new Date((long) modelListPhoneCallRemind.getRemindTime() * 1000);
            DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            holder.tvTimeRemind.setText(f.format(d));
        }


        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickRv.onItemClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelListPhoneCallReminds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone, tvEmail, tvContent, tvTimeRemind;
        private ImageView imgDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvTimeRemind = itemView.findViewById(R.id.tv_time_remind);
            imgDel = itemView.findViewById(R.id.ic_delete);
        }
    }

    public void updateData(List<ModelListPhoneCallRemind> list){
        modelListPhoneCallReminds.clear();
        modelListPhoneCallReminds.addAll(list);
        notifyDataSetChanged();
    }
}
