package com.xu.kevin.csvtest;

public class CatalogInfo{

    public String mLevelOne;
    public String mLevelTow;
    public String mLevelThree;

    public String mIconName;

    public Integer mCount;
    public Integer mStart;
    public Integer mEnd;

    @Override
    public String toString() {
        return "CatalogInfo{" +
                "mLevelOne='" + mLevelOne + '\'' +
                ", mLevelTow='" + mLevelTow + '\'' +
                ", mLevelThree='" + mLevelThree + '\'' +
                ", mIconName='" + mIconName + '\'' +
                ", mCount=" + mCount +
                ", mStart=" + mStart +
                ", mEnd=" + mEnd +
                '}';
    }

    public String getmLevelOne() {
        return mLevelOne;
    }

    public void setmLevelOne(String mLevelOne) {
        this.mLevelOne = mLevelOne;
    }

    public String getmLevelTow() {
        return mLevelTow;
    }

    public void setmLevelTow(String mLevelTow) {
        this.mLevelTow = mLevelTow;
    }

    public String getmLevelThree() {
        return mLevelThree;
    }

    public void setmLevelThree(String mLevelThree) {
        this.mLevelThree = mLevelThree;
    }

    public String getmIconName() {
        return mIconName;
    }

    public void setmIconName(String mIconName) {
        this.mIconName = mIconName;
    }

    public Integer getmCount() {
        return mCount;
    }

    public void setmCount(Integer mCount) {
        this.mCount = mCount;
    }

    public Integer getmStart() {
        return mStart;
    }

    public void setmStart(Integer mStart) {
        this.mStart = mStart;
    }

    public Integer getmEnd() {
        return mEnd;
    }

    public void setmEnd(Integer mEnd) {
        this.mEnd = mEnd;
    }




}
