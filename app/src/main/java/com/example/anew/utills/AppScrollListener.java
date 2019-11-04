package com.example.anew.utills;


import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class AppScrollListener extends RecyclerView.OnScrollListener {
    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int size = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            findFirstAndLastVisible((LinearLayoutManager) layoutManager);
        }

        if (dy > 0 && size - lastVisibleItemPosition <= 4) {
            onLoadMore();
            Log.e("GGG", "onScrolled: " + lastVisibleItemPosition);
        }
    }

    private void findFirstAndLastVisible(LinearLayoutManager layoutManager) {
        firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
    }

    public abstract void onLoadMore();

}
