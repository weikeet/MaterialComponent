package me.weicools.material.component.utils

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.TypedValue
import android.view.Menu
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.Window
import android.view.WindowManager
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.os.EnvironmentCompat
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.Date
import me.weicools.material.component.R

/**
 * @author Weicools Create on 2019.01.23
 *
 * desc:
 */
object Tools {

  val screenWidth: Int
    get() = Resources.getSystem().displayMetrics.widthPixels

  val screenHeight: Int
    get() = Resources.getSystem().displayMetrics.heightPixels

  val deviceName: String
    get() {
      val str = Build.MANUFACTURER
      val str2 = Build.MODEL
      if (str2.startsWith(str)) {
        return str2
      }
      val stringBuilder = StringBuilder()
      stringBuilder.append(str)
      stringBuilder.append(" ")
      stringBuilder.append(str2)
      return stringBuilder.toString()
    }

  val androidVersion: String
    get() {
      val stringBuilder = StringBuilder()
      stringBuilder.append(Build.VERSION.RELEASE)
      stringBuilder.append("")
      return stringBuilder.toString()
    }

  fun setSystemBarColor(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = activity.window
      window.addFlags(Integer.MIN_VALUE)
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
      window.statusBarColor = activity.resources.getColor(R.color.colorPrimaryDark)
    }
  }

  fun setSystemBarColor(activity: Activity, @ColorRes i: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = activity.window
      window.addFlags(Integer.MIN_VALUE)
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
      window.statusBarColor = activity.resources.getColor(i)
    }
  }

  fun setSystemBarColorDialog(context: Context, dialog: Dialog, @ColorRes i: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = dialog.window
      if (window != null) {
        window.addFlags(Integer.MIN_VALUE)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = context.resources.getColor(i)
      }
    }
  }

  fun setSystemBarLight(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val findViewById = activity.findViewById<View>(R.id.container)
      findViewById.systemUiVisibility = findViewById.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
  }

  fun setSystemBarLightDialog(dialog: Dialog) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val findViewById = dialog.findViewById<View>(R.id.container)
      findViewById.systemUiVisibility = findViewById.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
  }

  fun clearSystemBarLight(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
    }
  }

  fun setSystemBarTransparent(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = activity.window
      window.addFlags(Integer.MIN_VALUE)
      window.statusBarColor = Color.TRANSPARENT
    }
  }

  fun displayImageOriginal(context: Context, imageView: ImageView, @DrawableRes res: Int) {
    val options = RequestOptions()
    options.diskCacheStrategy(DiskCacheStrategy.NONE)
    Glide.with(context).load(res).apply(options).into(imageView)
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

  fun displayImageOriginal(context: Context, imageView: ImageView, str: String) {
    val options = RequestOptions()
    options.diskCacheStrategy(DiskCacheStrategy.NONE)
    Glide.with(context).load(str).apply(options).into(imageView)
  }

  fun getFormattedDateShort(l: Long?): String {
    return SimpleDateFormat("MMM dd, yyyy").format(Date(l!!))
  }

  fun getFormattedDateSimple(l: Long?): String {
    return SimpleDateFormat("MMMM dd, yyyy").format(Date(l!!))
  }

  fun getFormattedDateEvent(l: Long?): String {
    return SimpleDateFormat("EEE, MMM dd yyyy").format(Date(l!!))
  }

  fun getFormattedTimeEvent(l: Long?): String {
    return SimpleDateFormat("h:mm a").format(Date(l!!))
  }

  fun getEmailFromName(str: String?): String? {
    return if (str == null || str == "") str else str.replace(" ".toRegex(), ".").toLowerCase() + "@mail.com"
  }

  fun dpToPx(context: Context, dpValue: Int): Int {
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(),
        context.resources.displayMetrics))
  }

  fun dip2px(context: Context, f: Float): Int {
    return (f * context.resources.displayMetrics.density + 0.5f).toInt()
  }

  fun px2dip(context: Context, f: Float): Int {
    return (f / context.resources.displayMetrics.density + 0.5f).toInt()
  }

  fun nestedScrollTo(nestedScrollView: NestedScrollView, view: View) {
    nestedScrollView.post { nestedScrollView.scrollTo(500, view.bottom) }
  }

  fun toggleArrow(view: View): Boolean {
    if (view.rotation == 0.0f) {
      view.animate().setDuration(200).rotation(180.0f)
      return true
    }
    view.animate().setDuration(200).rotation(0.0f)
    return false
  }

  @JvmOverloads
  fun toggleArrow(z: Boolean, view: View, z2: Boolean = true): Boolean {
    var j: Long = 0
    val animate: ViewPropertyAnimator
    if (z) {
      animate = view.animate()
      if (z2) {
        j = 200
      }
      animate.setDuration(j).rotation(180.0f)
      return true
    }
    animate = view.animate()
    if (z2) {
      j = 200
    }
    animate.setDuration(j).rotation(0.0f)
    return false
  }

  fun changeNavigateionIconColor(toolbar: Toolbar, @ColorInt i: Int) {
    val navigationIcon = toolbar.navigationIcon
    navigationIcon!!.mutate()
    navigationIcon.setColorFilter(i, PorterDuff.Mode.SRC_ATOP)
  }

  fun changeMenuIconColor(menu: Menu, @ColorInt i: Int) {
    for (i2 in 0 until menu.size()) {
      val icon = menu.getItem(i2).icon
      if (icon != null) {
        icon.mutate()
        icon.setColorFilter(i, PorterDuff.Mode.SRC_ATOP)
      }
    }
  }

  fun changeOverflowMenuIconColor(toolbar: Toolbar, @ColorInt i: Int) {
    try {
      val overflowIcon = toolbar.overflowIcon
      overflowIcon!!.mutate()
      overflowIcon.setColorFilter(i, PorterDuff.Mode.SRC_ATOP)
    } catch (unused: Exception) {
    }

  }

  fun toCamelCase(str: String): String {
    var str = str
    str = str.toLowerCase()
    val stringBuilder = StringBuilder()
    var obj: Any? = 1
    for (c in str.toCharArray()) {
      var c2: Char = 0.toChar()
      if (Character.isSpaceChar(c2)) {
        obj = 1
      } else if (obj != null) {
        c2 = Character.toTitleCase(c2)
        obj = null
      }
      stringBuilder.append(c2)
    }
    return stringBuilder.toString()
  }

  fun insertPeriodically(str: String, str2: String, i: Int): String {
    val stringBuilder = StringBuilder(str.length + str2.length * (str.length / i) + 1)
    var str3 = ""
    var i2 = 0
    while (i2 < str.length) {
      stringBuilder.append(str3)
      val i3 = i2 + i
      stringBuilder.append(str.substring(i2, Math.min(i3, str.length)))
      i2 = i3
      str3 = str2
    }
    return stringBuilder.toString()
  }

  fun rateAction(activity: Activity) {
    val stringBuilder = StringBuilder()
    stringBuilder.append("market://details?id=")
    stringBuilder.append(activity.packageName)
    try {
      activity.startActivity(Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())))
    } catch (unused: ActivityNotFoundException) {
      val stringBuilder2 = StringBuilder()
      stringBuilder2.append("http://play.google.com/store/apps/details?id=")
      stringBuilder2.append(activity.packageName)
      activity.startActivity(Intent("android.intent.action.VIEW", Uri.parse(stringBuilder2.toString())))
    }

  }

  fun getVersionCode(context: Context): Int {
    try {
      return context.packageManager.getPackageInfo(context.packageName, 0).versionCode
    } catch (unused: PackageManager.NameNotFoundException) {
      return -1
    }

  }

  fun getVersionName(context: Context): String {
    try {
      val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
      val stringBuilder = StringBuilder()
      stringBuilder.append(context.getString(R.string.app_version))
      stringBuilder.append(" ")
      stringBuilder.append(packageInfo.versionName)
      return stringBuilder.toString()
    } catch (unused: PackageManager.NameNotFoundException) {
      return context.getString(R.string.version_unknown)
    }

  }

  fun getVersionNamePlain(context: Context): String {
    try {
      return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    } catch (unused: PackageManager.NameNotFoundException) {
      return context.getString(R.string.version_unknown)
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

  fun getDeviceID(context: Context): String? {
    val str = Build.SERIAL
    if (str == null || str.trim { it <= ' ' }.isEmpty() || str == EnvironmentCompat.MEDIA_UNKNOWN) {
      try {
        return Settings.Secure.getString(context.contentResolver, "android_id")
      } catch (unused: Exception) {
        return str
      }

    }
    return str
  }

  fun getFormattedDateOnly(l: Long): String {
    return SimpleDateFormat("dd MMM yy").format(Date(l))
  }

  fun directLinkToBrowser(activity: Activity, str: String) {
    try {
      activity.startActivity(Intent("android.intent.action.VIEW", Uri.parse(str)))
    } catch (unused: Exception) {
      Toast.makeText(activity, "Ops, Cannot open url", Toast.LENGTH_LONG).show()
    }

  }

  fun openInAppBrowser(activity: Activity, str: String, z: Boolean) {
    val builder = CustomTabsIntent.Builder()
    builder.setToolbarColor(activity.resources.getColor(R.color.grey_90))
    builder.setSecondaryToolbarColor(activity.resources.getColor(R.color.colorPrimary))
    val build = builder.build()
    val packageNameToUse = CustomTabsHelper.getPackageNameToUse(activity)
    if (packageNameToUse == null) {
      // TODO: 2019/1/23 start webView
      //ActivityWebView.navigate(activity, str, z);
    } else if (URLUtil.isValidUrl(str)) {
      build.intent.setPackage(packageNameToUse)
      build.intent.data = Uri.parse(str)
      activity.startActivityForResult(build.intent, 509)
    } else {
      Toast.makeText(activity, "Ops, Cannot open url", Toast.LENGTH_LONG).show()
    }
  }

  fun setSystemBarColorInt(activity: Activity, @ColorInt i: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = activity.window
      window.addFlags(Integer.MIN_VALUE)
      window.clearFlags(67108864)
      window.statusBarColor = i
    }
  }
}
