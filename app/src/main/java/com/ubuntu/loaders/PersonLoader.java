package com.ubuntu.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu-14 on 15/7/17.
 */

public class PersonLoader extends AsyncTaskLoader<List<Person>> {


    /** Query URL */
    private String mUrl;

    /**
     *
     * @param context of the activity
     * @param url to load data from
     */
    public PersonLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // force loadInBackground() to be called
    }

    @Override
    public List<Person> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        List<Person> personList;

        /*
        * Make http call to the given url
        * */
//        String personJSON = QueryUtils.fetchPersonData(mUrl);
//        personList = extractFeatureFromJson(personJSON);

        /*
        * Create dummy data
        * */
//        List<Person> personList = new ArrayList<>();
//        for (int i=1; i <= 20; i++) {
//            Person person = new Person("Puni "+i, "Charana", "Male", 1934567890098L);
//            personList.add(person);
//        }

        /*
        * Fetch data locally
        * Json file under assets
        * */
        String personJson = loadJSONFromAsset();
        personList = extractFeatureFromJson(personJson);

        return personList;
    }

    // Load data locally
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getContext().getAssets().open("persons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void deliverResult(List<Person> data) {
        super.deliverResult(data);
    }


    private static List<Person> extractFeatureFromJson(String personJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(personJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding person to
        List<Person> personList = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONArray jsonArray = new JSONArray(personJSON);

            // For each person in the personsArray, create an  Person object
            for (int i = 0; i < jsonArray.length(); i++) {

                // Get a single person at position i within the list of persons
                JSONObject currentPerson = jsonArray.getJSONObject(i);

                String fname = currentPerson.getString("first_name");
                String lname = currentPerson.getString("last_name");
                String gender = currentPerson.getString("gender");
                long dob = currentPerson.getLong("dob");

                // Create a new Person object with first name, last name, gender, dob,
                Person person = new Person(fname, lname, gender, dob);

                // Add the new Person to the list of persons.
                personList.add(person);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the person JSON results", e);
        }

        // Return the list of persons
        return personList;
    }
}
