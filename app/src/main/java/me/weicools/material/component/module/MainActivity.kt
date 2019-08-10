package me.weicools.material.component.module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import me.weicools.material.component.R
import me.weicools.material.component.common.adapter.CommonFragmentPagerAdapter

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
class MainActivity : AppCompatActivity() {

  private var mFragmentList: MutableList<Fragment> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    main_bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
      when (menuItem.itemId) {
        R.id.main_bottom_designs -> {
        }
        R.id.main_bottom_settings -> {
        }
        else -> {
        }
      }
      view_pager.currentItem = menuItem.order
      true
    }

    mFragmentList.add(MainDesignFragment.newInstance())
    mFragmentList.add(SettingsFragment.newInstance())
    view_pager.adapter = CommonFragmentPagerAdapter(supportFragmentManager, mFragmentList)
    view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

      override fun onPageSelected(position: Int) {
        main_bottom_navigation.menu.getItem(position).isChecked = true
      }

      override fun onPageScrollStateChanged(state: Int) {}
    })
  }
}
