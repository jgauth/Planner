package com.jgauth.planner;

import java.util.Date;

/**
 * Created by John Gauthier on 3/29/18.
 */

public class HomeworkItem {

    private String itemName;
    private Date itemDate;
    private String itemCourse;

    public HomeworkItem(String itemName, Date itemDate, String itemCourse) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.itemCourse = itemCourse;
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