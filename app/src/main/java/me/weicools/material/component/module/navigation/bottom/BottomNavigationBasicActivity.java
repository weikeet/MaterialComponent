package me.weicools.material.component.module.navigation.bottom;

import android.os.Build;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import me.weicools.material.component.R;
import me.weicools.material.component.config.RouterPath;
import me.weicools.material.component.utils.Tools;

/**
 * @author Weicools create on 2018.08.23
 *
 * desc:
 */
@Route(path = RouterPath.MODULE_BOTTOM_NAVIGATION_BASIC)
public class BottomNavigationBasicActivity extends AppCompatActivity {
  private boolean isNavigationHide, isSearchBarHide;
  private TextView mTextMessage;
  private BottomNavigationView navigation;
  private View searchBar;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_navigation_basic);

    initComponent();
  }

  private void initComponent () {
    searchBar = findViewById(R.id.search_bar);
    mTextMessage = findViewById(R.id.search_text);
    navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(menuItem -> {
      int itemId = menuItem.getItemId();
      if (itemId == R.id.navigation_favorites) {
        mTextMessage.setText(menuItem.getTitle());
        return true;
      } else if (itemId == R.id.navigation_nearby) {
        mTextMessage.setText(menuItem.getTitle());
        return true;
      } else if (itemId != R.id.navigation_recent) {
        return false;
      } else {
        mTextMessage.setText(menuItem.getTitle());
        return true;
      }
    });
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      findViewById(R.id.nested_scroll_view).setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
        animateNavigation(scrollY > oldScrollY);
        animateSearchBar(scrollY > oldScrollY);
      });
    }
    findViewById(R.id.bt_menu).setOnClickListener(view -> finish());

    Tools.displayImageOriginal(this, findViewById(R.id.image_1), R.drawable.image_8);
    Tools.displayImageOriginal(this, findViewById(R.id.image_2), R.drawable.image_9);
    Tools.displayImageOriginal(this, findViewById(R.id.image_3), R.drawable.image_15);
    Tools.displayImageOriginal(this, findViewById(R.id.image_4), R.drawable.image_14);
    Tools.displayImageOriginal(this, findViewById(R.id.image_5), R.drawable.image_12);
    Tools.displayImageOriginal(this, findViewById(R.id.image_6), R.drawable.image_2);
    Tools.displayImageOriginal(this, findViewById(R.id.image_7), R.drawable.image_5);
    Tools.setSystemBarColor(this, R.color.grey_5);
    Tools.setSystemBarLight(this);
  }

  private void animateNavigation (boolean z) {
    if (!(isNavigationHide && z) && (isNavigationHide || z)) {
      isNavigationHide = z;
      navigation.animate()
          .translationY((float) (z ? navigation.getHeight() * 2 : 0))
          .setStartDelay(100)
          .setDuration(300)
          .start();
    }
  }

  private void animateSearchBar (boolean z) {
    if (!(isSearchBarHide && z) && (isSearchBarHide || z)) {
      isSearchBarHide = z;
      searchBar.animate()
          .translationY((float) (z ? -(searchBar.getHeight() * 2) : 0))
          .setStartDelay(100)
          .setDuration(300)
          .start();
    }
  }
}
