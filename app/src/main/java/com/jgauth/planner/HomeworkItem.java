package com.jgauth.planner;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Created by John Gauthier on 3/29/18.
 */

public class HomeworkItem implements Comparable<HomeworkItem> {

    private String mItemName;
    private Date mItemDate;
    private String mItemCourse;
    private UUID mItemID;

    public HomeworkItem(String itemName, Date itemDate, String itemCourse) {
        this.mItemName = itemName;
        this.mItemDate = itemDate;
        this.mItemCourse = itemCourse;
        this.mItemID = UUID.randomUUID();
    }

    // GETTERS
    public String getItemName() {
        return this.mItemName;
    }

    public Date getItemDate() {
        return this.mItemDate;
    }

    public String getItemCourse() {
        return this.mItemCourse;
    }

    public UUID getID() {
        return this.mItemID;
    }


    // SETTERS
    public void setItemName(String name){
        this.mItemName = name;
    }

    public void setItemDate(Date date) {
        this.mItemDate = date;
    }

    public void setItemCourse(String course) {
        this.mItemCourse = course;
    }


    // COMPARABLE IMPLEMENTATION
    @Override
    public int compareTo(@NonNull HomeworkItem homeworkItem) {
        return this.mItemDate.compareTo(homeworkItem.getItemDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof HomeworkItem))
            return false;

        HomeworkItem that = (HomeworkItem) obj;
        return (this.getItemName().equals(that.getItemName()) && this.getItemDate() == that.getItemDate() && this.getItemCourse().equals(that.getItemCourse()));
    }
}