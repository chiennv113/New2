package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.R;
import com.example.anew.helper.ItemClickRv;

import java.util.List;

public class Adapter_List_Call_Phone_Filter extends RecyclerView.Adapter<Adapter_List_Call_Phone_Filter.ViewHolder> {

    private List<ModelListPhoneCall> modelListPhoneCalls;
    private ItemClickRv mitemClickRv;

    public Adapter_List_Call_Phone_Filter(List<ModelListPhoneCall> modelListPhoneCalls, ItemClickRv itemClickRv) {
        this.modelListPhoneCalls = modelListPhoneCalls;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rv_list_phone_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ModelListPhoneCall modelListPhoneCall = modelListPhoneCalls.get(position);
        holder.tvPhone.setText(modelListPhoneCall.getCustomer().getPhone1());
        holder.tvName.setText(modelListPhoneCall.getCustomer().getFullname());
        holder.tvCusfeel.setText(modelListPhoneCall.getCustomerFeel());

        final int id = modelListPhoneCall.getCustomer().getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickRv.onItemClick(position,id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelListPhoneCalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone, tvCusfeel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCusfeel = itemView.findViewById(R.id.item_tv_customer_feel);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPhone = itemView.findViewById(R.id.item_tv_phone);
        }
    }
}
