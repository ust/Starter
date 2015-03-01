package android.dating.ust.com.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains urls
 * Created by yaroslavpa on 3/1/2015.
 */
public class UrlAdapter extends BaseAdapter {

    private List<Url> urls = new ArrayList<>();
    private Context context;

    public UrlAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Url getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UrlHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.url_row, parent, false);
            holder = new UrlHolder(convertView);
        } else holder = (UrlHolder) convertView.getTag();
        Url url = urls.get(position);
        holder.tvUrl.setText(url.account().toString());
        return convertView;
    }

    public boolean addAll(List<Url> urls) {
        boolean added = urls.addAll(urls);
        if (added) notifyDataSetChanged();
        return added;
    }

    public boolean add(Url url) {
        boolean added = urls.add(url);
        if (added) notifyDataSetChanged();
        return added;
    }

    static class UrlHolder {
        TextView tvUrl;

        public UrlHolder(View view) {
            this.tvUrl = (TextView) view.findViewById(R.id.tvUrl);
            view.setTag(this);
        }
    }
}
