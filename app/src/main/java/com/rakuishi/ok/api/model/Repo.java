package com.rakuishi.ok.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rakuishi on 15/05/02.
 */
public class Repo {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("html_url")
    private String mUrl;

    public Repo(int id, String name, String description, String updatedAt, String url) {
        mId = id;
        mName = name;
        mDescription = description;
        mUpdatedAt = updatedAt;
        mUrl = url;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
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
               "name: " + mName + "\n" +
               "description: " + mDescription + "\n" +
               "updated_at: " + mUpdatedAt + "\n" +
               "html_url: " + mUrl;
    }
}
