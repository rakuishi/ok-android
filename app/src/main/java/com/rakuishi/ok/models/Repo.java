package com.rakuishi.ok.models;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    public final int id;

    @SerializedName("name")
    public final String name;

    @SerializedName("description")
    public final String description;

    @SerializedName("updated_at")
    public final String updatedAt;

    @SerializedName("html_url")
    public final String url;

    public Repo(int id, String name, String description, String updatedAt, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updatedAt = updatedAt;
        this.url = url;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
               "name: " + name + "\n" +
               "description: " + description + "\n" +
               "updated_at: " + updatedAt + "\n" +
               "html_url: " + url;
    }
}
