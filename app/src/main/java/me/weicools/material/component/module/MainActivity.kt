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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().add(R.id.container, MainDesignFragment.newInstance()).commit()
    }
  }
}
