package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class Winner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("crestUrl")
    val crestUrl: String
)