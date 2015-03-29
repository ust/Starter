package android.dating.ust.com.starter;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;


public class AddNewActivity extends ListActivity {



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

    private Long id;
    private TextView tvName;
    private TextView tvAge;
    private Button btnSearch;
    private ProgressBar progressBar;
    private UrlAdapter adapter;
    private SearchTask task;


    private Handler handler;

    private static class SearchHandler extends Handler {

        private final WeakReference<AddNewActivity> holder;

        public SearchHandler(AddNewActivity holder) {
            this.holder = new WeakReference<>(holder);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (SearchTask.Messages.values()[msg.what]) {
                case progress:
                    setProgress(msg.arg1);
                    break;
                case found:
                    foundUrl((Url) msg.obj);
                    break;
                case finish:
                    finish();
                    break;
                default:
            }
        }

        private void foundUrl(Url url) {
            AddNewActivity activity = holder.get();
            if (activity == null) return;
            activity.adapter.add(url);
        }

        private void setProgress(int progress) {
            AddNewActivity activity = holder.get();
            if (activity == null) return;
            activity.progressBar.setProgress(progress);
        }

        private void finish() {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        tvName = (TextView) findViewById(R.id.etName);
        tvAge = (TextView) findViewById(R.id.etAge);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationContext.item(item());
                setResult(ResultType.ok.code);
                finish();
            }
        });
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        adapter = new UrlAdapter(this);
        setListAdapter(adapter);
        handler = new SearchHandler(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        final SearchItem item = ApplicationContext.item();
        if (item != null) {
            id = item.id();
            tvName.setText(item.name());
            tvAge.setText(item.age().toString());
            adapter.addAll(item.urls());
        }
    }

    private void search() {
        if (task == null) {
            task = new SearchTask(handler, item());
            tvName.setEnabled(false);
            tvAge.setEnabled(false);
            btnSearch.setText("Pause");
            task.start();
        } else {
            task.stop();
            btnSearch.setText("Restart");
            tvName.setEnabled(true);
            tvAge.setEnabled(true);
            task = null;
        }
    }

    private SearchItem item() {
        return new SearchItem(id(), name(), age(), urls());
    }

    private Long id() {
        return id != null ? id : System.currentTimeMillis();
    }

    private String name() {
        return tvName.getText().toString();
    }

    private Byte age() {
        return Byte.parseByte(tvAge.getText().toString());
    }

    private List<Url> urls() {
        return adapter.urls();
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

}
