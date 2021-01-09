package com.example.ezyfood3.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Balance implements Parcelable {
    private int balance;

    public Balance( int balance ){
        this.balance = balance;
    }

    protected Balance(Parcel in) {
        balance = in.readInt();
    }

    public static final Creator<Balance> CREATOR = new Creator<Balance>() {
        @Override
        public Balance createFromParcel(Parcel in) {
            return new Balance(in);
        }

        @Override
        public Balance[] newArray(int size) {
            return new Balance[size];
        }
    };

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(balance);
    }
}
