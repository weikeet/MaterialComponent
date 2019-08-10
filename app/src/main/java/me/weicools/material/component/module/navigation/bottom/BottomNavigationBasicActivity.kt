package me.weicools.material.component.module.navigation.bottom

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_navigation_basic.*
import kotlinx.android.synthetic.main.fragment_tabs_store.*
import kotlinx.android.synthetic.main.include_card_view_search_bar.*
import me.weicools.material.component.R
import me.weicools.material.component.utils.Tools

/**
 * @author Weicools create on 2018.08.23
 *
 * desc:
 */
class BottomNavigationBasicActivity : AppCompatActivity() {
  private var isNavigationHide: Boolean = false
  private var isSearchBarHide: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bottom_navigation_basic)

    initComponent()
  }

  private fun initComponent() {
    //todo setOnNavigationItemSelectedListener
//    navigation.setOnNavigationItemSelectedListener { menuItem ->
//      val itemId = menuItem.itemId
//      when {
//        itemId == R.id.navigation_favorites -> {
//          search_text.text = menuItem.title
//          return@setOnNavigationItemSelectedListener
//        }
//        itemId == R.id.navigation_nearby -> {
//          search_text.text = menuItem.title
//          return@setOnNavigationItemSelectedListener
//        }
//        itemId != R.id.navigation_recent -> {
//        }
//        else -> {
//          search_text.text = menuItem.title
//          return@setOnNavigationItemSelectedListener
//        }
//      }
//    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      findViewById<View>(R.id.nested_scroll_view).setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        animateNavigation(scrollY > oldScrollY)
        animateSearchBar(scrollY > oldScrollY)
      }
    }

    bt_menu.setOnClickListener { finish() }

    Tools.displayImageOriginal(this, image_1, R.drawable.image_8)
    Tools.displayImageOriginal(this, image_2, R.drawable.image_9)
    Tools.displayImageOriginal(this, image_3, R.drawable.image_15)
    Tools.displayImageOriginal(this, image_4, R.drawable.image_14)
    Tools.displayImageOriginal(this, image_5, R.drawable.image_12)
    Tools.displayImageOriginal(this, image_6, R.drawable.image_2)
    Tools.displayImageOriginal(this, image_7, R.drawable.image_5)
    Tools.setSystemBarColor(this, R.color.grey_5)
    Tools.setSystemBarLight(this)
  }

  private fun animateNavigation(z: Boolean) {
    if (!(isNavigationHide && z) && (isNavigationHide || z)) {
      isNavigationHide = z
      navigation!!.animate()
          .translationY((if (z) navigation!!.height * 2 else 0).toFloat())
          .setStartDelay(100)
          .setDuration(300)
          .start()
    }
  }

  private fun animateSearchBar(z: Boolean) {
    if (!(isSearchBarHide && z) && (isSearchBarHide || z)) {
      isSearchBarHide = z

      search_bar.animate()
          .translationY((if (z) -(search_bar.height * 2) else 0).toFloat())
          .setStartDelay(100)
          .setDuration(300)
          .start()
    }
  }
}
