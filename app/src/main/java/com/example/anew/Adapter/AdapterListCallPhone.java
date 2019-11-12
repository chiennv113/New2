package com.example.anew.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Model.ModelListPhoneCall.CallList;
import com.example.anew.R;
import com.example.anew.helper.ILoadMore;
import com.example.anew.helper.ItemClickRv;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    TextView tvPhone;
    TextView tvContent;
    ImageView img_del;

    ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        img_del = itemView.findViewById(R.id.icon_delete);
        tvName = itemView.findViewById(R.id.item_tv_name);
        tvPhone = itemView.findViewById(R.id.item_tv_phone);
        tvContent = itemView.findViewById(R.id.tvNDInRemind);
    }
}

public class AdapterListCallPhone extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;

    private List<CallList> modelListPhoneCalls;
    private ItemClickRv mitemClickRv;
    private Context context;

    ILoadMore loadMore;
    boolean isLoading;

    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;


    public AdapterListCallPhone(List<CallList> modelListPhoneCalls, Context context, RecyclerView recyclerView, ItemClickRv itemClickRv) {
        this.modelListPhoneCalls = modelListPhoneCalls;
        this.context = context;
        mitemClickRv = itemClickRv;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount(); // Lấy tổng số lượng item đang có
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition(); // Lấy vị trí của item cuối cùng
                Log.e("", "onScrolled: lastVisibleItem: " + lastVisibleItem);
                Log.e("", "onScrolled: totalItemCount: " + totalItemCount);

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) // Nếu không phải trạng thái loading và tổng số lượng item bé hơn hoặc bằng vị trí item cuối + số lượng item tối đa hiển thị
                {
                    if (loadMore != null)
                        loadMore.onLoadMore(); // Gọi callback interface Loadmore
                    isLoading = true;
                }
            }
        });
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_list_call, parent, false);
            final RecyclerView.ViewHolder holder = new ItemViewHolder(view);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return holder;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final CallList modelListPhoneCall = modelListPhoneCalls.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.tvPhone.setText(modelListPhoneCall.getCustomer().getPhone1());
            viewHolder.tvName.setText(modelListPhoneCall.getCustomer().getFullname());
            String abc = (String.valueOf(modelListPhoneCall.getContent())).replace("<p>", "");
            String bcd = abc.replace("</p>", "");
            String aaa = bcd.replace("&nbsp;","");
            viewHolder.tvContent.setText(aaa);

            viewHolder.img_del.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cuộc gọi");
                builder.setMessage("Bạn có muốn thực hiện cuộc gọi tới số: " + modelListPhoneCall.getCustomer().getPhone1() + " không?");
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

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }


    @Override
    public int getItemCount() {
        return modelListPhoneCalls.size();
    }

    @Override
    public int getItemViewType(int position) {
        return modelListPhoneCalls.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }


    public void updateListCall(List<CallList> listPhoneCalls) {
        modelListPhoneCalls.clear();
        modelListPhoneCalls.addAll(listPhoneCalls);
        notifyDataSetChanged();
    }

    public void setLoader() {
        isLoading = false;
    }


}
