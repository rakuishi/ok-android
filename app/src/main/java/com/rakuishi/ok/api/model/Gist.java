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

    public Gist(String id, String description, String updatedAt) {
        mId = id;
        mDescription = description;
        mUpdatedAt = updatedAt;
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

    @Override
    public String toString() {
        return "id: " + mId + "\n" +
                "description: " + mDescription + "\n" +
                "updated_at: " + mUpdatedAt;
    }
}
