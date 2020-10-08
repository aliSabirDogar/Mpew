package com.radiocodeford.mpew;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kakashi on 04/06/2017.
 */

public class Notification implements Serializable {
    private String description;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    public Notification(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Notification(String description, Date date){
        this.description= description;
        this.date = date;
    }

}
