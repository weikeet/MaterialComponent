package me.weicools.material.component.expansion.panel

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expansion_panel_summary.*

/**
 * @author weicools Create on 2020.01.21
 */
class ExpansionPanelSummaryActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_expansion_panel_summary)

    setSupportActionBar(toolbar)
    supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

    tvExpansionPanelBasic.setOnClickListener {
      startActivity(Intent(this, ExpansionPanelBasicActivity::class.java))
    }
    tvExpansionPanelInvoice.setOnClickListener {
      startActivity(Intent(this, ExpansionPanelInvoiceActivity::class.java))
    }
    tvExpansionPanelTicket.setOnClickListener {
      startActivity(Intent(this, ExpansionPanelTicketActivity::class.java))
    }
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
      finish()
      return true
    }
    return super.onContextItemSelected(item)
  }
}