package com.jgauth.planner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by John Gauthier on 3/29/18.
 */

public class HomeworkItem {

    private String itemName;
    private Date itemDate;
    private String itemCourse;
    private UUID itemID;

    public HomeworkItem(String itemName, Date itemDate, String itemCourse) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.itemCourse = itemCourse;
        this.itemID = UUID.randomUUID();
    }

    public String getItemName() {
        return this.itemName;
    }

    public Date getItemDate() {
        return this.itemDate;
    }

    public String getItemCourse() {
        return this.itemCourse;
    }

    public UUID getID() { return this.itemID; }


    public void setItemName(String name){
        this.itemName = name;
    }

    public void setItemDate(Date date) {
        this.itemDate = date;
    }

    public void setItemCourse(String course) {
        this.itemCourse = course;
    }
}