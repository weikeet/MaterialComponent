package me.weicools.material.component.utils;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import java.util.Random;
import me.weicools.material.component.R;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
public class UiUtils {
  public static int dp2px (Context context, float dpVal) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
        context.getResources().getDisplayMetrics());
  }

  public static int getRandomColor (Context context) {
    Random random = new Random();
    int val = random.nextInt(10);
    int colorId;
    switch (val) {
      case 0:
        colorId = R.color.amber_400;
        break;
      case 1:
        colorId = R.color.blue_400;
        break;
      case 2:
        colorId = R.color.blue_grey_400;
        break;
      case 3:
        colorId = R.color.cyan_400;
        break;
      case 4:
        colorId = R.color.deep_orange_400;
        break;
      case 5:
        colorId = R.color.deep_purple_400;
        break;
      case 6:
        colorId = R.color.purple_400;
        break;
      case 7:
        colorId = R.color.indigo_400;
        break;
      case 8:
        colorId = R.color.light_blue_400;
        break;
      case 9:
        colorId = R.color.lime_400;
        break;
      default:
        colorId = R.color.colorPrimary;
        break;
    }

    return ContextCompat.getColor(context, colorId);
  }
}
