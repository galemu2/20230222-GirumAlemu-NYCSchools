package com.example.a20230222_girumalemu_nycschools.data

import androidx.paging.Pager
import com.example.a20230222_girumalemu_nycschools.data.model.NYCSchools
import com.example.a20230222_girumalemu_nycschools.data.model.SatResult
import retrofit2.Response

interface ISchoolsRepository {

    fun getSchools(): Pager<Int, NYCSchools>
    suspend fun getSatResult(dbn: String): Response<List<SatResult>>
}