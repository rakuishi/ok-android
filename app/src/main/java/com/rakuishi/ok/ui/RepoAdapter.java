package com.rakuishi.ok.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rakuishi.ok.R;
import com.rakuishi.ok.api.model.Repo;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rakuishi on 15/05/03.
 */
public class RepoAdapter extends ArrayAdapter<Repo> {

    private LayoutInflater mLayoutInflater;

    public RepoAdapter(Context context, List<Repo> list) {
        super(context, 0, list);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_three_line, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Repo item = getItem(position);
        holder.primaryTextView.setText(item.getName());
        holder.secondaryTextView.setText(item.getDescription());
        holder.tertiaryTextView.setText(item.getUpdatedAt());

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.row_three_line_primary_tv) TextView primaryTextView;
        @InjectView(R.id.row_three_line_secondary_tv) TextView secondaryTextView;
        @InjectView(R.id.row_three_line_tertiary_tv) TextView tertiaryTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
