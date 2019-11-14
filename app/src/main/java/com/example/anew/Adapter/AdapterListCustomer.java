package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListCustomer.User;
import com.example.anew.R;

import java.util.List;

public class AdapterListCustomer extends RecyclerView.Adapter<AdapterListCustomer.ViewHoler> {

    private Context context;
    private List<User> users;

    public AdapterListCustomer(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_customer, parent, false);
        return new ViewHoler(view);
    }

    //
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        User user = users.get(position);
        holder.email.setText(user.getEmail());
        holder.name.setText(user.getFullname());


    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView name, email, phone, address, skype, birthday, zalo, city,phone2;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tvEmailC);
            name = itemView.findViewById(R.id.tvNameC);
            phone = itemView.findViewById(R.id.tvPhoneC);
            address = itemView.findViewById(R.id.tvAddressC);
            skype = itemView.findViewById(R.id.tvSkypeC);
            birthday = itemView.findViewById(R.id.tvBirthdayC);
            zalo = itemView.findViewById(R.id.tvZalo);
            city = itemView.findViewById(R.id.tv_city);
            phone2 = itemView.findViewById(R.id.tvPhone2C);
        }

    }

    public void updateData(List<User> list) {
        users.clear();
        users.addAll(list);
        notifyDataSetChanged();
    }
}
