package com.example.a20230222_girumalemu_nycschools.data.model

import com.google.gson.annotations.SerializedName

data class NYCSchools(
    val borough: String,
    val dbn: String,  // important
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String,
    @SerializedName("school_name")
    val schoolName: String

) {
    override fun toString(): String {
        return "dbn:$dbn\n" +
                "HighSchool:$schoolName\n" +
                "Address:$primaryAddressLine1\n" +
                "Borough:$borough"
    }
}