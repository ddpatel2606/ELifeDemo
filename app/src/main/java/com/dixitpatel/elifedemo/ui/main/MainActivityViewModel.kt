package com.dixitpatel.elifedemo.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.application.MyApplication
import com.dixitpatel.elifedemo.model.TaskResponse
import com.dixitpatel.elifedemo.model.TaskTypes
import com.dixitpatel.elifedemo.repository.MainRepository
import com.dixitpatel.elifedemo.ui.base.LiveCoroutinesViewModel
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
 *  Main Activity ViewModel : ViewModel
 */
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository) : LiveCoroutinesViewModel() {

    var taskListLiveData: LiveData<List<TaskResponse>?>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    init {
        Timber.d("init MainViewModel")

        isLoading.set(true)
        taskListLiveData = launchOnViewModelScope {
            this.mainRepository.fetchTasksList(
                onSuccess = {
                    isLoading.set(false)
                },
                onError = {
                    isLoading.set(false)
                    it.let {
                        if (it?.contains("UnknownHostException") == true) {
                            _toastLiveData.postValue(MyApplication.instance.getString(R.string.internet_not_available))
                        } else {
                            _toastLiveData.postValue(it)
                        }
                    }
                }
            ).asLiveData()
        }
    }


    companion object {

        // Bind Task name with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("Task_name")
        fun TextView.setTaskName(name: String?) {
            this.text = if (name.isNullOrEmpty()) "" else " ${name.trim()}"
        }

        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("Task_image")
        fun ImageView.setTaskImageType(type: String?) {
            val drawable = when (type) {
                TaskTypes.GENERAL.name.toLowerCase() -> R.drawable.ic_general_vector
                TaskTypes.HYDRATION.name.toLowerCase() -> R.drawable.ic_hydration_vector
                TaskTypes.MEDICATION.name.toLowerCase() -> R.drawable.ic_medication_vector
                TaskTypes.NUTRITION.name.toLowerCase() -> R.drawable.ic_nutrition_vector
                else -> R.drawable.ic_general_vector
            }
            this.setImageDrawable(ContextCompat.getDrawable(this.context, drawable))
        }

        // Bind Task Desc with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("Task_desc")
        fun TextView.setTaskDesc(desc: String?) {
            this.text = if (desc.isNullOrEmpty()) "" else " ${desc.trim()}"
        }

    }

}