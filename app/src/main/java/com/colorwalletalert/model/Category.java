package com.colorwalletalert.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Category implements Parcelable {
    public String description;
    public Float target;
    public int iconPath;

    public Category() {}

    public Category(String description, Float target, int iconPath) {
        this.description = description;
        this.target = target;
        this.iconPath = iconPath;
    }

    protected Category(Parcel in) {
        description = in.readString();
        if (in.readByte() == 0) {
            target = null;
        } else {
            target = in.readFloat();
        }
        iconPath = in.readInt();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public Float getTarget() {
        return target;
    }

    public int getIconPath() {
        return iconPath;
    }

    public Float roundTwoDecimals(Float d) {
        DecimalFormat twoDForm = new DecimalFormat("#,##");
        return Float.valueOf(twoDForm.format(d));
    }

    public Float getSuggestedDailySpend (){

        // TODO: calcular de acordo com os dias restantes do mês
        return roundTwoDecimals(this.target/30);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        if (target == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(target);
        }
        parcel.writeInt(iconPath);
    }
}
