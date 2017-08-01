package com.rakuishi.ok.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.ListItemGistBinding;
import com.rakuishi.ok.model.Gist;
import com.rakuishi.ok.util.OnListChangedCallback;

public class GistAdapter extends ArrayAdapter<Gist> {

    private LayoutInflater inflater;

    public GistAdapter(Context context, ObservableArrayList<Gist> gists) {
        super(context, 0, gists);
        inflater = LayoutInflater.from(context);
        gists.addOnListChangedCallback(new OnListChangedCallback<>(this));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemGistBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.list_item_gist, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListItemGistBinding) convertView.getTag();
        }

        binding.setGist(getItem(position));

        return convertView;
    }
}
