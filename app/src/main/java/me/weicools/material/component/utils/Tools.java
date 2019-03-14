package me.weicools.material.component.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.os.EnvironmentCompat;
import androidx.core.widget.NestedScrollView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import java.text.SimpleDateFormat;
import java.util.Date;
import me.weicools.material.component.R;

/**
 * @author Weicools Create on 2019.01.23
 *
 * desc:
 */
public class Tools {
  public static void setSystemBarColor (Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = activity.getWindow();
      window.addFlags(Integer.MIN_VALUE);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
    }
  }

  public static void setSystemBarColor (Activity activity, @ColorRes int i) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = activity.getWindow();
      window.addFlags(Integer.MIN_VALUE);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(activity.getResources().getColor(i));
    }
  }

  public static void setSystemBarColorDialog (Context context, Dialog dialog, @ColorRes int i) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = dialog.getWindow();
      if (window != null) {
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(context.getResources().getColor(i));
      }
    }
  }

  public static void setSystemBarLight (Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      View findViewById = activity.findViewById(R.id.container);
      findViewById.setSystemUiVisibility(findViewById.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
  }

  public static void setSystemBarLightDialog (Dialog dialog) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      View findViewById = dialog.findViewById(R.id.container);
      findViewById.setSystemUiVisibility(findViewById.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
  }

  public static void clearSystemBarLight (Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
    }
  }

  public static void setSystemBarTransparent (Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = activity.getWindow();
      window.addFlags(Integer.MIN_VALUE);
      window.setStatusBarColor(Color.TRANSPARENT);
    }
  }

  public static void displayImageOriginal (Context context, ImageView imageView, @DrawableRes int res) {
    RequestOptions options = new RequestOptions();
    options.diskCacheStrategy(DiskCacheStrategy.NONE);
    Glide.with(context).load(res).apply(options).into(imageView);
  }

  //public static void displayImageRound (final Context context, final ImageView imageView, @DrawableRes int i) {
  //  RequestOptions options = new RequestOptions();
  //  options.centerCrop();
  //  Glide.with(context).load(Integer.valueOf(i)).apply(options).into(new BitmapImageViewTarget(imageView) {
  //    @Override
  //    protected void setResource (Bitmap bitmap) {
  //      Drawable create = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
  //      ((RoundedBitmapDrawable) create).setCircular(true);
  //      imageView.setImageDrawable(create);
  //    }
  //  });
  //}

  public static void displayImageOriginal (Context context, ImageView imageView, String str) {
    RequestOptions options = new RequestOptions();
    options.diskCacheStrategy(DiskCacheStrategy.NONE);
    Glide.with(context).load(str).apply(options).into(imageView);
  }

  public static String getFormattedDateShort (Long l) {
    return new SimpleDateFormat("MMM dd, yyyy").format(new Date(l));
  }

  public static String getFormattedDateSimple (Long l) {
    return new SimpleDateFormat("MMMM dd, yyyy").format(new Date(l));
  }

  public static String getFormattedDateEvent (Long l) {
    return new SimpleDateFormat("EEE, MMM dd yyyy").format(new Date(l));
  }

  public static String getFormattedTimeEvent (Long l) {
    return new SimpleDateFormat("h:mm a").format(new Date(l));
  }

  public static String getEmailFromName (String str) {
    return (str == null || str.equals("")) ? str : str.replaceAll(" ", ".").toLowerCase().concat("@mail.com");
  }

  public static int dpToPx (Context context, int dpValue) {
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) dpValue,
        context.getResources().getDisplayMetrics()));
  }

  public static int dip2px (Context context, float f) {
    return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
  }

  public static int px2dip (Context context, float f) {
    return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
  }
  //public static GoogleMap configActivityMaps(GoogleMap googleMap) {
  //  googleMap.setMapType(1);
  //  googleMap.getUiSettings().setZoomControlsEnabled(false);
  //  googleMap.getUiSettings().setCompassEnabled(true);
  //  googleMap.getUiSettings().setRotateGesturesEnabled(true);
  //  googleMap.getUiSettings().setZoomGesturesEnabled(true);
  //  googleMap.getUiSettings().setScrollGesturesEnabled(true);
  //  googleMap.getUiSettings().setMapToolbarEnabled(true);
  //  return googleMap;
  //}

  public static void copyToClipboard (Context context, String str) {
    ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(
        ClipData.newPlainText("clipboard", str));
    Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
  }

  public static void nestedScrollTo (final NestedScrollView nestedScrollView, final View view) {
    nestedScrollView.post(() -> nestedScrollView.scrollTo(500, view.getBottom()));
  }

  public static boolean toggleArrow (View view) {
    if (view.getRotation() == 0.0f) {
      view.animate().setDuration(200).rotation(180.0f);
      return true;
    }
    view.animate().setDuration(200).rotation(0.0f);
    return false;
  }

  public static boolean toggleArrow (boolean z, View view) {
    return toggleArrow(z, view, true);
  }

  public static boolean toggleArrow (boolean z, View view, boolean z2) {
    long j = 0;
    ViewPropertyAnimator animate;
    if (z) {
      animate = view.animate();
      if (z2) {
        j = 200;
      }
      animate.setDuration(j).rotation(180.0f);
      return true;
    }
    animate = view.animate();
    if (z2) {
      j = 200;
    }
    animate.setDuration(j).rotation(0.0f);
    return false;
  }

  public static void changeNavigateionIconColor (Toolbar toolbar, @ColorInt int i) {
    Drawable navigationIcon = toolbar.getNavigationIcon();
    navigationIcon.mutate();
    navigationIcon.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
  }

  public static void changeMenuIconColor (Menu menu, @ColorInt int i) {
    for (int i2 = 0; i2 < menu.size(); i2++) {
      Drawable icon = menu.getItem(i2).getIcon();
      if (icon != null) {
        icon.mutate();
        icon.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
      }
    }
  }

  public static void changeOverflowMenuIconColor (Toolbar toolbar, @ColorInt int i) {
    try {
      Drawable overflowIcon = toolbar.getOverflowIcon();
      overflowIcon.mutate();
      overflowIcon.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
    } catch (Exception unused) {
    }
  }

  public static int getScreenWidth () {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public static int getScreenHeight () {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  public static String toCamelCase (String str) {
    str = str.toLowerCase();
    StringBuilder stringBuilder = new StringBuilder();
    Object obj = 1;
    for (char c : str.toCharArray()) {
      char c2 = 0;
      if (Character.isSpaceChar(c2)) {
        obj = 1;
      } else if (obj != null) {
        c2 = Character.toTitleCase(c2);
        obj = null;
      }
      stringBuilder.append(c2);
    }
    return stringBuilder.toString();
  }

  public static String insertPeriodically (String str, String str2, int i) {
    StringBuilder stringBuilder = new StringBuilder((str.length() + (str2.length() * (str.length() / i))) + 1);
    String str3 = "";
    int i2 = 0;
    while (i2 < str.length()) {
      stringBuilder.append(str3);
      int i3 = i2 + i;
      stringBuilder.append(str.substring(i2, Math.min(i3, str.length())));
      i2 = i3;
      str3 = str2;
    }
    return stringBuilder.toString();
  }

  public static void rateAction (Activity activity) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("market://details?id=");
    stringBuilder.append(activity.getPackageName());
    try {
      activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
    } catch (ActivityNotFoundException unused) {
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("http://play.google.com/store/apps/details?id=");
      stringBuilder2.append(activity.getPackageName());
      activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder2.toString())));
    }
  }

  public static String getDeviceName () {
    String str = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str)) {
      return str2;
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append(" ");
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }

  public static String getAndroidVersion () {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Build.VERSION.RELEASE);
    stringBuilder.append("");
    return stringBuilder.toString();
  }

  public static int getVersionCode (Context context) {
    try {
      return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    } catch (PackageManager.NameNotFoundException unused) {
      return -1;
    }
  }

  public static String getVersionName (Context context) {
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(context.getString(R.string.app_version));
      stringBuilder.append(" ");
      stringBuilder.append(packageInfo.versionName);
      return stringBuilder.toString();
    } catch (PackageManager.NameNotFoundException unused) {
      return context.getString(R.string.version_unknown);
    }
  }

  public static String getVersionNamePlain (Context context) {
    try {
      return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException unused) {
      return context.getString(R.string.version_unknown);
    }
  }

  //public static DeviceInfo getDeviceInfo(Context context) {
  //  DeviceInfo deviceInfo = new DeviceInfo();
  //  deviceInfo.device = getDeviceName();
  //  deviceInfo.os_version = getAndroidVersion();
  //  StringBuilder stringBuilder = new StringBuilder();
  //  stringBuilder.append(getVersionCode(context));
  //  stringBuilder.append(" (");
  //  stringBuilder.append(getVersionNamePlain(context));
  //  stringBuilder.append(")");
  //  deviceInfo.app_version = stringBuilder.toString();
  //  deviceInfo.serial = getDeviceID(context);
  //  return deviceInfo;
  //}

  public static String getDeviceID (Context context) {
    String str = Build.SERIAL;
    if (str == null || str.trim().isEmpty() || str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
      try {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
      } catch (Exception unused) {
        return str;
      }
    }
    return str;
  }

  public static String getFormattedDateOnly (Long l) {
    return new SimpleDateFormat("dd MMM yy").format(new Date(l.longValue()));
  }

  public static void directLinkToBrowser (Activity activity, String str) {
    try {
      activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    } catch (Exception unused) {
      Toast.makeText(activity, "Ops, Cannot open url", Toast.LENGTH_LONG).show();
    }
  }

  public static void openInAppBrowser (Activity activity, String str, boolean z) {
    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
    builder.setToolbarColor(activity.getResources().getColor(R.color.grey_90));
    builder.setSecondaryToolbarColor(activity.getResources().getColor(R.color.colorPrimary));
    CustomTabsIntent build = builder.build();
    String packageNameToUse = CustomTabsHelper.getPackageNameToUse(activity);
    if (packageNameToUse == null) {
      // TODO: 2019/1/23 start webView
      //ActivityWebView.navigate(activity, str, z);
    } else if (URLUtil.isValidUrl(str)) {
      build.intent.setPackage(packageNameToUse);
      build.intent.setData(Uri.parse(str));
      activity.startActivityForResult(build.intent, 509);
    } else {
      Toast.makeText(activity, "Ops, Cannot open url", Toast.LENGTH_LONG).show();
    }
  }
}
