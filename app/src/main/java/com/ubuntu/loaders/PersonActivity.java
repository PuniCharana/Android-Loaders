package com.ubuntu.loaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

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

        // Add dummy data
        addDummyData();
    }

    private void addDummyData(){

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No person found."
        mEmptyStateTextView.setText(R.string.no_person);

        List<Person> personList = new ArrayList<>();
        for (int i=1; i <= 20; i++) {
            Person person = new Person("Puni", "Charana", "Male", 1234567890098L);
            personList.add(person);
        }

        personAdapter.addAll(personList);
    }
}

