package com.rakuishi.ok.data

import com.google.gson.annotations.SerializedName

data class Gist(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("html_url")
    val url: String
)