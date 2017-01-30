package com.rakuishi.ok.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("html_url")
    public String url;
}
