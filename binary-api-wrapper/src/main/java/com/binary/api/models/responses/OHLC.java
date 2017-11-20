package com.binary.api.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Morteza Tavanarad
 * @version 1.0.0
 * @since 11/20/2017
 */
public class OHLC {

    @SerializedName("close")
    @Expose
    private
    String close;

    @SerializedName("epoch")
    @Expose
    private
    String epoch;

    @SerializedName("granularity")
    @Expose
    private
    Integer granularity;

    @SerializedName("high")
    @Expose
    private
    String high;

    @SerializedName("id")
    @Expose
    private
    String id;

    @SerializedName("low")
    @Expose
    private
    String low;

    @SerializedName("open")
    @Expose
    private
    String open;

    @SerializedName("open_time")
    @Expose
    private
    String openTime;

    @SerializedName("symbol")
    @Expose
    private
    String symbol;

    public String getClose() {
        return close;
    }

    public String getEpoch() {
        return epoch;
    }

    public Integer getGranularity() {
        return granularity;
    }

    public String getHigh() {
        return high;
    }

    public String getId() {
        return id;
    }

    public String getLow() {
        return low;
    }

    public String getOpen() {
        return open;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getSymbol() {
        return symbol;
    }
}
