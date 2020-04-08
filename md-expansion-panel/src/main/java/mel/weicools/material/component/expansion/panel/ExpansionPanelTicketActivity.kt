package mel.weicools.material.component.expansion.panel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expansion_panel_ticket.*
import me.weicools.material.component.common.Tools

/**
 * @author weicools Create on 2020.01.21
 */
class ExpansionPanelTicketActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_expansion_panel_ticket)

    toolbar.setNavigationIcon(R.drawable.ic_menu)
    setSupportActionBar(toolbar)
    supportActionBar?.title = "Flight Ticket"
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    // todo Tools.setSystemBarColor(this)

    btnToggleInfo.setOnClickListener {
      toggleSectionInfo(it)
    }

    btnHideInfo.setOnClickListener {
      toggleSectionInfo(btnToggleInfo)
    }

    btnTogglePassenger.setOnClickListener {
      toggleSectionPassenger(it)
    }

    btnCopyCode.setOnClickListener {
      // todo Tools.copyToClipboard(applicationContext, tvBookingCode.text.toString())
    }
  }

  private fun toggleSectionInfo(view: View) {
    if (toggleArrow(view)) {
      ViewAnimation.expand(llExpandInfo) { Tools.nestedScrollTo(nestedScrollView, llExpandInfo) }
    } else {
      ViewAnimation.collapse(llExpandInfo)
    }
  }

  fun toggleSectionPassenger(view: View) {
    if (toggleArrow(view)) {
      ViewAnimation.expand(llExpandPassenger) { Tools.nestedScrollTo(nestedScrollView, llExpandPassenger) }
    } else {
      ViewAnimation.collapse(llExpandPassenger)
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