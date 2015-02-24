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


public class MainActivity extends ListActivity {

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

        setContentView(R.layout.activity_main);
        // detailMessage = {java.lang.String@3589}"Your content must have a ListView whose id attribute is 'android.R.id.list'"
        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }

        if (savedInstanceState != null) {
            mResume = savedInstanceState.getInt(CREATE);
            mStart = savedInstanceState.getInt(START);
            mRestart = savedInstanceState.getInt(RESTART);
            mResume = savedInstanceState.getInt(RESUME);
        }

        getListView().setFooterDividersEnabled(true);
        setListAdapter(new SearchAdapter());
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
                startActivity(new Intent(MainActivity.this, AddNewActivity.class));
            }
        });

        // TODO - Attach the adapter to this ListActivity's ListView

        Log.i(TAG, "Entered the onCreate() method");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Entered onActivityResult()");

        // TODO - Check result code and request code
        // if user submitted a new ToDoItem
        // Create a new ToDoItem from the data Intent
        // and then add it to the adapter
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CREATE, mCreate);
        outState.putInt(START, mStart);
        outState.putInt(RESTART, mRestart);
        outState.putInt(RESUME, mResume);
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
