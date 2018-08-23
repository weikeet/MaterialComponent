package me.weicools.material.compent.module.gridlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import me.weicools.material.compent.R;
import me.weicools.material.compent.config.RouterPath;

/**
 * @author Weicools create on 2018.08.23
 *
 * desc:
 */
@Route(path = RouterPath.MODULE_GRID_ALBUMS)
public class GridAlbumsActivity extends AppCompatActivity {

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_grid_albums);
  }
}
