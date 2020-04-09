package me.weicools.common.preferences

import android.content.SharedPreferences

/**
 * @author weicools
 * @date 2020.04.08
 */
interface GetPreferences<T> {
  fun invoke(preferences: SharedPreferences, key: String, value: T): T
}