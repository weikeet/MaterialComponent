package mel.weicools.material.component.expansion.panel

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expansion_panel_invoice.*
import me.weicools.material.component.common.Tools

/**
 * @author weicools Create on 2020.01.21
 */
class ExpansionPanelInvoiceActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_expansion_panel_invoice)

    setSupportActionBar(toolbar)
    supportActionBar?.title = null
    supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    // todo StatUtil

    btnToggleItems.setOnClickListener {
      toggleSection(it, llExpandItems)
    }

    btnToggleAddress.setOnClickListener {
      toggleSection(it, llExpandAddress)
    }

    btnToggleDescription.setOnClickListener {
      toggleSection(it, llExpandDescription)
    }

    btnCopyCode.setOnClickListener {
      // todo Tools.copyToClipboard(applicationContext, tvInvoiceCode.text.toString())
    }
  }

  private fun toggleSection(view1: View, view2: View) {
    if (toggleArrow(view1)) {
      ViewAnimation.expand(view2) { Tools.nestedScrollTo(nestedScrollView, view2) }
    } else {
      ViewAnimation.collapse(view2)
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

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
      finish()
      return true;
    }
    return super.onOptionsItemSelected(item)
  }
}