package me.weicools.material.component.button

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.appcompat.content.res.AppCompatResources

/**
 * @author weicools
 * @date 2020.09.01
 */
class MaterialDrawableHelper {
  companion object {
    const val INVALID_SRC = -1
    const val INVALID_COLOR = -1
    const val DEFAULT_RADIUS = 0f
    const val DEFAULT_STROKE_WIDTH = 0
    const val DEFAULT_STROKE_DASH = 0f

    const val TOP_BOTTOM = 0
    const val BOTTOM_TOP = 1
    const val LEFT_RIGHT = 2
    const val RIGHT_LEFT = 3
    const val TL_BR = 4
    const val TR_BL = 5
    const val BL_TR = 6
    const val BR_TL = 7
  }

  @IntDef(TOP_BOTTOM, BOTTOM_TOP, LEFT_RIGHT, RIGHT_LEFT, TL_BR, TR_BL, BL_TR, BR_TL)
  @Retention(AnnotationRetention.SOURCE)
  annotation class Orientation

  @DrawableRes
  var srcBackground = INVALID_SRC

  var rippleEnable = true

  @ColorInt
  var rippleColor = INVALID_COLOR

  @ColorInt
  var solidColor = INVALID_COLOR

  @ColorInt
  var gradientStartColor = INVALID_COLOR

  @ColorInt
  var gradientCenterColor = INVALID_COLOR

  @ColorInt
  var gradientEndColor = INVALID_COLOR

  @Orientation
  var gradientOrientation = TOP_BOTTOM

  var cornerRadius = DEFAULT_RADIUS
  var cornerTopLeftRadius = DEFAULT_RADIUS
  var cornerTopRightRadius = DEFAULT_RADIUS
  var cornerBottomLeftRadius = DEFAULT_RADIUS
  var cornerBottomRightRadius = DEFAULT_RADIUS

  @ColorInt
  var strokeColor = INVALID_COLOR
  var strokeWidth = DEFAULT_STROKE_WIDTH
  var strokeDashGap = DEFAULT_STROKE_DASH
  var strokeDashWidth = DEFAULT_STROKE_DASH

  fun buildDrawable(context: Context): Drawable? {
    val rippleColors = if (rippleColor == INVALID_COLOR) {
      AppCompatResources.getColorStateList(context, R.color.ripple_button)
    } else {
      ColorStateList.valueOf(rippleColor)
    }

    if (srcBackground != INVALID_SRC) {
      val srcDrawable = AppCompatResources.getDrawable(context, srcBackground)
      if (rippleEnable && srcDrawable !is RippleDrawable
        && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
      ) {
        return RippleDrawable(rippleColors, srcDrawable, null)
      }
      return srcDrawable
    }

    val contentDrawable = GradientDrawable()

    //Set color
    if (solidColor != INVALID_COLOR) {
      contentDrawable.colors = intArrayOf(solidColor, solidColor)
    } else {

      if (gradientStartColor != INVALID_COLOR && gradientEndColor != INVALID_COLOR) {
        contentDrawable.orientation = when (gradientOrientation) {
          BOTTOM_TOP -> GradientDrawable.Orientation.BOTTOM_TOP
          LEFT_RIGHT -> GradientDrawable.Orientation.LEFT_RIGHT
          RIGHT_LEFT -> GradientDrawable.Orientation.RIGHT_LEFT
          TL_BR -> GradientDrawable.Orientation.TL_BR
          TR_BL -> GradientDrawable.Orientation.TR_BL
          BL_TR -> GradientDrawable.Orientation.BL_TR
          BR_TL -> GradientDrawable.Orientation.BR_TL
          else -> GradientDrawable.Orientation.TOP_BOTTOM
        }

        contentDrawable.colors = if (gradientCenterColor != INVALID_COLOR) {
          intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
        } else {
          intArrayOf(gradientStartColor, gradientEndColor)
        }
      }
    }

    //Set corners
    if (cornerRadius > DEFAULT_RADIUS) {
      contentDrawable.cornerRadius = cornerRadius

    } else if (cornerTopLeftRadius > DEFAULT_RADIUS || cornerTopRightRadius > DEFAULT_RADIUS
      || cornerBottomLeftRadius > DEFAULT_RADIUS || cornerBottomRightRadius > DEFAULT_RADIUS
    ) {
      contentDrawable.cornerRadii = floatArrayOf(
        cornerTopLeftRadius, cornerTopLeftRadius, cornerTopRightRadius, cornerTopRightRadius,
        cornerBottomRightRadius, cornerBottomRightRadius, cornerBottomLeftRadius, cornerBottomLeftRadius
      )
    }

    //Set stroke
    if (strokeColor != INVALID_COLOR && strokeWidth > DEFAULT_STROKE_WIDTH) {
      contentDrawable.setStroke(strokeWidth, strokeColor, strokeDashWidth, strokeDashGap)
    }

    if (rippleEnable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return RippleDrawable(rippleColors, contentDrawable, null)
    }
    return contentDrawable
  }
}