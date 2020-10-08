package com.radiocodeford.mpew;

import java.io.Serializable;

/**
 * Created by thiba on 29/01/2017.
 */

public class Contact implements Serializable {
    private String name;
    private String phoneNo;
    private String mail;
    private String note;
    private String fixe;
    private String id;
    private String androidId;
    private Boolean checked;
    private String urlImage;
    private Boolean invited;
    private String firstName;
    private String adress;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    private String Address;

    public String getAndroidContactId() {
        return androidContactId;
    }

    public void setAndroidContactId(String androidContactId) {
        this.androidContactId = androidContactId;
    }

    private String androidContactId;


    public Contact(){
        this.setUrlImage("https://firebasestorage.googleapis.com/v0/b/call-transfert.appspot.com/o/contact.png?alt=media&token=2b127619-a2e8-4232-888e-29d29ec13ea9");
        this.setInvited(false);
    }

    public Contact(String name, String firstname, String phoneNo, String id, String email) {
        this.name = name;
        this.firstName = firstname;
        this.phoneNo = phoneNo;
        this.id = id;
        this.invited = false;
        this.checked = false;
        this.urlImage = "https://firebasestorage.googleapis.com/v0/b/call-transfert.appspot.com/o/contact.png?alt=media&token=2b127619-a2e8-4232-888e-29d29ec13ea9";
        this.mail=email;


    }


    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public Boolean getCheked() {
        return checked;
    }

    public void setCheked(Boolean cheked) {
        this.checked = cheked;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }


    public Boolean getInvited() {
        return invited;
    }

    public void setInvited(Boolean invited) {
        this.invited = invited;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }
}
