package com.radiocodeford.mpew;

public class Contactlist {
    public Contactlist(String phone, String email, String name) {
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String phone,email,name;
}
