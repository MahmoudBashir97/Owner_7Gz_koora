package com.example.owner_app.pojo;

public class Save_Data {
    String user_name;
    String hagz_date;
    String hour_booking;
    String Ml3b_name;

    public Save_Data(String user_name, String hagz_date, String hour_booking, String ml3b_name) {
        this.user_name = user_name;
        this.hagz_date = hagz_date;
        this.hour_booking = hour_booking;
        Ml3b_name = ml3b_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHagz_date() {
        return hagz_date;
    }

    public void setHagz_date(String hagz_date) {
        this.hagz_date = hagz_date;
    }

    public String getHour_booking() {
        return hour_booking;
    }

    public void setHour_booking(String hour_booking) {
        this.hour_booking = hour_booking;
    }

    public String getMl3b_name() {
        return Ml3b_name;
    }

    public void setMl3b_name(String ml3b_name) {
        Ml3b_name = ml3b_name;
    }
}
