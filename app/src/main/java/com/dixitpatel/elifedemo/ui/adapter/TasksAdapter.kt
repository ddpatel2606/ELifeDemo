package com.dixitpatel.elifedemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.databinding.RowItemAllBinding
import com.dixitpatel.elifedemo.model.TaskResponse

/**
 * Task List Adapter.
 */
class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

  var items: MutableList<TaskResponse> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      DataBindingUtil.inflate<RowItemAllBinding>(inflater, R.layout.row_item_all, parent, false)
    return TaskViewHolder(binding).apply {
      binding.root.setOnClickListener {

      }
    }
  }

  fun setTasksList(TaskList: List<TaskResponse>) {
    val previousItemSize = items.size
    items.clear()
    items.addAll(TaskList)
    notifyItemRangeChanged(previousItemSize, TaskList.size)
  }

  override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
    holder.binding.apply {
      model = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount() = items.size

  class TaskViewHolder(val binding: RowItemAllBinding) :
    RecyclerView.ViewHolder(binding.root)
}
