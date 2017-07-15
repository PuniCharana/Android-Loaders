package com.ubuntu.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu-14 on 15/7/17.
 */

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, List<Person> persons) {
        super(context, 0, persons);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_person, parent, false);
        }

        Person person = getItem(position);

        TextView pos = listItemView.findViewById(R.id.position);
        TextView fullName = listItemView.findViewById(R.id.full_name);
        TextView gender = listItemView.findViewById(R.id.gender);
        TextView dob = listItemView.findViewById(R.id.dob);

        pos.setText(Integer.toString(position+1));
        fullName.setText(person.getFname()+" "+person.getLname());
        gender.setText(person.getGender()+",");

        Date date = new Date(person.getDob());
        dob.setText(formatDate(date));


        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
