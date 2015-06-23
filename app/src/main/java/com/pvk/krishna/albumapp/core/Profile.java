package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 30/05/2015.
 */
public class Profile {

    private String fName;
    private String mName;
    private String lName;
    private String email;

    public Profile(String fName, String mName, String lName, String email) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.email = email;
    }

    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
