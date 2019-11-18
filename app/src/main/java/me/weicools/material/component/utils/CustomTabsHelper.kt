package me.weicools.material.component.utils

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import java.util.ArrayList

object CustomTabsHelper {
  private const val TAG = "CustomTabsHelper"

  private const val DEV_PACKAGE = "com.chrome.dev"
  private const val BETA_PACKAGE = "com.chrome.beta"
  private const val STABLE_PACKAGE = "com.android.chrome"
  private const val LOCAL_PACKAGE = "com.google.android.apps.chrome"

  private const val EXTRA_CUSTOM_TABS_KEEP_ALIVE = "android.support.customtabs.extra.KEEP_ALIVE"
  private const val ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService"

  private var sPackageNameToUse: String? = null

  fun getPackageNameToUse(context: Context): String? {
    if (sPackageNameToUse != null) {
      return sPackageNameToUse
    }

    val packageManager = context.packageManager
    val intent = Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"))
    val resolveActivity = packageManager.resolveActivity(intent, 0)
    val obj = resolveActivity?.activityInfo?.packageName

    val arrayList = ArrayList<String>()
    val queryIntentActivities = packageManager.queryIntentActivities(intent, 0)
    for (resolveInfo in queryIntentActivities) {
      val tabsServiceIntent = Intent()
      tabsServiceIntent.action = "android.support.customtabs.action.CustomTabsService"
      tabsServiceIntent.setPackage(resolveInfo.activityInfo.packageName)
      if (packageManager.resolveService(tabsServiceIntent, 0) != null) {
        arrayList.add(resolveInfo.activityInfo.packageName)
      }
    }

    if (arrayList.isEmpty()) {
      sPackageNameToUse = null
    } else if (arrayList.size == 1) {
      sPackageNameToUse = arrayList.get(0)
    } else if (!TextUtils.isEmpty(obj) && !hasSpecializedHandlerIntents(context, intent) && arrayList.contains(obj)) {
      sPackageNameToUse = obj
    } else if (arrayList.contains(STABLE_PACKAGE)) {
      sPackageNameToUse = STABLE_PACKAGE
    } else if (arrayList.contains(BETA_PACKAGE)) {
      sPackageNameToUse = BETA_PACKAGE
    } else if (arrayList.contains(DEV_PACKAGE)) {
      sPackageNameToUse = DEV_PACKAGE
    } else if (arrayList.contains(LOCAL_PACKAGE)) {
      sPackageNameToUse = LOCAL_PACKAGE
    }

    return sPackageNameToUse
  }

  private fun hasSpecializedHandlerIntents(context: Context, intent: Intent): Boolean {
    try {
      val queryIntentActivities = context.packageManager.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER)
      if (queryIntentActivities.size == 0) {
        return false
      }

      for (resolveInfo in queryIntentActivities) {
        val intentFilter = resolveInfo.filter
        if (intentFilter != null && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
          return true
        }
      }
      return false
    } catch (unused: RuntimeException) {
      Log.e(TAG, "Runtime exception while getting specialized handlers")
    }

    return false
  }
}
