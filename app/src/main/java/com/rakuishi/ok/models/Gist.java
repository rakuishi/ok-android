package com.rakuishi.ok.models;

import com.google.gson.annotations.SerializedName;

public class Gist {

    @SerializedName("id")
    public final String id;

    @SerializedName("description")
    public final String description;

    @SerializedName("updated_at")
    public final String updatedAt;

    @SerializedName("html_url")
    public final String url;

    public Gist(String id, String description, String updatedAt, String url) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
        this.url = url;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
               "description: " + description + "\n" +
               "updated_at: " + updatedAt + "\n" +
               "url: " + url;
    }
}
