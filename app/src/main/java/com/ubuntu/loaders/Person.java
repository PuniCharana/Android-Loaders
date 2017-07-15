package com.ubuntu.loaders;

/**
 * Created by ubuntu-14 on 15/7/17.
 */

class Person {
    private String fname;
    private String lname;
    private String gender;
    private long dob;

    public Person(String fname, String lname, String gender, long dob) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public long getDob() {
        return dob;
    }
}
