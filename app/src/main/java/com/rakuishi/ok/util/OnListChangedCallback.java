package com.rakuishi.ok.util;

import android.databinding.ObservableList;
import android.widget.ArrayAdapter;

public class OnListChangedCallback<T extends ObservableList> extends ObservableList.OnListChangedCallback<T> {

    private ArrayAdapter adapter;

    public OnListChangedCallback(ArrayAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(T t) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(T t, int i, int i1) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeInserted(T t, int i, int i1) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeMoved(T t, int i, int i1, int i2) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(T t, int i, int i1) {
        adapter.notifyDataSetChanged();
    }
}
