package android.dating.ust.com.starter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter
 * Created by Ярослав on 10.02.2015.
 */
public class SearchAdapter extends BaseAdapter {

    private static final String TAG = "searchAdapter";
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
        SearchItem item = items.get(position);
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
        holder.tvName.setText(item.name());
        holder.tvAge.setText(item.age().toString());
        // Return the View you just created
        return convertView;

    }

    static class ItemHolder {
        TextView tvName;
        TextView tvAge;

        public ItemHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvAge = (TextView) view.findViewById(R.id.tvAge);
            view.setTag(this);
        }
    }

    public void add(SearchItem searchItem) {
        items.add(searchItem);
        notifyDataSetChanged();
    }
}
