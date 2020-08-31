package me.weicools.material.component.alert.dialog

import android.app.Activity
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.awesome_dilaog.*

/**
 * @author weicools
 * @date 2020.08.30
 */
class AwesomeDialog {
  /**
   *  Alert Dialog Positions
   **/
  enum class POSITIONS {
    CENTER, BOTTOM
  }

  companion object {
    private lateinit var layoutInflater: LayoutInflater

    /***
     * core method For Alert Dialog
     * */
    fun build(context: Activity): AlertDialog {
      layoutInflater = LayoutInflater.from(context)
      val alertDialog = AlertDialog.Builder(context, R.style.full_screen_dialog)
        .setView(R.layout.awesome_dilaog)
      val alert: AlertDialog = alertDialog.create()
      // Let's start with animation work. We just need to create a style and use it here as follows.
      // Pop In and Pop Out Animation yet pending
      // alert.window?.attributes?.windowAnimations = R.style.SlidingDialogAnimation
      alert.show()
      return alert
    }
  }
}

/***
 * Title Properties For Alert Dialog
 * */
fun AlertDialog.title(
  title: String, fontStyle: Typeface? = null, titleColor: Int = 0
): AlertDialog {
  this.title.text = title.trim()
  if (fontStyle != null) {
    this.title.typeface = fontStyle
  }
  if (titleColor != 0) {
    this.title.setTextColor(titleColor)
  }
  this.title.show()
  return this
}

/***
 * Dialog Background properties For Alert Dialog
 * */
fun AlertDialog.background(dialogBackgroundColor: Int? = null): AlertDialog {
  if (dialogBackgroundColor != null) {
    this.dialogContainer.setBackgroundResource(dialogBackgroundColor)
  }
  return this
}

/***
 * Positions of Alert Dialog
 * */
fun AlertDialog.position(
  position: AwesomeDialog.POSITIONS = AwesomeDialog.POSITIONS.BOTTOM
): AlertDialog {
  val layoutParams = dialogContainer.layoutParams as RelativeLayout.LayoutParams
  if (position == AwesomeDialog.POSITIONS.CENTER) {
    layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
  } else if (position == AwesomeDialog.POSITIONS.BOTTOM) {
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
  }
  dialogContainer!!.layoutParams = layoutParams
  return this
}

/***
 * Sub Title or Body of Alert Dialog
 * */
fun AlertDialog.body(body: String, fontStyle: Typeface? = null, color: Int = 0): AlertDialog {
  this.subtitle.text = body.trim()
  this.subtitle.show()
  if (fontStyle != null) {
    this.subtitle.typeface = fontStyle
  }
  if (color != 0) {
    this.subtitle.setTextColor(color)
  }
  return this
}

/***
 * Icon of  Alert Dialog
 * */
fun AlertDialog.icon(icon: Int, animateIcon: Boolean = false): AlertDialog {
  this.image.setImageResource(icon)
  this.image.show()
  // Pulse Animation for Icon
  if (animateIcon) {
    val pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.dialog_pulse)
    image.startAnimation(pulseAnimation)
  }
  return this
}

/***
 * onPositive Button Properties For Alert Dialog
 * */
fun AlertDialog.onPositive(
  text: String, buttonBackgroundColor: Int? = null,
  textColor: Int? = null, action: (() -> Unit)? = null
): AlertDialog {
  this.positiveButton.show()
  if (buttonBackgroundColor != null) {
    this.positiveButton.setBackgroundResource(buttonBackgroundColor)
  }
  if (textColor != null) {
    this.positiveButton.setTextColor(textColor)
  }
  this.positiveButton.text = text.trim()
  this.positiveButton.setOnClickListener {
    action?.invoke()
    dismiss()
  }
  return this
}

/***
 * onNegative Button Properties For Alert Dialog
 * */
fun AlertDialog.onNegative(
  text: String, buttonBackgroundColor: Int? = null,
  textColor: Int? = null, action: (() -> Unit)? = null
): AlertDialog {
  this.negativeButton.show()
  this.negativeButton.text = text.trim()
  if (textColor != null) {
    this.negativeButton.setTextColor(textColor)
  }
  if (buttonBackgroundColor != null) {
    this.negativeButton.setBackgroundResource(buttonBackgroundColor)
  }
  this.negativeButton.setOnClickListener {
    action?.invoke()
    dismiss()
  }
  return this
}

private fun View.show() {
  this.visibility = View.VISIBLE
}