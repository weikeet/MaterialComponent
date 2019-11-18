package me.weicools.material.component.module

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.math.MathUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_main_design.*
import me.weicools.material.component.R

/**
 * @author Weicools Create on 2019.03.29
 *
 * desc:
 */
class MainDesignFragment : Fragment() {
  companion object {
    private const val GRID_SPAN_COUNT_MIN = 1
    private const val GRID_SPAN_COUNT_MAX = 4

    fun newInstance(): MainDesignFragment {

      return MainDesignFragment()
    }
  }

  private lateinit var activity: Activity

  override fun onAttach(context: Context) {
    super.onAttach(context)
    activity = context as Activity

    initFeatureDataList()
  }

  private fun initFeatureDataList() {
    FeatureDataManager.run {
      instance.addFeatureData(object : FeatureData(titleResId = R.string.mal_title_about, drawableResId = R.drawable.ic_accessibility) {
        override fun startActivity(activity: Activity) {

        }
      })
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main_design, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      addGridTopDividerVisibilityListener()
    } else {
      view_divider.visibility = View.VISIBLE
    }

    val gridSpanCount = calculateGridSpanCount()
    recyclerView.layoutManager = GridLayoutManager(activity, gridSpanCount)
//    recyclerView.addItemDecoration(
//        GridDividerDecoration(
//            resources.getDimensionPixelSize(R.dimen.cat_toc_grid_divider_size),
//            ContextCompat.getColor(context, R.color.cat_toc_grid_divider_color),
//            gridSpanCount))

    recyclerView.adapter = FeatureAdapter(activity, FeatureDataManager.instance.featureDataList)
  }

  private fun addGridTopDividerVisibilityListener() {
//    app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener(){
//
//    })
//
//    app_bar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//      if (Math.abs(verticalOffset) == app_bar_layout.getTotalScrollRange()) {
//        // CTL is collapsed, hide top divider
//        view_divider.setVisibility(View.GONE)
//      } else {
//        // CTL is expanded or expanding, show top divider
//        view_divider.setVisibility(View.VISIBLE)
//      }
//    }
  }

  private fun calculateGridSpanCount(): Int {
    val displayMetrics = resources.displayMetrics
    val displayWidth = displayMetrics.widthPixels
    val itemSize = resources.getDimensionPixelSize(R.dimen.toc_item_size)
    val gridSpanCount = displayWidth / itemSize
    return MathUtils.clamp(gridSpanCount, GRID_SPAN_COUNT_MIN, GRID_SPAN_COUNT_MAX)
  }
}
