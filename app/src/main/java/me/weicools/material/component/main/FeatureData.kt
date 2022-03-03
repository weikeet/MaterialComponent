package me.weicools.material.component.main

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.annotation.StringRes

abstract class FeatureData constructor(
    @param:StringRes @field:StringRes @get:StringRes val titleResId: Int,
    @param:DrawableRes @field:DrawableRes @get:DrawableRes val drawableResId: Int,
    @param:Status @field:Status val status: Int = STATUS_READY
) {

  @IntDef(STATUS_READY, STATUS_WIP)
  @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
  annotation class Status

  companion object {
    /** Status flag that denotes the demo and component are ready for use.  */
    const val STATUS_READY = 0

    /** Status flag that denotes the demo and/or component is work in progress.  */
    const val STATUS_WIP = 1
  }

  abstract fun startActivity(activity: Activity)
}
