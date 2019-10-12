package com.example.owner_app.listitems;

public class owner_listitem  {
    private String player_name;
    private String player_phone;
    private String elsa3ah;
    private String Date;



    public String getDate() {
        return Date;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public String getPlayer_phone() {
        return player_phone;
    }

    public String getElsa3ah() {
        return elsa3ah;
    }

    public owner_listitem(String player_name, String player_phone, String elsa3ah, String date) {
        this.player_name = player_name;
        this.player_phone = player_phone;
        this.elsa3ah = elsa3ah;
        Date = date;
    }
}
