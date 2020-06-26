package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class Area (
    @SerializedName( "id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ensignUrl")
    val ensignUrl: String?
)