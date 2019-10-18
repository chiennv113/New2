package com.example.anew.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelDeleteCall;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.ItemClickRv;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_List_Call_Phone_Filter extends RecyclerView.Adapter<Adapter_List_Call_Phone_Filter.ViewHolder> {

    private List<ModelListPhoneCall> modelListPhoneCalls;
    private ItemClickRv mitemClickRv;
    private Context context;

    public Adapter_List_Call_Phone_Filter(List<ModelListPhoneCall> modelListPhoneCalls, Context context, ItemClickRv itemClickRv) {
        this.modelListPhoneCalls = modelListPhoneCalls;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rv_list_phone_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ModelListPhoneCall modelListPhoneCall = modelListPhoneCalls.get(position);
        holder.tvPhone.setText(modelListPhoneCall.getCustomer().getPhone1());
        holder.tvName.setText(modelListPhoneCall.getCustomer().getFullname());
        holder.tvCusfeel.setText(modelListPhoneCall.getCustomerFeel());

        SharedPreferences prefs = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        final String cookie = prefs.getString("cookie_name", "No name defined");

        Log.e("TAG", "onBindViewHolder: " + cookie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = modelListPhoneCall.getId();
                mitemClickRv.onItemClick(position, id, holder.img_del);
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
            tvCusfeel = itemView.findViewById(R.id.item_tv_customer_feel);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPhone = itemView.findViewById(R.id.item_tv_phone);
        }
    }
}
