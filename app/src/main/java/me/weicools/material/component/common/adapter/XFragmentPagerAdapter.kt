package me.weicools.material.component.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author Weicools Create on 2019.03.29
 *
 * desc:
 */
class XFragmentPagerAdapter : FragmentPagerAdapter {
  private var mFragmentList: List<Fragment>
  private var mTitles: Array<String>? = null

  constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    mFragmentList = fragmentList
  }

  constructor(fm: FragmentManager, fragmentList: List<Fragment>, titles: Array<String>) : super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    mTitles = titles
    mFragmentList = fragmentList
  }

  override fun getItem(position: Int): Fragment {
    return mFragmentList[position]
  }

  override fun getCount(): Int {
    return mFragmentList.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return if (mTitles == null || mTitles!!.size != mFragmentList.size) {
      ""
    } else mTitles!![position]
  }
}
