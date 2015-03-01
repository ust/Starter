package android.dating.ust.com.starter;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class MainActivity extends ListActivity {

    public static final String TAG = "main_activity";
    private static final String FILE_NAME = "starter.dat";

    enum RequestType {
        addNew(1);

        private int code;

        RequestType(int code) {
            this.code = code;
        }

        public static RequestType of(int code) {
            for (RequestType r : values())
                if (r.code == code)
                    return r;
            return null;
        }
    }

    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            // getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }

        getListView().setFooterDividersEnabled(true);
        // Inflate footerView for footer_view.xml file
        TextView footer = (TextView) getLayoutInflater().inflate(R.layout.new_search_item, null);
        // detailMessage = {java.lang.String@3592}"addView(View, LayoutParams) is not supported in AdapterView"
        // Add footerView to ListView
        this.getListView().addFooterView(footer);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered footerView.OnClickListener.onClick()");
                // launch add new activity
                startActivityForResult(new Intent(MainActivity.this, AddNewActivity.class), RequestType.addNew.code);
            }
        });
        // Attach the adapter to this ListActivity's ListView
        adapter = new SearchAdapter(getBaseContext());
        setListAdapter(adapter);
        Log.i(TAG, "Entered the onCreate() method");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Entered onActivityResult()");
        // Check result code and request code
        switch (RequestType.of(requestCode)) {
            case addNew:
                switch (AddNewActivity.ResultType.of(resultCode)) {
                    case ok:
                        // if user submitted a new ToDoItem
                        addSearchItem(data);
                        break;
                }
                break;
        }
    }

    private void addSearchItem(Intent data) {
        // Create a new ToDoItem from the data Intent
        // and then add it to the adapter
        adapter.add(new SearchItem(data));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save ToDoItems
        saveItems();
    }

    // Save ToDoItems to file
    private void saveItems() {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE); //
             PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                     fos)))) {

            for (int i = 0; i < getListAdapter().getCount(); i++)
                writer.println(getListAdapter().getItem(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load saved ToDoItems, if necessary
        if (getListAdapter().getCount() == 0)
            loadItems();
    }

    // Load stored ToDoItems
    private void loadItems() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            for (String id = reader.readLine(); id != null; id = reader.readLine())
                adapter.add(new SearchItem(Long.parseLong(id), reader.readLine(), Byte.parseByte(reader.readLine())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            rootView.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), AddNewActivity.class));
                }
            });

            return rootView;
        }
    }

}
