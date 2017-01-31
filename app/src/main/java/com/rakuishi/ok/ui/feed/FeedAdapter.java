package com.rakuishi.ok.ui.feed;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.ListItemFeedBinding;
import com.rakuishi.ok.model.FeedItem;
import com.rakuishi.ok.util.OnListChangedCallback;

public class FeedAdapter extends ArrayAdapter<FeedItem> {

    private LayoutInflater inflater;

    public FeedAdapter(Context context, ObservableArrayList<FeedItem> feedItems) {
        super(context, 0, feedItems);
        inflater = LayoutInflater.from(context);
        feedItems.addOnListChangedCallback(new OnListChangedCallback<>(this));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemFeedBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.list_item_feed, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListItemFeedBinding) convertView.getTag();
        }

        binding.setFeedItem(getItem(position));

        return convertView;
    }
}
