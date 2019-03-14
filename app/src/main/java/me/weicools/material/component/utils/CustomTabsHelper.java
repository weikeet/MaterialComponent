package me.weicools.material.component.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class CustomTabsHelper {
  private static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
  static final String BETA_PACKAGE = "com.chrome.beta";
  static final String DEV_PACKAGE = "com.chrome.dev";
  private static final String EXTRA_CUSTOM_TABS_KEEP_ALIVE = "android.support.customtabs.extra.KEEP_ALIVE";
  static final String LOCAL_PACKAGE = "com.google.android.apps.chrome";
  static final String STABLE_PACKAGE = "com.android.chrome";
  private static final String TAG = "CustomTabsHelper";
  private static String sPackageNameToUse;

  private CustomTabsHelper () {
  }

  public static String getPackageNameToUse (Context context) {
    if (sPackageNameToUse != null) {
      return sPackageNameToUse;
    }
    PackageManager packageManager = context.getPackageManager();
    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
    ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
    String obj = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
    List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
    List arrayList = new ArrayList();
    for (ResolveInfo resolveInfo : queryIntentActivities) {
      Intent intent2 = new Intent();
      intent2.setAction("android.support.customtabs.action.CustomTabsService");
      intent2.setPackage(resolveInfo.activityInfo.packageName);
      if (packageManager.resolveService(intent2, 0) != null) {
        arrayList.add(resolveInfo.activityInfo.packageName);
      }
    }
    if (arrayList.isEmpty()) {
      sPackageNameToUse = null;
    } else if (arrayList.size() == 1) {
      sPackageNameToUse = (String) arrayList.get(0);
    } else if (!TextUtils.isEmpty(obj) && !hasSpecializedHandlerIntents(context, intent) && arrayList.contains(obj)) {
      sPackageNameToUse = obj;
    } else if (arrayList.contains(STABLE_PACKAGE)) {
      sPackageNameToUse = STABLE_PACKAGE;
    } else if (arrayList.contains(BETA_PACKAGE)) {
      sPackageNameToUse = BETA_PACKAGE;
    } else if (arrayList.contains(DEV_PACKAGE)) {
      sPackageNameToUse = DEV_PACKAGE;
    } else if (arrayList.contains(LOCAL_PACKAGE)) {
      sPackageNameToUse = LOCAL_PACKAGE;
    }
    return sPackageNameToUse;
  }

  private static boolean hasSpecializedHandlerIntents (Context context, Intent intent) {
    try {
      List<ResolveInfo> queryIntentActivities =
          context.getPackageManager().queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
      if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
        return false;
      }

      for (ResolveInfo resolveInfo : queryIntentActivities) {
        IntentFilter intentFilter = resolveInfo.filter;
        if (intentFilter != null && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
          return true;
        }
      }
      return false;
    } catch (RuntimeException unused) {
      Log.e(TAG, "Runtime exception while getting specialized handlers");
    }
    return false;
  }
}
