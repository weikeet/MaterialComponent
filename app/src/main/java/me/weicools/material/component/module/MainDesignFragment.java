package me.weicools.material.component.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.weicools.material.component.R;

/**
 * @author Weicools Create on 2019.03.29
 *
 * desc:
 */
public class MainDesignFragment extends Fragment {
  public static MainDesignFragment newInstance () {
    return new MainDesignFragment();
  }

  @Nullable
  @Override
  public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_design, container, false);
    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
