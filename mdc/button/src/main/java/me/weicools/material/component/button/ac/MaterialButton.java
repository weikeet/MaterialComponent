package me.weicools.material.component.button.ac;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import me.weicools.material.component.button.R;

/**
 * @author weicools
 * @date 2020.09.01
 */
public class MaterialButton extends AppCompatButton {

  public MaterialButton(@NonNull Context context) {
    super(context);
    init(context, null);
  }

  public MaterialButton(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public MaterialButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, @Nullable AttributeSet attrs) {
    GradientDrawable drawable = new GradientDrawable();
    drawable.setColors(new int[] { -1, -1 });
    // drawable.setColors(new int[] { ContextCompat.getColor(context, R.color.app_blue), ContextCompat.getColor(context, R.color.app_blue_dark) });
    setBackground(drawable);
  }
}
