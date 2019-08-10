package me.weicools.material.component.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(private val context: Context) {
  private val sharedPreferences: SharedPreferences

  var isFirstLaunch: Boolean
    get() = this.sharedPreferences.getBoolean(FIRST_LAUNCH, true)
    set(z) = this.sharedPreferences.edit().putBoolean(FIRST_LAUNCH, z).apply()

  var clickSwitch: Boolean
    get() = this.sharedPreferences.getBoolean(CLICK_SWITCH, true)
    set(bool) = this.sharedPreferences.edit().putBoolean(CLICK_SWITCH, bool).apply()

  var fcmRegId: String?
    get() = this.sharedPreferences.getString(FCM_PREF_KEY, null)
    set(str) = this.sharedPreferences.edit().putString(FCM_PREF_KEY, str).apply()

  var isNeedRegister: Boolean
    get() = this.sharedPreferences.getBoolean(NEED_REGISTER, true)
    set(z) = this.sharedPreferences.edit().putBoolean(NEED_REGISTER, z).apply()

  init {
    this.sharedPreferences = context.getSharedPreferences("MAIN_PREF", 0)
  }

  fun actionClickOffer(): Boolean {
    val z: Boolean
    var i = 1
    val i2 = this.sharedPreferences.getInt(CLICK_OFFER, 1)
    if (i2 < 10) {
      i = 1 + i2
      z = false
    } else {
      z = true
    }
    this.sharedPreferences.edit().putInt(CLICK_OFFER, i).apply()
    return z
  }

  fun actionClickInters(): Boolean {
    val z: Boolean
    var i = 1
    val i2 = this.sharedPreferences.getInt(CLICK_INTERS, 1)
    if (i2 < 10) {
      i = 1 + i2
      z = false
    } else {
      z = true
    }
    this.sharedPreferences.edit().putInt(CLICK_INTERS, i).apply()
    return z
  }

  companion object {
    private val CLICK_INTERS = "_.MAX_CLICK_INTERS "
    private val CLICK_OFFER = "_.MAX_CLICK_OFFER"
    private val CLICK_SWITCH = "_.MAX_CLICK_SWITCH"
    private val FCM_PREF_KEY = "_.FCM_PREF_KEY"
    private val FIRST_LAUNCH = "_.FIRST_LAUNCH"
    private val MAX_CLICK_INTERS = 10
    private val MAX_CLICK_OFFER = 10
    private val NEED_REGISTER = "_.NEED_REGISTER"
  }
}
