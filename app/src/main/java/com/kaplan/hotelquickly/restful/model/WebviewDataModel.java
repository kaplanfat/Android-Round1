package com.kaplan.hotelquickly.restful.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kaplanfatt on 09/01/16.
 */
public class WebviewDataModel implements Serializable {

    public String webViewTitle;

    @SerializedName("url")
    public String url;

    @SerializedName("filePath")
    public String filePath;

    @SerializedName("namespace")
    public String namespace;

    @SerializedName("cache")
    public boolean cache;

    @SerializedName("params")
    public String[] params;

    @SerializedName("pageTitle")
    public String pageTitle;

    @SerializedName("templateLastUpdated")
    public templateLastUpdated template;

    public static class templateLastUpdated
    {
        @SerializedName("unixTimeStamp")
        long unixTimestamp;

        @SerializedName("dateTime")
        String dateTime;
    }
}
