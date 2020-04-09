package me.weicools.common.preferences

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.ObservableEmitter

internal class RxAllPreferencesListener(
  emitter: ObservableEmitter<Map<String, *>?>
) : RxPreferenceListener<Map<String, *>?>("", emitter) {

  override fun getCurrentValue(preferences: SharedPreferences?): Map<String, *>? {
    return preferences?.all
  }

  override fun shouldEmit(key: String?): Boolean {
    // Emit when any key changes:
    return true
  }
}