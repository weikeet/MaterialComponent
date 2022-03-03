package com.weiwei.material.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.math.MathUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.weiwei.extensions.widthPixels
import com.weiwei.material.R
import com.weiwei.material.databinding.MaterialFragmentMainBinding
import com.weiwei.view.binding.viewBinding
import kotlin.math.abs

/**
 * @author weiwei
 * @date 2022.03.03
 */
class MaterialMainFragment : Fragment() {

  companion object {
    private const val GRID_SPAN_COUNT_MIN = 1
    private const val GRID_SPAN_COUNT_MAX = 4
  }

  private val binding: MaterialFragmentMainBinding by viewBinding(MaterialFragmentMainBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.material_fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    addGridTopDividerVisibilityListener()

    val gridSpanCount = calculateGridSpanCount()
    binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), gridSpanCount)
    binding.recyclerView.addItemDecoration(
      GridDividerDecoration(
        resources.getDimensionPixelSize(R.dimen.cat_toc_grid_divider_size),
        ContextCompat.getColor(requireContext(), R.color.cat_toc_grid_divider_color),
        gridSpanCount
      )
    )

    binding.recyclerView.adapter = FeatureAdapter(requireActivity(), FeatureDataManager.instance.featureDataList)
  }

  private fun addGridTopDividerVisibilityListener() {
    binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
      binding.viewDivider.isVisible = abs(verticalOffset) != binding.appBarLayout.totalScrollRange
    })
  }

  private fun calculateGridSpanCount(): Int {
    val gridSpanCount = widthPixels / resources.getDimensionPixelSize(R.dimen.cat_toc_item_size)
    return MathUtils.clamp(gridSpanCount, GRID_SPAN_COUNT_MIN, GRID_SPAN_COUNT_MAX)
  }

}