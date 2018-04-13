package com.jgauth.planner;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

/**
 * Created by John Gauthier on 3/29/18.
 */

public class HomeworkItem implements Comparable<HomeworkItem>, Parcelable {

    private static final String TAG = "HomeworkItem";

    private String mItemName;
    private Date mItemDate;
    private String mItemCourse;
    private UUID mItemId;
    private int mHeaderId;

    public HomeworkItem(String itemName, Date itemDate, String itemCourse) {
        this.mItemName = itemName;
        this.mItemDate = itemDate;
        this.mItemCourse = itemCourse;
        this.mItemId = UUID.randomUUID();
        this.mHeaderId = Utils.getHeaderIdFromDate(mItemDate);
    }

    // GETTERS
    public String getItemName() {
        Log.i(TAG, "getItemName: "+this.mItemName);
        Log.i(TAG, "getItemName: "+this.mItemId);
        return this.mItemName;
    }

    public Date getItemDate() {
        return this.mItemDate;
    }

    public String getItemCourse() {
        return this.mItemCourse;
    }

    public UUID getId() {
        return this.mItemId;
    }

    public int getHeaderId() {
        return mHeaderId;
    }


    // SETTERS
    public void setItemName(String name){
        this.mItemName = name;
        Log.i(TAG, "setItemName: "+name);
    }

    public void setItemDate(Date date) {
        this.mItemDate = date;
        this.mHeaderId = Utils.getHeaderIdFromDate(date);
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

    // PARCELABLE IMPLEMENTATION
    // Implements parcelable to allow HomeworkItems to be attached to intents as an extra
    private HomeworkItem(Parcel in) {
        mItemName = in.readString();
        long tempDate = in.readLong();
        mItemDate = tempDate != -1 ? new Date(tempDate) : null;
        mItemCourse = in.readString();
        mItemId = (UUID) in.readValue(UUID.class.getClassLoader());
        mHeaderId = in.readInt();
    }

    // describeContents() never used in this case
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mItemName);
        dest.writeLong(mItemDate != null ? mItemDate.getTime() : -1L);
        dest.writeString(mItemCourse);
        dest.writeValue(mItemId);
        dest.writeInt(mHeaderId);
    }

    public static final Parcelable.Creator<HomeworkItem> CREATOR = new Parcelable.Creator<HomeworkItem>() {

        @Override
        public HomeworkItem[] newArray(int size) {
            return new HomeworkItem[size];
        }

        @Override
        public HomeworkItem createFromParcel(Parcel in) {
            return new HomeworkItem(in);
        }
    };
}