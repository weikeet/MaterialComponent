package me.weicools.common.preferences

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.ObservableEmitter

/**
 * @author weicools
 * @date 2020.04.08
 * @implNote
 */
class RxPreferenceContainsListener(
  private val key: String, emitter: ObservableEmitter<Boolean>
) : RxPreferenceListener<Boolean>(key, emitter) {
  override fun getCurrentValue(preferences: SharedPreferences?): Boolean {
    return preferences?.contains(key) ?: false
  }
}