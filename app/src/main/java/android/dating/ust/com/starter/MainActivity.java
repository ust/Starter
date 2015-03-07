package android.dating.ust.com.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class MainActivity extends ListActivity {

    private static final String FILE_NAME = "starter.dat";

    private SearchAdapter adapter;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getListView().setFooterDividersEnabled(true);
        // Inflate footerView for footer_view.xml file
        TextView footer = (TextView) getLayoutInflater().inflate(R.layout.new_search_item, getListView(), false);
        // Add footerView to ListView
        this.getListView().addFooterView(footer);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch add new activity
                startActivityForResult(new Intent(MainActivity.this, AddNewActivity.class), RequestType.add.code);
            }
        });
        // Attach the adapter to this ListActivity's ListView
        adapter = new SearchAdapter(this);
        setListAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check result code and request code
        switch (RequestType.of(requestCode)) {
            case add:
                switch (AddNewActivity.ResultType.of(resultCode)) {
                    case cancel:
                        ApplicationContext.clearItem();
                        break;
                    case ok:
                        // if user submitted a new ToDoItem
                        // Create a new ToDoItem from the data Intent
                        // and then add it to the adapter
                        SearchItem item = ApplicationContext.item();
                        if (item != null) adapter.add(item);
                        ApplicationContext.item(null);
                        break;
                }
                break;
            case edit:
                switch (AddNewActivity.ResultType.of(resultCode)) {
                    case cancel:
                        ApplicationContext.clearItem();
                        break;
                    case ok:
                        adapter.set(ApplicationContext.item());
                        ApplicationContext.clearItem();
                        break;
                }
                break;
        }
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

            for (int i = 0; i < getListAdapter().getCount(); i++) {
                writer.println(gson.toJson(getListAdapter().getItem(i)));
            }
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
            for (String line = reader.readLine(); line != null; line = reader.readLine())
                adapter.add(gson.fromJson(line, SearchItem.class));
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

    enum RequestType {
        add(1), edit(2);

        int code;

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


}
