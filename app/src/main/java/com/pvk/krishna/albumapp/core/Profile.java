package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 30/05/2015.
 */
public class Profile {

    private String fName;
    private String mName;
    private String lName;
    private String email;
    private String dob;
    private String telephone;
    private String streetName;
    private String country;
    private String state;
    private String city;
    private String fbId;

    public Profile(){}

    public Profile(String fName, String mName, String lName, String email, String dob) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.email = email;
        this.dob = dob;
    }

    public Profile(String fName, String mName, String lName, String email, String dob, String telephone, String streetName, String country, String state, String city) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.email = email;
        this.dob = dob;
        this.telephone = telephone;
        this.streetName = streetName;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public String getlName() {
        return lName;
    }

    public String getCountry() {
        return country;
    }

    public String getfName() {
        return fName;
    }

    public String getDob() {
        return dob;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getmName() {
        return mName;
    }

    public String getState() {
        return state;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }
    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", telephone='" + telephone + '\'' +
                ", streetName='" + streetName + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", fbId='" + fbId + '\'' +
                '}';
    }
}
