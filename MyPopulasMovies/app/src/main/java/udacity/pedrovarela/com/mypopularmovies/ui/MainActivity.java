package udacity.pedrovarela.com.mypopularmovies.ui;import android.app.Activity;import android.content.Intent;import android.os.Bundle;import android.preference.PreferenceManager;import android.util.Log;import android.view.Menu;import android.view.MenuItem;import udacity.pedrovarela.com.mypopularmovies.R;public class MainActivity extends Activity {    private static final String DETAILFRAGMENT_TAG = "DetailFragmentTag";    private boolean mTwoPane = false;    @Override    protected void onCreate(Bundle savedInstanceState) {        Log.d("LC", "onCreate");        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        /**         * {@link http://developer.android.com/guide/topics/ui/settings.html#Defaults}         */        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);        if (findViewById(R.id.fragment_detail_container) != null) {            mTwoPane = true;            /**             * Note how in the two pane case we check if the saved instance state is null. Why? Well if we rotate the phone, the system saves the fragment state in the saved state bundle and is smart enough to restore this state.             Therefore, if the saved state bundle is not null, the system already has the fragment it needs and you shouldn’t go adding another one.             */            if (savedInstanceState == null) {                getFragmentManager().beginTransaction().replace(R.id.fragment_detail_container, new DetailActivityFragment(), DETAILFRAGMENT_TAG).commit();            }        } else {            mTwoPane = false;        }    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        // Inflate the menu; this adds items to the action bar if it is present.        getMenuInflater().inflate(R.menu.menu_main, menu);        return true;    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        // Handle action bar item clicks here. The action bar will        // automatically handle clicks on the Home/Up button, so long        // as you specify a parent activity in AndroidManifest.xml.        int id = item.getItemId();        if (id == R.id.action_settings) {            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);            startActivity(intent);        }        return super.onOptionsItemSelected(item);    }    @Override    protected void onStart() {        super.onStart();        Log.d("LC", "ONSTART");        // The activity is about to become visible.    }    @Override    protected void onResume() {        super.onResume();        Log.d("LC", "ONRESUME");        // The activity has become visible (it is now "resumed").    }    @Override    protected void onPause() {        super.onPause();        Log.d("LC", "onPAUSE");        // Another activity is taking focus (this activity is about to be "paused").    }    @Override    protected void onStop() {        super.onStop();        Log.d("LC", "onSTOP");        // The activity is no longer visible (it is now "stopped")    }    @Override    protected void onDestroy() {        Log.d("LC","onDESTROY");        super.onDestroy();        // The activity is about to be destroyed.    }}