package com.dixitpatel.elifedemo.db

import com.dixitpatel.elifedemo.utils.MockUtil
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class TaskDaoTest : LocalDatabase() {

  private lateinit var tasksDao: TasksDao

  @Before
  fun init() {
    tasksDao = db.tasksDao()
  }

  @Test
  fun insertAndLoadTasksListTest() = runBlocking {
    val mockDataList = MockUtil.mockTaskResponseList()
    tasksDao.insertTaskList(mockDataList)

    val loadFromDB = tasksDao.getTasksList()
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = MockUtil.mockTaskResponse()
    assertThat(loadFromDB?.get(0)?.name, `is`(mockData.name))
  }
}
