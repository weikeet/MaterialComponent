package me.weicools.material.component.module;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Route;
import java.util.List;
import me.weicools.material.component.R;
import me.weicools.material.component.config.RouterConfig;
import me.weicools.material.component.config.RouterData;
import me.weicools.material.component.config.RouterPath;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
@Route(path = RouterPath.MODULE_MAIN)
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
