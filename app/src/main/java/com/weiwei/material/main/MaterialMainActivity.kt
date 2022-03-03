package com.weiwei.material.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.material.R

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
class MaterialMainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.material_activity_main)

    // TODO: apply window insets

    if (savedInstanceState == null) {
      val mainFragment = MaterialMainFragment()
      supportFragmentManager.beginTransaction().add(R.id.container, mainFragment).commit()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    super.onCreateOptionsMenu(menu)
    // TODO: create menu
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // TODO: item selected
    return super.onOptionsItemSelected(item)
  }

  override fun onBackPressed() {
    // TODO: handle back?
    super.onBackPressed()
  }

}
