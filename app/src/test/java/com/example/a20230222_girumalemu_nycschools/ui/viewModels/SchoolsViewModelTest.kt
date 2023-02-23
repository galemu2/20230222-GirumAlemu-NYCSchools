package com.example.a20230222_girumalemu_nycschools.ui.viewModels

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class SchoolsViewModelTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test1(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        Truth.assertThat(context.packageName).endsWith("com.example.a20230222_girumalemu_nycschools")
     }
}