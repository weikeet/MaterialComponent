package me.weicools.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * @author Weicools Create on 2019.03.29
 *
 * desc:
 */
class ExFragmentPagerAdapter(
  fm: FragmentManager,
  private val fragmentList: List<Fragment>,
  private val titleArray: Array<String>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  companion object {
    fun instantiateFragment(fm: FragmentManager, viewPager: ViewPager, position: Int, defaultResult: Fragment): Fragment {
      val tag = "android:switcher:" + viewPager.id + ":" + position
      val fragment = fm.findFragmentByTag(tag)
      return fragment ?: defaultResult
    }
  }

  override fun getItem(position: Int): Fragment {
    // 参考 https://mp.weixin.qq.com/s/MOWdbI5IREjQP1Px-WJY1Q
    // https://stackoverflow.com/questions/8785221/retrieve-a-fragment-from-a-viewpager
    // Fragment 使用 [FragmentPagerAdapter.instantiateFragment] 创建
    return fragmentList[position]
  }

  override fun getCount(): Int {
    return fragmentList.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return titleArray[position]
  }
}
