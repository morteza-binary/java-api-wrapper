package com.binary.api.models.requests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.reactivex.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.UUID;

/**
 * Created by morteza on 7/19/2017.
 */

public class RequestBase {
    @SerializedName("passthrough")
    JsonObject passThrough;

    @SerializedName("req_id")
    @Nullable
    Long id = null;

    @Expose
    transient Type  responseType;

    public void setUUID() {
        if (this.passThrough == null) {
            this.passThrough = new JsonObject();
        }

        UUID uuid = UUID.randomUUID();
        this.passThrough.addProperty("uuid", uuid.toString());
    }

    public String getUUID() {
        if(this.passThrough == null) {
            return null;
        }

        return this.passThrough.get("uuid").getAsString();
    }

    public JsonObject getPassThrough() {
        return passThrough;
    }

    public void setPassThrough(JsonObject passThrough) {
        this.passThrough = passThrough;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public Type getResponseType() {
        return responseType;
    }

    public void setResponseType(Type responseType) {
        this.responseType = responseType;
    }
}
