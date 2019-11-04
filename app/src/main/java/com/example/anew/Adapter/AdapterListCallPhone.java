package com.example.anew.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListPhoneCall.CallList;
import com.example.anew.Model.ModelListPhoneCall.Customer;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCallV2;
import com.example.anew.R;
import com.example.anew.helper.ILoadMore;
import com.example.anew.helper.ItemClickRv;

import java.util.List;

public class AdapterListCallPhone extends RecyclerView.Adapter<AdapterListCallPhone.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;

    private List<CallList> modelListPhoneCalls;
    private ItemClickRv mitemClickRv;
    private Context context;


    public AdapterListCallPhone(List<CallList> modelListPhoneCalls, Context context, ItemClickRv itemClickRv) {
        this.modelListPhoneCalls = modelListPhoneCalls;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CallList modelListPhoneCall = modelListPhoneCalls.get(position);
        holder.tvPhone.setText(modelListPhoneCall.getCustomer().getPhone1());
        holder.tvName.setText(modelListPhoneCall.getCustomer().getFullname());
        holder.tvContent.setText(String.valueOf(modelListPhoneCall.getContent()));

        holder.img_del.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cuộc gọi");
            builder.setMessage("Bạn có muốn thực hiện cuộc gọi tới số: "+modelListPhoneCall.getCustomer().getPhone1()+" không?");
            builder.setCancelable(false);
            builder.setPositiveButton("Gọi", (dialog, which) -> {
                String phone = modelListPhoneCall.getCustomer().getPhone1();
                mitemClickRv.onClickCall(position, phone);
                dialog.dismiss();
            })
                    .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        });
        holder.itemView.setOnClickListener(view -> {
            String phone = modelListPhoneCall.getCustomer().getPhone1();
            mitemClickRv.onItemClick(phone);
        });
    }


    @Override
    public int getItemCount() {
        return modelListPhoneCalls.size();
    }

    @Override
    public int getItemViewType(int position) {
        return modelListPhoneCalls.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone, tvContent;
        private ImageView img_del;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_del = itemView.findViewById(R.id.icon_delete);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPhone = itemView.findViewById(R.id.item_tv_phone);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    public void updateListCall(List<CallList> listPhoneCalls) {
        modelListPhoneCalls.clear();
        modelListPhoneCalls.addAll(listPhoneCalls);
        notifyDataSetChanged();
    }


}
