package com.rakuishi.ok.model;

import com.google.gson.annotations.SerializedName;

public class Gist {

    @SerializedName("id")
    public String id;

    @SerializedName("description")
    public String description;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("html_url")
    public String url;
}
