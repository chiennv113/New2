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

import com.example.anew.Model.ModelDeleteRemind;
import com.example.anew.Model.ModelListPhoneCallRemind.ModelListPhoneCallRemind;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_List_Call_Remind extends RecyclerView.Adapter<Adapter_List_Call_Remind.ViewHolder> {

    private List<ModelListPhoneCallRemind> modelListPhoneCallReminds;
    private Context context;

    public Adapter_List_Call_Remind(List<ModelListPhoneCallRemind> modelListPhoneCallReminds, Context context) {
        this.modelListPhoneCallReminds = modelListPhoneCallReminds;
        this.context = context;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelListPhoneCallRemind modelListPhoneCallRemind = modelListPhoneCallReminds.get(position);
        holder.tvPhone.setText(modelListPhoneCallRemind.getCallTo().getPhone1());
        holder.tvName.setText(modelListPhoneCallRemind.getCallTo().getFullname());
        holder.tvEmail.setText(modelListPhoneCallRemind.getCallTo().getEmail());
        holder.tvContent.setText(modelListPhoneCallRemind.getRemindContent());
        Log.e("TAG", "onBindViewHolder: "+modelListPhoneCallRemind.getRemindTime());


//        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss",).toString();

        SharedPreferences prefs = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        final String cookie = prefs.getString("cookie_name", "No name defined");

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiClient.getInstance().deleteRemind("delete_phone_remind", modelListPhoneCallRemind.getId(), cookie).enqueue(new Callback<ModelDeleteRemind>() {
                    @Override
                    public void onResponse(Call<ModelDeleteRemind> call, Response<ModelDeleteRemind> response) {
                        try {
                            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(context, "" + response.body().getError(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelDeleteRemind> call, Throwable t) {

                    }
                });
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
}
