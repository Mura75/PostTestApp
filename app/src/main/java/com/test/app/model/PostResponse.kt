package com.test.app.model

import com.google.gson.annotations.SerializedName

data class PostResponse(@SerializedName("hits") val hits: List<Post>,
                        @SerializedName("page") val page: Int,
                        @SerializedName("hitsPerPage") val hitPerPage: Int) {
}