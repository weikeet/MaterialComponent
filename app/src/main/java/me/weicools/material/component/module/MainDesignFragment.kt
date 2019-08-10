package me.weicools.material.component.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.weicools.material.component.R

/**
 * @author Weicools Create on 2019.03.29
 *
 * desc:
 */
class MainDesignFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main_design, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  companion object {
    fun newInstance(): MainDesignFragment {
      return MainDesignFragment()
    }
  }
}
