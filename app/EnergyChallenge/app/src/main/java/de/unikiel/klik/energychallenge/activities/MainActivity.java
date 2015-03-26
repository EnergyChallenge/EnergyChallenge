package de.unikiel.klik.energychallenge.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.widget.SearchView;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.fragments.ActivitiesFragment;
import de.unikiel.klik.energychallenge.fragments.MainFragment;
import de.unikiel.klik.energychallenge.fragments.NavigationDrawerFragment;
import de.unikiel.klik.energychallenge.fragments.OptionsFragment;
import de.unikiel.klik.energychallenge.fragments.ProfileFragment;
import de.unikiel.klik.energychallenge.fragments.ProposalsFragment;
import de.unikiel.klik.energychallenge.fragments.RankingSliderFragment;
import de.unikiel.klik.energychallenge.services.NotificationService;


/* Main app activity */
public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Fragment that manges the behaviour, interaction and appearance of the navigation drawer
    private NavigationDrawerFragment mainNavigationDrawerFragment;

    // The current title viewable on the activity
    private CharSequence currentTitle;



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


        //Start the notifications service
        Intent notificationsIntent = new Intent(this.getApplicationContext(), NotificationService.class);
        notificationsIntent.putExtra("KEY", "Values to pass to service");
        this.getApplicationContext().startService(notificationsIntent);

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
                        .replace(R.id.container, ProfileFragment.newInstance())
                        .commit();
                break;
            case 3:
                //Ranking list fragment
                currentTitle = getString(R.string.navigation_option3);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RankingSliderFragment.newInstance())
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

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            return true;
        }
        return super.onCreateOptionsMenu(menu);

    }


}
