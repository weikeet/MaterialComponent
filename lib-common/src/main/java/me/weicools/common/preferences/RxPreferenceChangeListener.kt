package me.weicools.common.preferences

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.ObservableEmitter

/**
 * @author weicools
 * @date 2020.04.08
 */
class RxPreferenceChangeListener<T>(
  private val key: String,
  private val defaultValue: T,
  private val getPreferences: GetPreferences<T>,
  emitter: ObservableEmitter<T>
) : RxPreferenceListener<T>(key, emitter) {

  override fun getCurrentValue(preferences: SharedPreferences?): T {
    // return preferences?.let { getPreferences.invoke(it, key, defaultValue) } ?: defaultValue
    return if (preferences == null) {
      defaultValue
    } else {
      getPreferences.invoke(preferences, key, defaultValue)
    }
  }
}