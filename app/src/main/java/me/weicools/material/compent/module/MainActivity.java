package me.weicools.material.compent.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import me.weicools.material.compent.R;
import me.weicools.material.compent.config.RouterConfig;
import me.weicools.material.compent.config.RouterData;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
public class MainActivity extends AppCompatActivity {
  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    List<RouterData> dataList = new RouterConfig().get();
    MainItemAdapter adapter = new MainItemAdapter(this, dataList);
    recyclerView.setAdapter(adapter);
  }
}
