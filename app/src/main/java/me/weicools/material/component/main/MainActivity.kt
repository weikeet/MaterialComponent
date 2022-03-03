package me.weicools.material.component.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.math.MathUtils
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.weiwei.view.binding.viewBinding
import me.weicools.material.component.R
import me.weicools.material.component.databinding.ActivityMainBinding
import kotlin.math.abs

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    addGridTopDividerVisibilityListener()

    val gridSpanCount = calculateGridSpanCount()
    binding.recyclerView.layoutManager = GridLayoutManager(this, gridSpanCount)
    binding.recyclerView.addItemDecoration(
      GridDividerDecoration(
        resources.getDimensionPixelSize(R.dimen.cat_toc_grid_divider_size),
        ContextCompat.getColor(this, R.color.cat_toc_grid_divider_color),
        gridSpanCount
      )
    )

    binding.recyclerView.adapter = FeatureAdapter(this, FeatureDataManager.instance.featureDataList)
  }

  private fun addGridTopDividerVisibilityListener() {
    binding.appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { _, verticalOffset ->
      if (abs(verticalOffset) == binding.appBarLayout.totalScrollRange) {
        // CTL is collapsed, hide top divider
        binding.viewDivider.visibility = View.GONE
      } else {
        // CTL is expanded or expanding, show top divider
        binding.viewDivider.visibility = View.VISIBLE
      }
    })
  }

  private fun calculateGridSpanCount(): Int {
    val displayMetrics = resources.displayMetrics
    val displayWidth = displayMetrics.widthPixels
    val itemSize = resources.getDimensionPixelSize(R.dimen.cat_toc_item_size)
    val gridSpanCount = displayWidth / itemSize
    return MathUtils.clamp(gridSpanCount, GRID_SPAN_COUNT_MIN, GRID_SPAN_COUNT_MAX)
  }

  companion object {
    private const val GRID_SPAN_COUNT_MIN = 1
    private const val GRID_SPAN_COUNT_MAX = 4
  }
}
