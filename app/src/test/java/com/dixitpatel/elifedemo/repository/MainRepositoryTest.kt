package com.dixitpatel.elifedemo.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dixitpatel.elifedemo.db.TasksDao
import com.dixitpatel.elifedemo.network.ApiInterface
import com.dixitpatel.elifedemo.utils.MainCoroutinesRule
import com.dixitpatel.elifedemo.utils.MockUtil
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private lateinit var repository: MainRepository
    private val service: ApiInterface = mock()
    private val tasksDao: TasksDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = MainRepository(service, tasksDao)
    }

    @ExperimentalTime
    @Test
    fun fetchTasksListFromNetworkTest() = runBlocking {

        repository.fetchTasksList(
            onSuccess = {},
            onError = {}
        ).test {
            expectItem()?.get(0)?.let { assertEquals(it.id, 1) }
            assertEquals(expectItem()?.get(0)?.name, "Take the rubbish out")
            assertEquals(expectItem(), MockUtil.mockTaskResponseList())
            expectComplete()
        }
    }

}
