package com.sam_chordas.android.stockhawk.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * novoa on 4/26/16.
 */
public class QuoteModel implements Parcelable{
    private String symbol;
    private String percentChange;
    private String change;
    private String bidPrice;
    private String previousClose;
    private String yearRange;
    private String changeForYearLow;
    private String changeForYearHigh;

    public QuoteModel() {
    }

    protected QuoteModel(Parcel in) {
        symbol = in.readString();
        percentChange = in.readString();
        change = in.readString();
        bidPrice = in.readString();
        previousClose = in.readString();
        yearRange = in.readString();
        changeForYearLow = in.readString();
        changeForYearHigh = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(percentChange);
        dest.writeString(change);
        dest.writeString(bidPrice);
        dest.writeString(previousClose);
        dest.writeString(yearRange);
        dest.writeString(changeForYearLow);
        dest.writeString(changeForYearHigh);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuoteModel> CREATOR = new Creator<QuoteModel>() {
        @Override
        public QuoteModel createFromParcel(Parcel in) {
            return new QuoteModel(in);
        }

        @Override
        public QuoteModel[] newArray(int size) {
            return new QuoteModel[size];
        }
    };

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getYearRange() {
        return yearRange;
    }

    public void setYearRange(String yearRange) {
        this.yearRange = yearRange;
    }

    public String getChangeForYearLow() {
        return changeForYearLow;
    }

    public void setChangeForYearLow(String changeForYearLow) {
        this.changeForYearLow = changeForYearLow;
    }

    public String getChangeForYearHigh() {
        return changeForYearHigh;
    }

    public void setChangeForYearHigh(String changeForYearHigh) {
        this.changeForYearHigh = changeForYearHigh;
    }
}
