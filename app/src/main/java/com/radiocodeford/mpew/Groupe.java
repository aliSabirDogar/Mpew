package com.radiocodeford.mpew;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Created by Kakashi on 23/02/2017.
 */

public class Groupe implements Serializable {
    private String name;
    private String description;
    private String urlImage;
   public static HashMap<String, Contactlist> contacts;
    public Groupe(){
        this.urlImage = "https://firebasestorage.googleapis.com/v0/b/call-transfert.appspot.com/o/teamwork.png?alt=media&token=2dff1833-339e-42b1-937e-302769995887";
    }
    public Groupe(String name, String description, HashMap<String, Contactlist> contacts){
        this.name = name;
        this.description = description;
        this.contacts = contacts;
        this.urlImage = "https://firebasestorage.googleapis.com/v0/b/call-transfert.appspot.com/o/teamwork.png?alt=media&token=2dff1833-339e-42b1-937e-302769995887";
    }

    public HashMap<String, Contactlist> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<String, Contactlist> contacts) {
        this.contacts = contacts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
