package me.weicools.material.component.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatButton

/**
 * @author weicools
 * @date 2020.09.01
 */
class MaterialButton @JvmOverloads constructor(
  context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = android.R.attr.buttonStyle
) : AppCompatButton(context, attributeSet, defStyleAttr) {
  companion object {
    private const val TAG = "MaterialButton"
  }

  init {
    attributeSet?.let { attrs -> buildBackground(attrs) }
  }

  private fun buildBackground(attrs: AttributeSet) {
    val ta = context.obtainStyledAttributes(attrs, R.styleable.MaterialButton)

    val drawableHelper = MaterialDrawableHelper().apply {
      srcBackground = ta.getResourceId(R.styleable.MaterialButton_mdb_Background, MaterialDrawableHelper.INVALID_SRC)

      rippleEnable = ta.getBoolean(R.styleable.MaterialButton_mdb_Ripple_enable, true)
      rippleColor = ta.getColor(R.styleable.MaterialButton_mdb_Ripple_color, MaterialDrawableHelper.INVALID_COLOR)

      solidColor = ta.getColor(R.styleable.MaterialButton_mdb_Solid_color, MaterialDrawableHelper.INVALID_COLOR)

      gradientStartColor = ta.getColor(R.styleable.MaterialButton_mdb_Gradient_startColor, MaterialDrawableHelper.INVALID_COLOR)
      gradientCenterColor = ta.getColor(R.styleable.MaterialButton_mdb_Gradient_centerColor, MaterialDrawableHelper.INVALID_COLOR)
      gradientEndColor = ta.getColor(R.styleable.MaterialButton_mdb_Gradient_endColor, MaterialDrawableHelper.INVALID_COLOR)
      gradientOrientation = ta.getInt(R.styleable.MaterialButton_mdb_Gradient_orientation, 0)

      cornerRadius = ta.getDimension(R.styleable.MaterialButton_mdb_Corner_radius, MaterialDrawableHelper.DEFAULT_RADIUS)
      cornerTopLeftRadius = ta.getDimension(R.styleable.MaterialButton_mdb_Corner_topLeftRadius, MaterialDrawableHelper.DEFAULT_RADIUS)
      cornerTopRightRadius = ta.getDimension(R.styleable.MaterialButton_mdb_Corner_topRightRadius, MaterialDrawableHelper.DEFAULT_RADIUS)
      cornerBottomLeftRadius = ta.getDimension(R.styleable.MaterialButton_mdb_Corner_bottomLeftRadius, MaterialDrawableHelper.DEFAULT_RADIUS)
      cornerBottomRightRadius = ta.getDimension(R.styleable.MaterialButton_mdb_Corner_bottomRightRadius, MaterialDrawableHelper.DEFAULT_RADIUS)

      strokeColor = ta.getColor(R.styleable.MaterialButton_mdb_Stroke_color, MaterialDrawableHelper.INVALID_COLOR)
      strokeWidth = ta.getDimensionPixelOffset(R.styleable.MaterialButton_mdb_Stroke_width, MaterialDrawableHelper.DEFAULT_STROKE_WIDTH)
      strokeDashGap = ta.getDimension(R.styleable.MaterialButton_mdb_Stroke_dashGap, 0f)
      strokeDashWidth = ta.getDimension(R.styleable.MaterialButton_mdb_Stroke_dashWidth, 0f)
    }

    background = drawableHelper.buildDrawable(context)

    ta.recycle()
  }

  override fun setBackground(background: Drawable?) {
    super.setBackground(background)
    Log.d(TAG, "setBackground: background=${background}")
  }
}