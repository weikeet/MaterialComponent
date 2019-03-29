package me.weicools.material.component.module.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/**
 * @author Weicools Create on 2019.03.29
 *
 * desc:
 */
public class XFragmentPagerAdapter extends FragmentPagerAdapter {
  @NonNull private List<Fragment> mFragmentList;
  @Nullable private String[] mTitles;

  public XFragmentPagerAdapter (FragmentManager fm, List<Fragment> fragmentList) {
    super(fm);
    mFragmentList = fragmentList;
  }

  public XFragmentPagerAdapter (FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
    super(fm);
    mTitles = titles;
    mFragmentList = fragmentList;
  }

  @Override
  public Fragment getItem (int position) {
    return mFragmentList.get(position);
  }

  @Override
  public int getCount () {
    return mFragmentList.size();
  }

  @Nullable
  @Override
  public CharSequence getPageTitle (int position) {
    if (mTitles == null || mTitles.length != mFragmentList.size()) {
      return "";
    }
    return mTitles[position];
  }
}
