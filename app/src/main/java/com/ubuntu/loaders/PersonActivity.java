package com.ubuntu.loaders;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    // Loader id
    private static final int PERSONS_LOADER_ID = 1;

    // url to load data from
    private static final String URL_TO_TOAD = "http://www.google.com";

    // Adapter for list of persons
    private PersonAdapter personAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);


        // Find a reference to the {@link ListView} in the layout
        ListView personListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        personListView.setEmptyView(mEmptyStateTextView);


        // Create a new adapter that takes an empty list of earthquakes as input
        personAdapter = new PersonAdapter(this, new ArrayList<Person>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        personListView.setAdapter(personAdapter);

        // Set an item click listener on the ListView.
        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person person = personAdapter.getItem(i);

                Toast.makeText(getApplicationContext(), person.getFname()+" "+person.getLname(), Toast.LENGTH_SHORT).show();
            }
        });



        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getSupportLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(PERSONS_LOADER_ID, null, loaderCallbacks);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }


    // Note : Instead of doing this way we can let PersonActivity implements the LoaderManager.LoaderCallbacks<List<Person>>
    // if PersonActivity implements the LoaderManager.LoaderCallbacks<List<Person>>
    // on initLoader instead of loaderCallbacks pass this as argument
    private LoaderManager.LoaderCallbacks<List<Person>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<Person>>() {
        @Override
        public Loader<List<Person>> onCreateLoader(int id, Bundle args) {
            return new PersonLoader(getApplicationContext(), URL_TO_TOAD);
        }

        @Override
        public void onLoadFinished(Loader<List<Person>> loader, List<Person> data) {

            // Hide loading indicator because the data has been loaded
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Set empty state text to display "No person found."
            mEmptyStateTextView.setText(R.string.no_person);

            personAdapter.clear();

            if (data!=null && !data.isEmpty()) {
                personAdapter.addAll(data);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Person>> loader) {
            personAdapter.clear();
        }
    };
}

