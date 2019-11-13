package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListCustomer.ModelListCustomer;
import com.example.anew.R;

import java.util.List;

public class AdapterListCustomer extends RecyclerView.Adapter<AdapterListCustomer.ViewHoler>  {

    private Context context;
    private List<ModelListCustomer> modelListCustomers;

    public AdapterListCustomer(Context context, List<ModelListCustomer> modelListCustomers) {
        this.context = context;
        this.modelListCustomers = modelListCustomers;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_customer,parent, false);
        return null;
    }


//
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

    }


    @Override
    public int getItemCount() {
        return modelListCustomers.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView name,email,phone,address,skype,birthday;

    public ViewHoler(@NonNull View itemView) {
        super(itemView);
        email = itemView.findViewById(R.id.tvEmailC);
        name = itemView.findViewById(R.id.tvNameC);
        phone = itemView.findViewById(R.id.tvPhoneC);
        address = itemView.findViewById(R.id.tvAddressC);
        skype = itemView.findViewById(R.id.tvSkypeC);
        birthday = itemView.findViewById(R.id.tvBirthdayC);
    }
}
}
