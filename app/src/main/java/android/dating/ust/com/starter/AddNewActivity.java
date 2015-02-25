package android.dating.ust.com.starter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class AddNewActivity extends Activity {

    public static final String TAG = "lab";

    public enum ResultType {
        ok(1);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "mCreate ");

        setContentView(R.layout.activity_result);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (savedInstanceState != null) {
        }
        displayCounters();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "mStart ");
        displayCounters();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "mRestart ");
        displayCounters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "mResume ");
        displayCounters();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
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

    private void displayCounters() {
    }

}
