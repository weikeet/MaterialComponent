package me.weicools.material.component.module;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import me.weicools.material.component.R;
import me.weicools.material.component.config.RouterPath;
import me.weicools.material.component.module.adapter.XFragmentPagerAdapter;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
@Route(path = RouterPath.MODULE_MAIN)
public class MainActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.toolbar_title) TextView toolbarTitle;
  @BindView(R.id.app_bar_layout) AppBarLayout appBarLayout;
  @BindView(R.id.view_pager) ViewPager mViewPager;
  @BindView(R.id.main_bottom_navigation) BottomNavigationView mainBottomNavigation;

  List<Fragment> mFragmentList = new ArrayList<>();

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mainBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
      switch (menuItem.getItemId()) {
        case R.id.main_bottom_designs:
          break;
        case R.id.main_bottom_settings:
          break;
        default:
          break;
      }
      mViewPager.setCurrentItem(menuItem.getOrder());
      return true;
    });

    mFragmentList.add(MainDesignFragment.newInstance());
    mFragmentList.add(SettingsFragment.newInstance());
    mViewPager.setAdapter(new XFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) { }

      @Override
      public void onPageSelected (int position) {
        mainBottomNavigation.getMenu().getItem(position).setChecked(true);
      }

      @Override
      public void onPageScrollStateChanged (int state) { }
    });
  }
}
