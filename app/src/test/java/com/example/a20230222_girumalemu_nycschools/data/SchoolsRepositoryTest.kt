package com.example.a20230222_girumalemu_nycschools.data

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.liveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a20230222_girumalemu_nycschools.data.model.NYCSchools
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SchoolsRepositoryTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)
    private val repository = DummySchoolsRepository()

    class NoopListCallback : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }

    class MyDiffCallback : DiffUtil.ItemCallback<NYCSchools>() {
        override fun areItemsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem.dbn == newItem.dbn
        }

        override fun areContentsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem == newItem
        }
    }
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getSchools():Unit = runBlocking {

        val differ = AsyncPagingDataDiffer(
            diffCallback = MyDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )

        val schools = repository.getSchools().liveData.value
        schools?.let {
            differ.submitData(schools)
             Truth.assertThat(differ.snapshot().items[0].schoolName).isEqualTo(repository.nycSchl.schoolName)
        }

    }
}