package com.example.anew.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.R;
import com.example.anew.helper.ItemClickRv;

import java.util.List;


public class AdapterListCallPhoneFilter extends RecyclerView.Adapter<AdapterListCallPhoneFilter.ViewHolder> {

    private List<ModelListPhoneCall> modelListPhoneCalls;
    private ItemClickRv mitemClickRv;
    private Context context;


    public AdapterListCallPhoneFilter(List<ModelListPhoneCall> modelListPhoneCalls, Context context, ItemClickRv itemClickRv) {
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
        final ModelListPhoneCall modelListPhoneCall = modelListPhoneCalls.get(position);
        holder.tvPhone.setText(modelListPhoneCall.getCustomer().getPhone1());
        holder.tvName.setText(modelListPhoneCall.getCustomer().getFullname());
//        holder.tvCusfeel.setText(modelListPhoneCall.getCustomerFeel());


        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = modelListPhoneCall.getCustomer().getPhone1();
                mitemClickRv.onClickCall(position,phone);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = modelListPhoneCall.getCustomer().getPhone1();
                mitemClickRv.onItemClick(phone);
            }
        });
    }


    @Override
    public int getItemCount() {
        return modelListPhoneCalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone, tvCusfeel;
        private ImageView img_del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_del = itemView.findViewById(R.id.icon_delete);
//            tvCusfeel = itemView.findViewById(R.id.item_tv_customer_feel);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPhone = itemView.findViewById(R.id.item_tv_phone);
        }
    }
}
