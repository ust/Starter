package android.dating.ust.com.starter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends Activity {

    public static final String TAG = "lab";
    public static final String CREATE = "mCreate";
    public static final String START = "mStart";
    public static final String RESTART = "mRestart";
    public static final String RESUME = "mResume";
    private Integer mCreate = 0, mRestart = 0, mStart = 0, mResume = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "mCreate " + ++mCreate);

        setContentView(R.layout.activity_result);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (savedInstanceState != null) {
            mResume = savedInstanceState.getInt(CREATE);
            mStart = savedInstanceState.getInt(START);
            mRestart = savedInstanceState.getInt(RESTART);
            mResume = savedInstanceState.getInt(RESUME);
        }
        displayCounters();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "mStart " + ++mStart);
        displayCounters();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "mRestart " + ++mRestart);
        displayCounters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "mResume " + ++mResume);
        displayCounters();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CREATE, mCreate);
        outState.putInt(START, mStart);
        outState.putInt(RESTART, mRestart);
        outState.putInt(RESUME, mResume);
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
        ((TextView) findViewById(R.id.tvCreate)).setText(mCreate.toString());
        ((TextView) findViewById(R.id.tvStart)).setText(mStart.toString());
        ((TextView) findViewById(R.id.tvRestart)).setText(mRestart.toString());
        ((TextView) findViewById(R.id.tvResume)).setText(mResume.toString());
    }

}
