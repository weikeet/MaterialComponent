package me.weicools.common.preferences

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.ObservableEmitter

/**
 * @author weicools
 * @date 2020.04.08
 * @implNote
 */
abstract class RxPreferenceListener<T>(
  private val key: String, private val emitter: ObservableEmitter<T>
) : SharedPreferences.OnSharedPreferenceChangeListener {

  abstract fun getCurrentValue(preferences: SharedPreferences?): T

  override fun onSharedPreferenceChanged(preferences: SharedPreferences?, key: String?) {
    if (shouldEmit(key)) {
      try {
        emitter.onNext(getCurrentValue(preferences))
      } catch (error: Throwable) {
        emitter.onError(error)
      }
    }
  }

  open fun shouldEmit(key: String?): Boolean {
    return this.key == key
  }
}