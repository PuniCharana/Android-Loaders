package com.ubuntu.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

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
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // force loadInBackground() to be called
    }

    @Override
    public List<Person> loadInBackground() {

        List<Person> personList = new ArrayList<>();
        for (int i=1; i <= 20; i++) {
            Person person = new Person("Puni", "Charana", "Male", 1234567890098L);
            personList.add(person);
        }

        return personList;
    }

    @Override
    public void deliverResult(List<Person> data) {
        super.deliverResult(data);
    }
}
