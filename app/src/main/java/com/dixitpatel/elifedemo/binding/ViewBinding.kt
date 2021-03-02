package com.dixitpatel.elifedemo.binding

import android.app.Activity
import android.view.View
import androidx.databinding.BindingAdapter
import com.dixitpatel.elifedemo.utils.Alerts
import com.dixitpatel.elifedemo.utils.CircularProgressView
import timber.log.Timber

/**
 * View Binding with view
 */
object ViewBinding {

  @JvmStatic
  @BindingAdapter("toast")
  fun bindToast(view: View, text: String?) {
    text?.let {
      Alerts.showSnackBar(view.context as Activity, it)
    }
  }

  @JvmStatic
  @BindingAdapter("gone")
  fun bindGone(view: CircularProgressView, shouldBeGone: Boolean) {
    Timber.e("timber Progress $shouldBeGone")
    view.visibility = if (shouldBeGone) {
      View.GONE
    } else {
      View.VISIBLE
    }
  }

}
