package com.mahdicen.knowmovies.local


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "movie_table")
data class Movie(
    @SerializedName("imdb")
    val imdb: Double?,
    @SerializedName("Poster")
    val poster: String?,
    @SerializedName("Title")
    @PrimaryKey
    val title: String,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Year")
    val year: Int
) : Parcelable