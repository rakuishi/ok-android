package com.rakuishi.ok.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rakuishi.ok.R;
import com.rakuishi.ok.api.model.FeedItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rakuishi on 15/05/02.
 */
public class FeedAdapter extends ArrayAdapter<FeedItem> {

    private LayoutInflater mLayoutInflater;

    public FeedAdapter(Context context, List<FeedItem> list) {
        super(context, 0, list);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_two_line, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        FeedItem item = getItem(position);
        holder.primaryTextView.setText(item.getTitle());
        holder.secondaryTextView.setText(item.getPubDate());

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.row_two_line_primary_tv) TextView primaryTextView;
        @InjectView(R.id.row_two_line_secondary_tv) TextView secondaryTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
