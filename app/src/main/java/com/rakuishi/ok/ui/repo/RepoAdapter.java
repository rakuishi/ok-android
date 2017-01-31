package com.rakuishi.ok.ui.repo;

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
import com.rakuishi.ok.databinding.ListItemRepoBinding;
import com.rakuishi.ok.model.Gist;
import com.rakuishi.ok.model.Repo;
import com.rakuishi.ok.util.OnListChangedCallback;

public class RepoAdapter extends ArrayAdapter<Repo> {

    private LayoutInflater inflater;

    public RepoAdapter(Context context, ObservableArrayList<Repo> repos) {
        super(context, 0, repos);
        inflater = LayoutInflater.from(context);
        repos.addOnListChangedCallback(new OnListChangedCallback<>(this));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemRepoBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.list_item_repo, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListItemRepoBinding) convertView.getTag();
        }

        binding.setRepo(getItem(position));

        return convertView;
    }
}
