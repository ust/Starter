package android.dating.ust.com.starter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO - Get the current ToDoItem
        //final ToDoItem toDoItem = null;

        // TODO - Inflate the View for this ToDoItem
        // from todo_item.xml
        RelativeLayout itemLayout = null;

        // TODO - Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file

        // TODO - Display Title in TextView
        final TextView titleView = null;

        // TODO - Set up Status CheckBox
        final CheckBox statusView = null;

        statusView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.i(TAG, "Entered onCheckedChanged()");

                // TODO - set up an OnCheckedChangeListener, which
                // is called when the user toggles the status checkbox

            }
        });

        // TODO - Display Priority in a TextView

        final TextView priorityView = null;

        // TODO - Display Time and Date.
        // Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
        // time String

        final TextView dateView = null;

        // Return the View you just created
        return itemLayout;

    }

    public void add(SearchItem searchItem) {
        items.add(searchItem);
        notifyDataSetChanged();
    }
}
