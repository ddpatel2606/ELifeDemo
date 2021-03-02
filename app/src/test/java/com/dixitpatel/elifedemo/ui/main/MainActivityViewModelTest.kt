package com.dixitpatel.elifedemo.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.dixitpatel.elifedemo.db.TasksDao
import com.dixitpatel.elifedemo.model.TaskResponse
import com.dixitpatel.elifedemo.network.ApiInterface
import com.dixitpatel.elifedemo.repository.MainRepository
import com.dixitpatel.elifedemo.utils.MainCoroutinesRule
import com.dixitpatel.elifedemo.utils.MockUtil
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mainRepository: MainRepository
    private val apiService: ApiInterface = mock()
    private val tasksDao: TasksDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        mainRepository = MainRepository(apiService, tasksDao)
        viewModel = MainActivityViewModel(mainRepository)
    }

    @Test
    fun fetchTasksListTest() = runBlocking {
        val mockData = MockUtil.mockTaskResponseList()
        whenever(tasksDao.getTasksList()).thenReturn(mockData)

        val observer: Observer<List<TaskResponse>?> = mock()
        val fetchedData: LiveData<List<TaskResponse>?> =
            mainRepository.fetchTasksList(
                onSuccess = {},
                onError = {}
            ).asLiveData()
        fetchedData.observeForever(observer)

        verify(tasksDao, atLeastOnce()).getTasksList()
        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }
}
