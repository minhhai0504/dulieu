package com.example.dulieu.ui.nguoidungfrag;

public class UserModel {
    public String username;
    public String password;
    public String hoten;
    public String sdt;

    public UserModel() {
    }

    public UserModel(String username, String password, String hoten, String sdt) {
        this.username = username;
        this.password = password;
        this.hoten = hoten;
        this.sdt = sdt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
