package com.dixitpatel.elifedemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dixitpatel.elifedemo.model.TaskResponse

/**
 * Task Dao class contains all methods insert and get data from DB.
 */
@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(taskList: List<TaskResponse>)

    @Query("SELECT * FROM task_table")
    suspend fun getTasksList(): List<TaskResponse>?

}