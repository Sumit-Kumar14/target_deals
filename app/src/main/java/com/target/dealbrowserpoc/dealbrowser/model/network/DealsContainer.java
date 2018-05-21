package com.target.dealbrowserpoc.dealbrowser.model.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.target.dealbrowserpoc.dealbrowser.model.DealItem;

import java.util.ArrayList;

/**
 * @author Sumit Kumar
 */

public class DealsContainer implements Parcelable{

    @SerializedName("_id")
    private String id;

    @SerializedName("data")
    private ArrayList<DealItem> dealItemList;

    @SerializedName("type")
    private String type;

    public String get_id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList getDealItemList() {
        return dealItemList;
    }

    public void setDealItemList(ArrayList<DealItem> dealItemList) {
        this.dealItemList = dealItemList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.dealItemList);
        dest.writeString(this.type);
    }

    private DealsContainer(Parcel in) {
        this.id = in.readString();
        this.dealItemList = in.createTypedArrayList(DealItem.CREATOR);
        this.type = in.readString();
    }

    public static final Creator<DealsContainer> CREATOR = new Creator<DealsContainer>() {
        @Override
        public DealsContainer createFromParcel(Parcel source) {
            return new DealsContainer(source);
        }

        @Override
        public DealsContainer[] newArray(int size) {
            return new DealsContainer[size];
        }
    };
}