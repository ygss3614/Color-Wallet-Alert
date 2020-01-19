package com.colorwalletalert.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.colorwalletalert.ui.R;

import com.colorwalletalert.utils.Utils;

public class Category implements Parcelable {
    public String description;
    public Float target;
    public Float spend;
    public int iconPath;

    public Category() {}

    public Category(String description, Float target, int iconPath) {
        this.description = description;
        this.target = target;
        this.spend = Float.valueOf(0);
        this.iconPath = iconPath;
    }

    protected Category(Parcel in) {
        description = in.readString();
        if (in.readByte() == 0) {
            target = null;
        } else {
            target = in.readFloat();
        }
        if (in.readByte() == 0) {
            spend = null;
        } else {
            spend = in.readFloat();
        }
        iconPath = in.readInt();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTarget(Float target) {
        this.target = target;
    }

    public void setSpend(Float spend) {
        this.spend = spend;
    }

    public void setIconPath(int iconPath) {
        this.iconPath = iconPath;
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


    public Float getSpend() { return spend; }

    public Float getTarget() {
        return target;
    }

    public int getIconPath() {
        return iconPath;
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
        if (spend == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(spend);
        }
        parcel.writeInt(iconPath);
    }


    /***
     * name: getSuggestedDailySpend
     * description: Calculates a suggested daily spend according to amount available and days of
     * months
     * params:
     *
     * @return Float suggestedDailySpend
     * @param
     */
    public String getSuggestedDailySpend (){
        // COMPLETED: calcular de acordo com os dias restantes do mês
        // get the count of days to month ends
        int daysToMonthEnd = Utils.getInstance().getDaysToMonthEnd();
        return Utils.getInstance().roundTwoDecimals(getAvailableAmount()/daysToMonthEnd);
    }


    /***
     * name: getAvailableAmount
     * description: Calculates a available amount
     * params:
     *
     * @return Float availableAmount
     * @param
     */
    public Float getAvailableAmount(){
        return (this.target - this.spend);
    }

    /***
     * name: getCardBackgroundColor
     * description: Calculates a available amount
     * params:
     *
     * @return int colorId
     * @param
     */
    public int getCardBackgroundColor(){
        int colorId = R.color.colorCategoryGreen;
        Float percent = (this.spend / this.target) * 100;
        if (percent > 50 && percent < 80){
            colorId = R.color.colorCategoryOrange;
        }else if (percent > 80){
            colorId = R.color.colorCategoryRed;
        }
        return colorId;
    }
}
