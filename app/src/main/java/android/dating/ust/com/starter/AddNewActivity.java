package android.dating.ust.com.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;


public class AddNewActivity extends ListActivity {

    private TextView tvName;
    private TextView tvAge;
    private UrlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        final SearchItem item = SearchItem.from(getIntent());
        tvName = (TextView) findViewById(R.id.etName);
        tvAge = (TextView) findViewById(R.id.etAge);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ResultType.ok.code, //
                        new SearchItem(item != null ? item.id() : id(), name(), age()).putExtras(new Intent()));
                finish();
            }
        });
        adapter = new UrlAdapter(this);
        setListAdapter(adapter);
        if (item != null) {
            tvName.setText(item.name());
            tvAge.setText(item.age().toString());
            adapter.addAll(item.urls());
        }
    }

    private void search() {
        // Search
        // add urls to list
        try {
            adapter.add(new Url(new URL("http://bla.com"), new URL("http://bla.com/nothing.jpg")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Byte age() {
        return Byte.parseByte(tvAge.getText().toString());
    }

    private String name() {
        return tvName.getText().toString();
    }

    private Long id() {
        return System.currentTimeMillis();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stupid, menu);
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

    public enum ResultType {
        cancel(RESULT_CANCELED), ok(RESULT_OK);

        private int code;

        ResultType(int code) {
            this.code = code;
        }

        public static ResultType of(int code) {
            for (ResultType r : values())
                if (r.code == code)
                    return r;
            return null;
        }
    }
}
