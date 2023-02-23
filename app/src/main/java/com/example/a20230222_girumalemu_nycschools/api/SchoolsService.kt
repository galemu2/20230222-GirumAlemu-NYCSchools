package com.example.a20230222_girumalemu_nycschools.api

import com.example.a20230222_girumalemu_nycschools.data.model.NYCSchools
import com.example.a20230222_girumalemu_nycschools.data.model.SatResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsService {

    @GET("s3k6-pzi2.json")
    suspend fun getAllSchools(
        @Query("\$limit") limit: Int = 2,
        @Query("\$offset") offset: Int = 0
    ): List<NYCSchools>

    @GET("f9bf-2cp4.json")
    suspend fun getSatResult(
        @Query("dbn") dbn: String
    ): Response<List<SatResult>>
}