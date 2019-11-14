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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
   holder.phone.setText(String.valueOf(user.getPhone1()));
        Date d = new Date((long) user.getCreateTime() * 1000);
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        holder.date.setText(f.format(d));


    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView name, email, phone, date;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameC);
            email = itemView.findViewById(R.id.tvEmailC);
            phone = itemView.findViewById(R.id.tvPhoneC);
            date = itemView.findViewById(R.id.tvDateC);

        }

    }

    public void updateData(List<User> list) {
        users.clear();
        users.addAll(list);
        notifyDataSetChanged();
    }
}
