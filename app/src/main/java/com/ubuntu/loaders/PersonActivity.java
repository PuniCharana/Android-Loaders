package com.ubuntu.loaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private PersonAdapter personAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);


        // Find a reference to the {@link ListView} in the layout
        ListView personListView = (ListView) findViewById(R.id.list);


        // Create a new adapter that takes an empty list of earthquakes as input
        personAdapter = new PersonAdapter(this, new ArrayList<Person>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        personListView.setAdapter(personAdapter);

        // Add dummy data
        addDummyData();
    }

    private void addDummyData(){
        List<Person> personList = new ArrayList<Person>();
        for (int i=1; i <= 20; i++) {
            Person person = new Person("Puni", "Charana", "Male", 1234567890098L);
            personList.add(person);
        }

        personAdapter.addAll(personList);
    }
}
