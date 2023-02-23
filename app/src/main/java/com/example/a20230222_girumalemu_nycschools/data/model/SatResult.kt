package com.example.a20230222_girumalemu_nycschools.data.model

import com.google.gson.annotations.SerializedName

data class SatResult(
    var dbn: String? = null,

    @SerializedName("num_of_sat_test_takers")
    var numOfSatTestTakers: String? = null,

    @SerializedName("sat_critical_reading_avg_score")
    var satCriticalReadingAvgScore: String? = null,

    @SerializedName("sat_math_avg_score")
    var satMathAvgScore: String? = null,

    @SerializedName("sat_writing_avg_score")
    var satWritingAvgScore: String? = null,

    @SerializedName("school_name")
    var schoolName: String? = null,
) {

    override fun toString(): String {
        return "dbn:$dbn+\n" +
                "test takers:${numOfSatTestTakers ?: -1}\n" +
                "Math score:${satMathAvgScore ?: -1}\n" +
                "Writing:${satWritingAvgScore ?: -1}\n" +
                "Reading:${satCriticalReadingAvgScore ?: -1}"
    }
}