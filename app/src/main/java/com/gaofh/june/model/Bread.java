package com.gaofh.june.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bread implements Parcelable {
    public String name;
    public String prices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.prices);
    }

    public Bread() {
    }

    protected Bread(Parcel in) {
        this.name = in.readString();
        this.prices = in.readString();
    }

    public static final Parcelable.Creator<Bread> CREATOR = new Parcelable.Creator<Bread>() {
        @Override
        public Bread createFromParcel(Parcel source) {
            return new Bread(source);
        }

        @Override
        public Bread[] newArray(int size) {
            return new Bread[size];
        }
    };
}
