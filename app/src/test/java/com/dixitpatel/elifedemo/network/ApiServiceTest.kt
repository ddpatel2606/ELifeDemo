package com.dixitpatel.elifedemo.network

import com.dixitpatel.elifedemo.utils.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ApiServiceTest : ApiAbstract<ApiInterface>() {

  private lateinit var service: ApiInterface

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @Before
  fun initService() {
    service = createService(ApiInterface::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchTaskListFromNetworkTest() = runBlocking {
    enqueueResponse("/tasks.json")
    val response = service.fetchTaskList()
    val responseBody = requireNotNull((response).body())
    mockWebServer.takeRequest()

    assertThat(responseBody[0].id, `is`(1))
    assertThat(responseBody[0].name, `is`("Take the rubbish out"))
    assertThat(responseBody[0].description, `is`("Empty the bin and take the rubbish and recycling to the communal rubbish bins that are on the lower ground floor of the building"))
    assertThat(responseBody[0].type, `is`("general"))
  }

}
