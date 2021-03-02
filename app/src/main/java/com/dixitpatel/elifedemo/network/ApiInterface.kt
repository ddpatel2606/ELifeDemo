package com.dixitpatel.elifedemo.network

import com.dixitpatel.elifedemo.model.TaskResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 *  All Network Calling Apis are define here with Coroutine function.
 */
interface ApiInterface {

    // Fetch Task list API
    // https://adam-deleteme.s3.amazonaws.com/tasks.json
    @GET("tasks.json")
    suspend fun fetchTaskList(): Response<List<TaskResponse>>


}