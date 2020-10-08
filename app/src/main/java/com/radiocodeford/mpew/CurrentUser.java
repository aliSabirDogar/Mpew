package com.radiocodeford.mpew;


import java.util.Date;
import java.util.HashMap;



/**
 * Created by thiba on 30/01/2017.
 */

public class CurrentUser {


    public final static String serverKey = "AAAA9NT4dlU:APA91bH4I6U80qIPo7xc3BAoVHnGBrNd0xiA6_ZKbaUvBQu-owMLEH1jvwVR6tVJY2s2zPbuUDYg0LG3OczLmer1CHELOAE4zMMpGmJhj60GhD1LSWdJvffsbQo3FxDLTywbmPQdcrmn";

    private CurrentUser()
    {}

    /** Instance unique pré-initialisée */
    private static CurrentUser  INSTANCE = new CurrentUser ();

    /** Point d'accès pour l'instance unique du singleton */
    public static CurrentUser  getInstance()
    {	return INSTANCE;
    }

    private String pushId;
    private String name;
    private String firstname;
    private String email;
    private String phone;
    private String fixe;
    private String note;

    private Date enddate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public Boolean getOfflineMode() {
        return offlineMode;
    }

    public void setOfflineMode(Boolean offlineMode) {
        this.offlineMode = offlineMode;
    }

    private Boolean offlineMode;
    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    private String iemi;
    private HashMap<String, Groupe> groupes;
    private HashMap<String, Notification> notifications;

    private String code_pays;

    public HashMap<String, Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(HashMap<String, Notification> notifications) {
        this.notifications = notifications;
    }

    public HashMap<String, String> getGroupeID() {
        return groupeID;
    }

    public void setGroupeID(HashMap<String, String> groupeID) {
        this.groupeID = groupeID;
    }

    private HashMap<String, String> groupeID;

    public HashMap<String, Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(HashMap<String, Groupe> groupes) {
        this.groupes = groupes;
    }
    public HashMap<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
    }

    private HashMap<String, Contact> contacts;


    public HashMap<String, Contact> getNotify_contact() {
        return notify_contact;
    }

    public void setNotify_contact(HashMap<String, Contact> notify_contact) {
        this.notify_contact = notify_contact;
    }

    private HashMap<String, Contact> notify_contact;
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    private String urlImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public void setUser (User u)
    {
        if(u != null) {
            this.name = u.getName();
            this.firstname = u.getFirstname();
            this.email = u.getEmail();
            this.phone = u.getPhone();
            this.contacts = u.getContacts();

            this.iemi = u.getIemi();
            this.urlImage = u.getUrlImage();
            this.groupes = u.getGroupes();
            this.groupeID = u.getGroupesID();
            this.setCode_pays(u.getCode_pays());
            this.offlineMode = false;
            this.enddate = u.getEnddate();
            this.notify_contact = u.getNotify_contact();

        }
    }




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


    public String getCode_pays() {
        return code_pays;
    }

    public void setCode_pays(String code_pays) {
        this.code_pays = code_pays;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
