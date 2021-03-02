package com.dixitpatel.elifedemo.repository

import androidx.annotation.WorkerThread
import com.dixitpatel.elifedemo.db.TasksDao
import com.dixitpatel.elifedemo.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository Class provides Abstraction layer between view model and controller.
 */
class MainRepository @Inject constructor(
        private val mApiInterface: ApiInterface, private val tasksDao: TasksDao) : Repository {

    @WorkerThread
    suspend fun fetchTasksList(
            onSuccess: () -> Unit,
            onError: (String?) -> Unit
    ) = flow {
        var tasks = tasksDao.getTasksList()
        if (tasks?.isEmpty() == true) {
            /**
             * fetches a list of [Task] from the network and getting [ApiResponse] asynchronously.
              */
            val response = mApiInterface.fetchTaskList()
            if (response.isSuccessful) {
                response.body().let {
                    tasks = it
                    tasks?.let { it1 ->
                        tasksDao.insertTaskList(it1)
                        emit(tasksDao.getTasksList())
                        onSuccess()
                    }
                }
            } else {
                onError(response.errorBody().toString())
            }
        } else {
            emit(tasks)
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)
}
