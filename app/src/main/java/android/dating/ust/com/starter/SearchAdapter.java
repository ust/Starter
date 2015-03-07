package android.dating.ust.com.starter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter
 * Created by Ярослав on 10.02.2015.
 */
public class SearchAdapter extends BaseAdapter {

    private List<SearchItem> items = new ArrayList<>();
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public SearchItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the current ToDoItem
        final SearchItem item = items.get(position);
        // Inflate the View for this ToDoItem
        // from todo_item.xml
        ItemHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_item, parent, false);
            holder = new ItemHolder(convertView);
        } else holder = (ItemHolder) convertView.getTag();
        // Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file
        View.OnClickListener editor = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationContext.item(item);
                ((MainActivity) context).startActivityForResult(
                        new Intent(context, AddNewActivity.class),
                        MainActivity.RequestType.edit.code);
            }
        };
        holder.tvName.setOnClickListener(editor);
        holder.tvAge.setOnClickListener(editor);
        holder.tvName.setText(item.name());
        holder.tvAge.setText(item.age().toString());
        // Return the View you just created
        return convertView;

    }

    public void add(SearchItem searchItem) {
        items.add(searchItem);
        notifyDataSetChanged();
    }

    public void set(final SearchItem item) {
        int old = Iterables.indexOf(items, new Predicate<SearchItem>() {
            @Override
            public boolean apply(SearchItem input) {
                return input.id().equals(item.id());
            }
        });
        if (old != -1)
            items.set(old, item);
        else items.add(item);
        notifyDataSetChanged();
    }

    static class ItemHolder {
        TextView tvName;
        TextView tvAge;

        public ItemHolder(View view) {
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.tvAge = (TextView) view.findViewById(R.id.tvAge);
            view.setTag(this);
        }
    }

}
