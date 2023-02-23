package com.example.a20230222_girumalemu_nycschools.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a20230222_girumalemu_nycschools.data.DummySchoolsRepository
import com.example.a20230222_girumalemu_nycschools.data.model.SatResult
import com.example.a20230222_girumalemu_nycschools.getOrAwaitValue
import com.example.a20230222_girumalemu_nycschools.util.Resource
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SchoolsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()

    private val dummySchoolsRepository = DummySchoolsRepository()
    private val viewModel = SchoolsViewModel(dummySchoolsRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testPackageName(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        Truth.assertThat(context.packageName).endsWith("com.example.a20230222_girumalemu_nycschools")
     }

    @Test
    fun testGetSatResults(): Unit = runBlocking{
        // check for loading
        val satResult = viewModel.satResult.value
         viewModel.getSatResults("123")
         if(satResult is Resource.Success ) {
            val successResult = satResult.data?.get(0)
            successResult?.let {
                 Truth.assertThat(it.dbn).isEqualTo("123")
            }
        }else{
            throw Exception("Test Failed")
         }

    }

}