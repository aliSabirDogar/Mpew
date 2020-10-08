package com.radiocodeford.mpew;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by thiba on 29/01/2017.
 */


/** UserService Mapping class**/

public class User {

    private String name;
    private String firstname;
    private String email;
    private String phone;
    private Date enddate;

    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }
    private String iemi;
    private String id;
    private String pushId;
    private HashMap<String, Contact> contacts;

    private HashMap<String, Groupe> groupes;
    private String code_pays;

    public HashMap<String, Contact> getNotify_contact() {
        return notify_contact;
    }

    public void setNotify_contact(HashMap<String, Contact> notify_contact) {
        this.notify_contact = notify_contact;
    }

    private HashMap<String, Contact> notify_contact;



    public User(){
    }



    public User(String name, String firstname, String email, String phone, HashMap<String, Contact> contacts) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
        this.contacts = contacts;
        urlImage = "https://firebasestorage.googleapis.com/v0/b/call-transfert.appspot.com/o/contact.png?alt=media&token=2b127619-a2e8-4232-888e-29d29ec13ea9";
        this.groupes = new HashMap<>();
        notify_contact = new HashMap<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getCode_pays() {
        return code_pays;
    }

    public void setCode_pays(String code_pays) {
        this.code_pays = code_pays;
    }
    public HashMap<String, Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(HashMap<String, Notification> notifications) {
        this.notifications = notifications;
    }

    private HashMap<String, Notification> notifications;


    public HashMap<String, String> getGroupesID() {
        return groupesID;
    }

    public void setGroupesID(HashMap<String, String> groupesID) {
        this.groupesID = groupesID;
    }

    private HashMap<String, String> groupesID;


    public HashMap<String, Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(HashMap<String, Groupe> groupes) {
        this.groupes = groupes;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    private String urlImage;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
