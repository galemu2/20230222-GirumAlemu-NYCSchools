package com.example.a20230222_girumalemu_nycschools.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.a20230222_girumalemu_nycschools.api.SchoolsApi
import com.example.a20230222_girumalemu_nycschools.data.model.NYCSchools

class SchoolsRepository {

    fun getSchools(): Pager<Int, NYCSchools> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SchoolsPagingSource(SchoolsApi.getInstance())
        }
    }

    suspend fun getSatResult(dbn: String) =
        SchoolsApi.getInstance().getSatResult(dbn = dbn)

}