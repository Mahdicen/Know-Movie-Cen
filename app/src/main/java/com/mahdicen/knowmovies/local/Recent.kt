package com.mahdicen.knowmovies.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recent_table")
data class Recent(
    @SerializedName("imdbID")
    val imdbID: String?,
    @SerializedName("Poster")
    val poster: String?,

    @SerializedName("Title")
    @PrimaryKey
    val title: String,

    @SerializedName("Type")
    val type: String?,
    @SerializedName("Year")
    val year: String?
) : Parcelable