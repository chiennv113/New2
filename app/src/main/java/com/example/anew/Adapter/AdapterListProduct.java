package com.example.anew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.R;

import java.util.List;

public class AdapterListProduct extends RecyclerView.Adapter<AdapterListProduct.ViewHoler> {
    private Context context;
    private List<ModelLoadAllProduct> modelLoadAllProducts;

    public AdapterListProduct(Context context, List<ModelLoadAllProduct> modelLoadAllProducts) {
        this.context = context;
        this.modelLoadAllProducts = modelLoadAllProducts;
    }



    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        final ModelLoadAllProduct modelLoadAllProduct = modelLoadAllProducts.get(position);
        holder.title.setText(String.valueOf(modelLoadAllProduct.getTitle()));
        holder.type.setText(String.valueOf(modelLoadAllProduct.getType()));
        if(String.valueOf(modelLoadAllProduct.getType()).equals("1")){
            holder.type.setText("Sản phẩm");
        } else if (String.valueOf(modelLoadAllProduct.getType()).equals("0")){
            holder.type.setText("Dịch vụ");
        }

        holder.name.setText("Tên sản phẩm: " +modelLoadAllProduct.getName());
        holder.description.setText("Mô tả: " + modelLoadAllProduct.getDescription());

    }

    @Override
    public int getItemCount() {
        return modelLoadAllProducts.size();
    }



    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView title, type, name, description;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvFullNameInViewTicket);
            name = itemView.findViewById(R.id.tvNameC);
            type = itemView.findViewById(R.id.tvType);
            description = itemView.findViewById(R.id.tvDescription);
        }
    }

    public void updateData(List<ModelLoadAllProduct> list) {
        modelLoadAllProducts.clear();
        modelLoadAllProducts.addAll(list);
        notifyDataSetChanged();
    }

}
