package me.weicools.material.component.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
  private static final String CLICK_INTERS = "_.MAX_CLICK_INTERS ";
  private static final String CLICK_OFFER = "_.MAX_CLICK_OFFER";
  private static final String CLICK_SWITCH = "_.MAX_CLICK_SWITCH";
  private static final String FCM_PREF_KEY = "_.FCM_PREF_KEY";
  private static final String FIRST_LAUNCH = "_.FIRST_LAUNCH";
  private static final int MAX_CLICK_INTERS = 10;
  private static final int MAX_CLICK_OFFER = 10;
  private static final String NEED_REGISTER = "_.NEED_REGISTER";
  private Context context;
  private SharedPreferences sharedPreferences;

  public SharedPref (Context context) {
    this.context = context;
    this.sharedPreferences = context.getSharedPreferences("MAIN_PREF", 0);
  }

  public void setFirstLaunch (boolean z) {
    this.sharedPreferences.edit().putBoolean(FIRST_LAUNCH, z).apply();
  }

  public boolean isFirstLaunch () {
    return this.sharedPreferences.getBoolean(FIRST_LAUNCH, true);
  }

  public boolean actionClickOffer () {
    boolean z;
    int i = 1;
    int i2 = this.sharedPreferences.getInt(CLICK_OFFER, 1);
    if (i2 < 10) {
      i = 1 + i2;
      z = false;
    } else {
      z = true;
    }
    this.sharedPreferences.edit().putInt(CLICK_OFFER, i).apply();
    return z;
  }

  public boolean actionClickInters () {
    boolean z;
    int i = 1;
    int i2 = this.sharedPreferences.getInt(CLICK_INTERS, 1);
    if (i2 < 10) {
      i = 1 + i2;
      z = false;
    } else {
      z = true;
    }
    this.sharedPreferences.edit().putInt(CLICK_INTERS, i).apply();
    return z;
  }

  public boolean getClickSwitch () {
    return this.sharedPreferences.getBoolean(CLICK_SWITCH, true);
  }

  public void setClickSwitch (boolean bool) {
    this.sharedPreferences.edit().putBoolean(CLICK_SWITCH, bool).apply();
  }

  public void setFcmRegId (String str) {
    this.sharedPreferences.edit().putString(FCM_PREF_KEY, str).apply();
  }

  public String getFcmRegId () {
    return this.sharedPreferences.getString(FCM_PREF_KEY, null);
  }

  public void setNeedRegister (boolean z) {
    this.sharedPreferences.edit().putBoolean(NEED_REGISTER, z).apply();
  }

  public boolean isNeedRegister () {
    return this.sharedPreferences.getBoolean(NEED_REGISTER, true);
  }
}
