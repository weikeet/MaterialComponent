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
    lyt_expand_text.visibility = View.GONE
    bt_toggle_text.setOnClickListener {
      toggleSectionText(bt_toggle_text)
    }
    this.bt_hide_text.setOnClickListener {
      toggleSectionText(bt_toggle_text)
    }
    this.lyt_expand_input.visibility = View.GONE
    this.bt_toggle_input.setOnClickListener {
      toggleSectionInput(bt_toggle_input)
    }
    this.bt_hide_input.setOnClickListener {
      toggleSectionInput(bt_toggle_input)
    }
    this.bt_save_input.setOnClickListener {
      // Snackbar.make(parent_view, "Data saved" as CharSequence, Snackbar.LENGTH_SHORT).show()
      toggleSectionInput(bt_toggle_input)
    }
  }

  private fun toggleSectionText(view: View) {
    if (toggleArrow(view)) {
      ViewAnimation.expand(this.lyt_expand_text) { Tools.nestedScrollTo(nested_scroll_view, lyt_expand_text) }
    } else {
      ViewAnimation.collapse(this.lyt_expand_text)
    }
  }

  private fun toggleSectionInput(view: View) {
    if (toggleArrow(view)) {
      ViewAnimation.expand(this.lyt_expand_input) { Tools.nestedScrollTo(nested_scroll_view, lyt_expand_input) }
    } else {
      ViewAnimation.collapse(this.lyt_expand_input)
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
