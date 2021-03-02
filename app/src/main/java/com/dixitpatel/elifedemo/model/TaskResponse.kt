package com.dixitpatel.elifedemo.model

import android.graphics.drawable.AnimatedImageDrawable
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dixitpatel.elifedemo.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import okhttp3.internal.concurrent.Task

/**
 *  Task Listing Result.
 */
@Entity(tableName = "task_table")
data class TaskResponse(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
  @ColumnInfo(name = "task_id") val taskId: Long,
  @ColumnInfo(name = "task_name") val name: String?,
  @ColumnInfo(name = "task_description") val description: String?,
  @ColumnInfo(name = "task_type") var type: String?) {
  /**
   * Return Task Types as per task is given
   */
  private fun getTaskType(): TaskTypes {
    return when (type) {
      TaskTypes.GENERAL.name -> TaskTypes.GENERAL
      TaskTypes.HYDRATION.name -> TaskTypes.HYDRATION
      TaskTypes.MEDICATION.name -> TaskTypes.MEDICATION
      TaskTypes.NUTRITION.name -> TaskTypes.NUTRITION
      else -> TaskTypes.GENERAL
    }
  }
}
