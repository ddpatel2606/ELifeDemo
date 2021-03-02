package com.dixitpatel.elifedemo.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dixitpatel.elifedemo.extension.recyclerViewAnimate
import com.dixitpatel.elifedemo.model.TaskResponse
import com.dixitpatel.elifedemo.ui.adapter.TasksAdapter

/**
 * RecyclerView Binding with Adapter
 */
object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter.apply {
      stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
    view.recyclerViewAnimate()
  }


  @JvmStatic
  @BindingAdapter("adapterTaskList")
  fun bindAdapterTaskList(view: RecyclerView, taskList: List<TaskResponse>?) {
    taskList?.let{ itemList ->
      view.adapter?.let { adapter ->
        (adapter as TasksAdapter).setTasksList(itemList)
      }
    }
  }
}
