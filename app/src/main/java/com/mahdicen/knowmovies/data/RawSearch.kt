package com.mahdicen.knowmovies.data

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class RawSearch(
    @SerializedName("Response")
    val response: String?,
    @SerializedName("Search")
    val search: List<Search?>?,
    @SerializedName("totalResults")
    val totalResults: String?
) : Parcelable {
    @Parcelize
    data class Search(
        @SerializedName("imdbID")
        val imdbID: String?,
        @SerializedName("Poster")
        val poster: String?,
        @SerializedName("Title")
        val title: String?,
        @SerializedName("Type")
        val type: String?,
        @SerializedName("Year")
        val year: String?
    ) : Parcelable
}