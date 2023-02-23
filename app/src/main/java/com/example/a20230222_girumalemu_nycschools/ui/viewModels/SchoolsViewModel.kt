package com.example.a20230222_girumalemu_nycschools.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.a20230222_girumalemu_nycschools.data.SchoolsRepository
import com.example.a20230222_girumalemu_nycschools.data.model.SatResult
import com.example.a20230222_girumalemu_nycschools.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SchoolsViewModel(private val repository: SchoolsRepository = SchoolsRepository()) :
    ViewModel() {

    val schools = repository.getSchools().flow.cachedIn(viewModelScope)

    var satResult: MutableLiveData<Resource<List<SatResult>>> = MutableLiveData()

    fun getSatResults(dbn: String) {

        viewModelScope.launch {
            satResult.postValue(Resource.Loading())
            val response = repository.getSatResult(dbn = dbn)
            satResult.postValue(handleSatResponse(response))
        }

    }

    private fun handleSatResponse(
        response: Response<List<SatResult>>
    ): Resource<List<SatResult>> {

        if (response.isSuccessful) {
            response.body()?.let { satResults ->
                return Resource.Success(data = satResults)
            }
        }

        return Resource.Error(message = response.message())

    }

}


