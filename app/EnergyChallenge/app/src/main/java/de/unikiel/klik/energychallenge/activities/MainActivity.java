package de.unikiel.klik.energychallenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import de.unikiel.klik.energychallenge.ExampleFragment;
import de.unikiel.klik.energychallenge.JsonTestFragment;
import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.fragments.ActivitiesFragment;
import de.unikiel.klik.energychallenge.fragments.MainFragment;
import de.unikiel.klik.energychallenge.fragments.MyProfileFragment;
import de.unikiel.klik.energychallenge.fragments.NavigationDrawerFragment;
import de.unikiel.klik.energychallenge.fragments.OptionsFragment;
import de.unikiel.klik.energychallenge.fragments.ProposalsFragment;
import de.unikiel.klik.energychallenge.fragments.RankingListFragment;
import de.unikiel.klik.energychallenge.fragments.TeamRankingFragment;


/* Main app activity */
public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Fragment that manges the behaviour, interaction and appearance of the navigation drawer
    private NavigationDrawerFragment mainNavigationDrawerFragment;

    // The current title viewable on the activity
    private CharSequence currentTitle;

    // Temp Application context TODO
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Load the state and initial layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the navigation drawer
        mainNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        currentTitle = getTitle();

        mainNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    public void onSectionAttached(int position) {
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        //Fragment selectedFragment;

        //Swap in a new fragment to match the navigation selection and change the activity title
        switch (++position) {
            case 1:
                //Main fragment
                currentTitle = getString(R.string.navigation_option1);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commit();
                break;
            case 2:
                //My profile fragment
                currentTitle = getString(R.string.navigation_option2);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MyProfileFragment.newInstance())
                        .commit();
                break;
            case 3:
                //Ranking list fragment
                currentTitle = getString(R.string.navigation_option3);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RankingListFragment.newInstance())
                        .commit();
                break;
            case 4:
                //Activities fragment
                currentTitle = getString(R.string.navigation_option4);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ActivitiesFragment.newInstance())
                        .commit();
                break;
            case 5:
                //Proposals fragment
                currentTitle = getString(R.string.navigation_option5);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ProposalsFragment.newInstance())
                        .commit();
                break;
            case 6:
                //Options (settings) fragment
                currentTitle = getString(R.string.navigation_option6);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, OptionsFragment.newInstance())
                        .commit();
                break;
            case 7:
                //JSON Test fragment
                currentTitle = getString(R.string.navigation_option7);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, JsonTestFragment.newInstance())
                        .commit();
                break;
            case 8:
                //UI Example fragment
                currentTitle = getString(R.string.navigation_option8);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ExampleFragment.newInstance())
                        .commit();
                break;

        }
    }

    //Bring the action bar elements back after the drawer is closed
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(currentTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mainNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    // Handling action bar clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Get the action id
        int id = item.getItemId();

        //Execute the appropriate action
        switch (id) {
            case R.id.action_search:
                //Open the search activity
                openSearch();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
        //Open the search activity
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }

}
