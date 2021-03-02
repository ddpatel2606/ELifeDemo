package com.dixitpatel.elifedemo.utils

import com.dixitpatel.elifedemo.model.TaskResponse

object MockUtil {

  fun mockTaskResponse() = TaskResponse(1,
    1,
    "Take the rubbish out",
    "Empty the bin and take the rubbish and recycling to the communal rubbish bins that are on the lower ground floor of the building",
    "general")

  fun mockTaskResponseList() = listOf(mockTaskResponse())
}
