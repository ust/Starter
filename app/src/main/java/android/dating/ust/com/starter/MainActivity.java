package android.dating.ust.com.starter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends Activity {

    public static final String TAG = "lab";
    private Integer mCreate = 0, mRestart = 0, mStart = 0, mResume = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "mCreate " + ++mCreate);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        ((TextView) findViewById(R.id.tvCreate)).setText(mCreate.toString());
        ((TextView) findViewById(R.id.tvStart)).setText(mStart.toString());
        ((TextView) findViewById(R.id.tvRestart)).setText(mRestart.toString());
        ((TextView) findViewById(R.id.tvResume)).setText(mResume.toString());

        // Has previous state been saved?
        if (savedInstanceState != null) {
            // TODO:
            // Restore value of counters from saved state
            // Only need 4 lines of code, one for every count variable

        }

        // Emit LogCat message
        Log.i(TAG, "Entered the onCreate() method");

        // TODO:
        // Update the appropriate count variable
        // Update the user interface via the displayCounts() method

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "mRestart " + ++mRestart);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "mStart " + ++mStart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "mResume " + ++mResume);
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
                    startActivity(new Intent(getActivity(), ResultActivity.class));
                }
            });

            return rootView;
        }
    }
}
