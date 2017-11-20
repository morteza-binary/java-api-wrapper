package com.binary.api.models.responses;

import com.binary.api.models.requests.TickHistoryRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Morteza Tavanarad
 * @version 1.0.0
 * @since 11/20/2017
 */
public class OHLCResponce extends ResponseBase<TickHistoryRequest> {
    @SerializedName("ohlc")
    @Expose
    private OHLC ohlc;

    public OHLC getOhlc() {
        return ohlc;
    }

    public void setOhlc(OHLC ohlc) {
        this.ohlc = ohlc;
    }
}
