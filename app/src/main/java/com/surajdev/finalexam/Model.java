package com.surajdev.finalexam;

import java.io.Serializable;

public class Model implements Serializable {

    double lat, longi;

    public Model() {
    }

    public Model(double lat, double longi) {
        this.lat = lat;
        this.longi = longi;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    @Override
    public String toString() {
        return "Model{" +
                "lat=" + lat +
                ", longi=" + longi +
                '}';
    }
}
