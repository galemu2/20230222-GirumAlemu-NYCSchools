package com.example.a20230222_girumalemu_nycschools.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a20230222_girumalemu_nycschools.data.model.NYCSchools
import com.example.a20230222_girumalemu_nycschools.data.model.SatResult
import retrofit2.Response

class DummySchoolsRepository :ISchoolsRepository{

    val satResult = SatResult().apply {
        this.schoolName ="Best NY School"
        this.numOfSatTestTakers="100"
        this.satCriticalReadingAvgScore="101"
        this.satMathAvgScore="102"
        this.satWritingAvgScore="103"
    }

    val nycSchl  = NYCSchools("Bronx", "123", "KingsBridge Ave", "Best NYC School")

    override fun getSchools(): Pager<Int, NYCSchools> {
       return Pager(PagingConfig(1)){
       object :   PagingSource<Int, NYCSchools> () {
           override fun getRefreshKey(state: PagingState<Int, NYCSchools>): Int {
               return 1
           }

           override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NYCSchools> {
              return LoadResult.Page(listOf(nycSchl), null, null)
           }
       }
       }
    }

    override suspend fun getSatResult(dbn: String): Response<List<SatResult>> {
        satResult.dbn = dbn
       return Response.success(listOf(satResult))
    }
}