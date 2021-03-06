package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 30/05/2015.
 */
public class Registration {
    private String fName;
    private String mName;
    private String lName;
    private String email;
    private String password;

    public Registration(String fName, String mName, String lName, String email, String password) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
