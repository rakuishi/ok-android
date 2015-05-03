package com.rakuishi.ok.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rakuishi on 15/05/02.
 */
public class Gist {

    @SerializedName("id")
    private String mId;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("html_url")
    private String mUrl;

    public Gist(String id, String description, String updatedAt, String url) {
        mId = id;
        mDescription = description;
        mUpdatedAt = updatedAt;
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return "id: " + mId + "\n" +
               "description: " + mDescription + "\n" +
               "updated_at: " + mUpdatedAt + "\n" +
               "url: " + mUrl;
    }
}
