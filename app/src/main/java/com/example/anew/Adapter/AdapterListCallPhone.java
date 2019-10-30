package com.example.anew.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.R;
import com.example.anew.helper.ItemClickRv;

import java.util.ArrayList;
import java.util.List;


public class AdapterListCallPhone extends RecyclerView.Adapter<AdapterListCallPhone.ViewHolder> implements Filterable {

    private List<ModelListPhoneCall> modelListPhoneCalls;
    private List<ModelListPhoneCall> mdFillter;
    private ItemClickRv mitemClickRv;
    private Context context;


    public AdapterListCallPhone(List<ModelListPhoneCall> modelListPhoneCalls, Context context, ItemClickRv itemClickRv) {
        this.modelListPhoneCalls = modelListPhoneCalls;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    public void setSearchResult(List<ModelListPhoneCall> result) {
        modelListPhoneCalls = result;
        notifyDataSetChanged();
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
        holder.tvContent.setText(String.valueOf(modelListPhoneCall.getContent()));

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = modelListPhoneCall.getCustomer().getPhone1();
                mitemClickRv.onClickCall(position, phone);
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mdFillter = modelListPhoneCalls;
                } else {
                    List<ModelListPhoneCall> filteredList = new ArrayList<>();
                    for (ModelListPhoneCall row : modelListPhoneCalls) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCustomer().getFullname().contains(charString.toLowerCase()) || row.getCustomer().getPhone1().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mdFillter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mdFillter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mdFillter = (ArrayList<ModelListPhoneCall>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone, tvContent;
        private ImageView img_del;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_del = itemView.findViewById(R.id.icon_delete);
//            tvCusfeel = itemView.findViewById(R.id.item_tv_customer_feel);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPhone = itemView.findViewById(R.id.item_tv_phone);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }


}
