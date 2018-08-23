package me.weicools.material.component;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
public class MaterialApp extends Application {
  @Override
  public void onCreate () {
    super.onCreate();
    ARouter.openLog();
    ARouter.openDebug();
    ARouter.init(this);
  }
}
