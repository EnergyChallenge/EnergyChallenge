package de.unikiel.klik.energychallenge.activities;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.adapters.SearchResultAdapter;
import de.unikiel.klik.energychallenge.tasks.PerformSearchTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

public class SearchActivity extends ListActivity {

    private SearchResultAdapter searchAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_extended);

        progressIndicator = (LinearLayout) findViewById(R.id.progress_container_id);
        emptyListText = (TextView) findViewById(R.id.empty_id);

        searchAdapter = new SearchResultAdapter(this);

        setListAdapter(searchAdapter);

        handleIntent(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }



    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            loadSearchData(query);
        }
    }

    private void loadSearchData(String query) {

        if (NetworkX.isAvailable(this)) {
            new PerformSearchTask(searchAdapter, progressIndicator, emptyListText).execute();
        } else {
            emptyListText.setText(R.string.no_network_connection);
            Toast.makeText(this, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("type", searchAdapter.getItem(position).getType());
        profileIntent.putExtra("id", searchAdapter.getItem(position).getId());
        startActivity(profileIntent);

    }


}
