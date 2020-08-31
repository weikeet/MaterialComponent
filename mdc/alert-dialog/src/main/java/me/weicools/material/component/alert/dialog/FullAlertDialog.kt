package me.weicools.material.component.alert.dialog

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * @author weicools
 * @date 2020.08.30
 */
class FullAlertDialog(private val activty: Activity) : AlertDialog(activty, R.style.full_screen_dialog) {
  enum class POSITIONS {
    CENTER, BOTTOM
  }

  var title = ""
  var titleColor = 0
  val titleFont: Typeface? = null

  var subtitle = ""
  var subtitleColor = 0
  val subtitleFont: Typeface? = null

  var dialogBackgroundColor = 0

  var position = POSITIONS.BOTTOM

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val view = View.inflate(activty, R.layout.awesome_dilaog, null)

    val dialogContainer = view.findViewById<ConstraintLayout>(R.id.dialogContainer)
    val layoutParams = dialogContainer.layoutParams as FrameLayout.LayoutParams
    if (position == POSITIONS.BOTTOM) {
      layoutParams.gravity = Gravity.BOTTOM
    } else {
      layoutParams.gravity = Gravity.CENTER
    }

    val iconView = view.findViewById<ImageView>(R.id.image)
    val titleView = view.findViewById<TextView>(R.id.title)
    val subtitleView = view.findViewById<TextView>(R.id.subtitle)
    val positiveButton = view.findViewById<TextView>(R.id.positiveButton)
    val negativeButton = view.findViewById<TextView>(R.id.negativeButton)


  }
}