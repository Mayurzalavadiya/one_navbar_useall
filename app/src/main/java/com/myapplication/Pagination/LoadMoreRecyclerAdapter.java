package com.myapplication.Pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.myapplication.R;

import java.util.List;


public abstract class LoadMoreRecyclerAdapter<T extends BaseRecyclerAdapter.ViewHolder, M> extends BaseRecyclerAdapter<T, M> {

    private final int VIEW_TYPE_FOOTER = 1, VIEW_TYPE_ITEM = 2;
    private boolean showLoadMore = false;

    public LoadMoreRecyclerAdapter(List<M> data) {
        super(data);
    }


    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FOOTER && showLoadMore) {
            return (T) new LoaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_progress_view, parent, false));
        }
        return onCreateHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        if (position == getItemCount() - 1 && showLoadMore) {
            ((LoaderHolder) holder).linProgressView.setVisibility(
                   showLoadMore ? View.VISIBLE : View.GONE);
            return;
        }
        onBindHolder(holder, position);
    }

    //This methods for created for making loadmore recyclerview
    public abstract T onCreateHolder(ViewGroup parent, int viewType);

    public abstract void onBindHolder(T holder, int position);

    @Override
    public int getItemCount() {
        if (getList() == null || getList().size() <= 0) {
            return 0;
        }
        if (showLoadMore) {
            return getList().size() + 1;
        } else {
            return getList().size();
        }
    }

    public void showLoadMore(boolean showLoadMore) {
        //Return if already shown/hide
        if (this.showLoadMore == showLoadMore) {
            return;
        }
        this.showLoadMore = showLoadMore;
        if (showLoadMore) {
            notifyItemInserted(getItemCount() - 1);
        } else {
            notifyItemRemoved(getItemCount() - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 && showLoadMore ? VIEW_TYPE_FOOTER : VIEW_TYPE_ITEM;
    }

    class LoaderHolder extends ViewHolder {
        View linProgressView;

        LoaderHolder(View itemView) {
            super(itemView);
            linProgressView = itemView.findViewById(R.id.progress);
        }
    }
}