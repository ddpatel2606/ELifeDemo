package com.dixitpatel.elifedemo.ui.main

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.databinding.ActivityMainBinding
import com.dixitpatel.elifedemo.extension.recyclerViewAnimate
import com.dixitpatel.elifedemo.model.TaskResponse
import com.dixitpatel.elifedemo.model.TaskTypes
import com.dixitpatel.elifedemo.ui.adapter.TasksAdapter
import com.dixitpatel.elifedemo.ui.base.BaseActivity
import com.dixitpatel.elifedemo.utils.Alerts
import com.dixitpatel.elifedemo.utils.ConnectivityReceiver
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
 *  Main Activity : Task Listing Activity
 */
class MainActivity : BaseActivity<MainActivityViewModel?>(), CompoundButton.OnCheckedChangeListener
{
    // Binding view and initialized as lazy
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    @Inject
    lateinit var model: MainActivityViewModel

    @Inject
    lateinit var connectivityReceiver: ConnectivityReceiver

    override fun getViewModel(): MainActivityViewModel {
        return model
    }

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = TasksAdapter()
            viewModel = model
            toolbar.let {
                setSupportActionBar(it)
                it.title = getString(R.string.app_name)
                it.setNavigationOnClickListener {
                    onBackPressed()
                }
            }
            progressBar.run {
                roundBorder = true
                indeterminateMode = true
                progressBarColor = ContextCompat.getColor(this@MainActivity, R.color.color_primary)
                progressBarWidth = 4F
                backgroundProgressBarWidth = 4F
                backgroundProgressBarColor = ContextCompat.getColor(
                        this@MainActivity,
                        R.color.transparent
                )
            }
        }

        binding.generalSortToogle.setOnCheckedChangeListener(this)
        binding.nutritionSortToogle.setOnCheckedChangeListener(this)
        binding.hydrationSortToogle.setOnCheckedChangeListener(this)
        binding.medicationSortToogle.setOnCheckedChangeListener(this)


        // show hide banner when internet goes off and on.
        connectivityReceiver.observe(this@MainActivity, {
            if (it == true)
            {
                binding.internetLayout.root.visibility = View.GONE
            }
            else
            {
                binding.internetLayout.root.visibility = View.VISIBLE
            }
        })
    }

    // Remove sorting and load actual data.
    private fun removeSort()
    {
        (binding.adapter as TasksAdapter).items = model.taskListLiveData.value as MutableList<TaskResponse>
        (binding.adapter as TasksAdapter).notifyDataSetChanged()
        binding.myRecyclerView.recyclerViewAnimate()
    }

    // Sort By general category
    private fun sortByGeneral()
    {
        (binding.adapter as TasksAdapter).items =
            (binding.adapter as TasksAdapter).items.filter { it.type == TaskTypes.GENERAL.name.toLowerCase(Locale.getDefault()) } as MutableList<TaskResponse>
        (binding.adapter as TasksAdapter).notifyDataSetChanged()
        binding.myRecyclerView.recyclerViewAnimate()
    }

    // Sort By medication
    private fun sortByMedication()
    {
        (binding.adapter as TasksAdapter).items =
            (binding.adapter as TasksAdapter).items.filter { it.type == TaskTypes.MEDICATION.name.toLowerCase(
                Locale.getDefault()) } as MutableList<TaskResponse>
        (binding.adapter as TasksAdapter).notifyDataSetChanged()
        binding.myRecyclerView.recyclerViewAnimate()
    }

    // Sort By hydration
    private fun sortByHydration()
    {
        (binding.adapter as TasksAdapter).items =
            (binding.adapter as TasksAdapter).items.filter { it.type == TaskTypes.HYDRATION.name.toLowerCase(
                Locale.getDefault()) } as MutableList<TaskResponse>
        (binding.adapter as TasksAdapter).notifyDataSetChanged()
        binding.myRecyclerView.recyclerViewAnimate()
    }

    // Sort By nutrition
    private fun sortByNutrition()
    {
        (binding.adapter as TasksAdapter).items = (binding.adapter as TasksAdapter).items.filter { it.type == TaskTypes.NUTRITION.name.toLowerCase(
            Locale.getDefault()) } as MutableList<TaskResponse>
        (binding.adapter as TasksAdapter).notifyDataSetChanged()
        binding.myRecyclerView.recyclerViewAnimate()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2500 > System.currentTimeMillis()) {
            super.onBackPressed()
            ActivityCompat.finishAffinity(this)
        } else {
            Alerts.showSnackBar(
                    this@MainActivity, resources.getString(R.string.double_press_exit)
            )
            binding.nutritionSortToogle.isChecked = false
            binding.generalSortToogle.isChecked = false
            binding.medicationSortToogle.isChecked = false
            binding.hydrationSortToogle.isChecked = false
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if(buttonView?.id == R.id.generalSortToogle)
        {
            if (isChecked)
            {
                binding.nutritionSortToogle.isChecked = false
                binding.hydrationSortToogle.isChecked = false
                binding.medicationSortToogle.isChecked = false
                sortByGeneral()
            }
            else
            {
                removeSort()
            }
        }else if(buttonView?.id == R.id.nutritionSortToogle)
        {
            if (isChecked)
            {
                binding.generalSortToogle.isChecked = false
                binding.hydrationSortToogle.isChecked = false
                binding.medicationSortToogle.isChecked = false
                sortByNutrition()
            }else
            {
                removeSort()
            }
        }else if(buttonView?.id == R.id.hydrationSortToogle)
        {
            if (isChecked)
            {
                binding.nutritionSortToogle.isChecked = false
                binding.generalSortToogle.isChecked = false
                binding.medicationSortToogle.isChecked = false
                sortByHydration()
            }else
            {
                removeSort()
            }
        }else if (buttonView?.id == R.id.medicationSortToogle)
        {
            if (isChecked)
            {
                binding.nutritionSortToogle.isChecked = false
                binding.hydrationSortToogle.isChecked = false
                binding.generalSortToogle.isChecked = false
                sortByMedication()
            }
            else
            {
                removeSort()
            }
        }
    }
}