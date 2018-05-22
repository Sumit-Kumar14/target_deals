package com.target.dealbrowserpoc.dealbrowser.model.datasource;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DealItem implements Parcelable{

    @SerializedName("index")
    private int index;

    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private String originalPrice;

    @SerializedName("salePrice")
    private String salePrice;

    @SerializedName("image")
    private String image;

    @SerializedName("aisle")
    private String aisle;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.index);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.originalPrice);
        dest.writeString(this.salePrice);
        dest.writeString(this.image);
        dest.writeString(this.aisle);
    }

    protected DealItem(Parcel in) {
        this.index = in.readInt();
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.originalPrice = in.readString();
        this.salePrice = in.readString();
        this.image = in.readString();
        this.aisle = in.readString();
    }

    public static final Creator<DealItem> CREATOR = new Creator<DealItem>() {
        @Override
        public DealItem createFromParcel(Parcel source) {
            return new DealItem(source);
        }

        @Override
        public DealItem[] newArray(int size) {
            return new DealItem[size];
        }
    };
}