package com.dixitpatel.elifedemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dixitpatel.elifedemo.model.TaskResponse
import okhttp3.internal.concurrent.Task
import org.jetbrains.annotations.NotNull

/**
 * Room Database for data store.
 */
@Database(entities = [TaskResponse::class], version = 1)
abstract class RoomDatabaseHelper : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}