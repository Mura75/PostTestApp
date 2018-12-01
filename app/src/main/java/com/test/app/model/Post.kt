package com.test.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.app.utils.Constants.HH_MM_D_MM_YYYY
import com.test.app.utils.Constants.ISO_DATA_FORMAT_8601
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class Post(@SerializedName("created_at") val createdAt: String,
                @SerializedName("title") val title: String): Parcelable {

    fun getTime(): String {
        val original = SimpleDateFormat(ISO_DATA_FORMAT_8601)
        val target = SimpleDateFormat(HH_MM_D_MM_YYYY)
        val date = original.parse(createdAt)
        return target.format(date)
    }
}