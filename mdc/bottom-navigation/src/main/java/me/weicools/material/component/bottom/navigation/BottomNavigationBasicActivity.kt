package me.weicools.material.component.bottom.navigation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_navigation_basic.*
import kotlinx.android.synthetic.main.include_card_view_search_bar.*
import me.weicools.common.Tools

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
    navigation.setOnNavigationItemSelectedListener { menuItem ->
      val itemId = menuItem.itemId
      when {
        itemId == R.id.navigation_favorites -> {
          search_text.text = menuItem.title
        }
        itemId == R.id.navigation_nearby -> {
          search_text.text = menuItem.title
        }
        itemId != R.id.navigation_recent -> {
        }
        else -> {
          search_text.text = menuItem.title
        }
      }
      true
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      findViewById<View>(R.id.nested_scroll_view).setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        animateNavigation(scrollY > oldScrollY)
        animateSearchBar(scrollY > oldScrollY)
      }
    }

    bt_menu.setOnClickListener { finish() }

    // val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_1)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_2)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_3)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_4)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_5)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_6)
    // Glide.with(this).load(R.drawable.image_8).apply(options).into(image_7)

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
