package mel.weicools.material.component.expansion.panel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expansion_panel_basic.*
import me.weicools.material.component.common.Tools

/**
 * @author Weicools create on 2018.08.23
 *
 * desc:
 */
class ExpansionPanelBasicActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_expansion_panel_basic)

    initComponent()
  }

  private fun initComponent() {
    llExpand.visibility = View.GONE
    val toggleTextListener = View.OnClickListener { toggleSectionText(btnToggleText) }
    btnToggleText.setOnClickListener(toggleTextListener)
    btnHideText.setOnClickListener(toggleTextListener)

    this.llExpandInput.visibility = View.GONE
    val toggleInputListener = View.OnClickListener { toggleSectionInput(btnToggleInput) }
    this.btnToggleInput.setOnClickListener(toggleInputListener)
    this.btnHideInput.setOnClickListener(toggleInputListener)
    this.btnSaveInput.setOnClickListener {
      // Snackbar.make(parent_view, "Data saved" as CharSequence, Snackbar.LENGTH_SHORT).show()
      toggleSectionInput(btnToggleInput)
    }
  }

  private fun toggleSectionText(view: View) {
    if (toggleArrow(view)) {
      ViewAnimation.expand(this.llExpand) { Tools.nestedScrollTo(nestedScrollView, llExpand) }
    } else {
      ViewAnimation.collapse(this.llExpand)
    }
  }

  private fun toggleSectionInput(view: View) {
    if (toggleArrow(view)) {
      ViewAnimation.expand(this.llExpandInput) { Tools.nestedScrollTo(nestedScrollView, llExpandInput) }
    } else {
      ViewAnimation.collapse(this.llExpandInput)
    }
  }

  private fun toggleArrow(view: View): Boolean {
    if (view.rotation == 0.0f) {
      view.animate().setDuration(200).rotation(180.0f)
      return true
    }
    view.animate().setDuration(200).rotation(0.0f)
    return false
  }
}
