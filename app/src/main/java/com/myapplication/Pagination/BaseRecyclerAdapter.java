package com.myapplication.Pagination;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseRecyclerAdapter<T extends BaseRecyclerAdapter.ViewHolder, M> extends RecyclerView.Adapter<T> {

    private List<M> data;
    private RecycleOnItemClickListener mRecycleOnItemClickListener;
    private RecycleOnItemLongClickListener mRecycleOnItemLongClickListener;

    public BaseRecyclerAdapter(List<M> data) {
        this.data = data;
    }

    public void onItemClick(View view, int position) {
        if (mRecycleOnItemClickListener != null && position != -1) {
            mRecycleOnItemClickListener.onItemClick(view, position);
        }
    }

    public void onItemLongClick(View view, int position) {
        if (mRecycleOnItemLongClickListener != null) {
            mRecycleOnItemLongClickListener.onItemLongClick(view, position);
        }
    }

    public BaseRecyclerAdapter setRecycleOnItemClickListener(RecycleOnItemClickListener mRecycleOnItemClickListener) {
        this.mRecycleOnItemClickListener = mRecycleOnItemClickListener;
        return this;
    }

    public BaseRecyclerAdapter setRecyclerOnLongItemClickListener(RecycleOnItemLongClickListener mRecycleOnItemLongClickListener) {
        this.mRecycleOnItemLongClickListener = mRecycleOnItemLongClickListener;
        return this;
    }

    public void add(M item) {
        this.data.add(item);
        notifyItemInserted(this.data.size() - 1);
    }

    public void add(M item, int position) {
        this.data.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(List<M> items) {
        this.data.addAll(items);
        notifyItemRangeInserted(this.data.size() - items.size(), items.size());
    }

    public void set(List<M> items) {
        List<M> clone = new ArrayList<>(items);
        this.data.clear();
        this.data.addAll(clone);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public M getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
//        int size = data.size();
        return data != null && data.size() > 0 ? data.size() : 50;
    }

    public List<M> getList() {
        return data;
    }

    //Getter Methods for Resources
    public String getString(ViewHolder holder, int id) {
        return holder.itemView.getContext().getString(id);
    }

    public String getString(ViewHolder holder, int id, Object... formatArgs) {
        return holder.itemView.getContext().getString(id, formatArgs);
    }

    public Drawable getDrawable(ViewHolder holder, int id) {
        return ContextCompat.getDrawable(holder.itemView.getContext(), id);
    }

    public int getColor(ViewHolder holder, int id) {
        return ContextCompat.getColor(holder.itemView.getContext(), id);
    }

    public interface RecycleOnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface RecycleOnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getLayoutPosition());
            }
        };

        private View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClick(view, getLayoutPosition());
                return false;
            }
        };

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //put here clickable views list
        public void clickableViews(View... views) {
            for (View view : views) {
                view.setOnClickListener(mOnClickListener);
            }
        }

        //put long clickable view here
        public void longClickableViews(View... views) {
            for (View view : views) {
                view.setOnLongClickListener(mLongClickListener);
            }
        }
    }

    /* ADDED BY_CT*/
    public void notifyItemChanged(M item) {
        notifyItemChanged(getList().indexOf(item));
    }
}