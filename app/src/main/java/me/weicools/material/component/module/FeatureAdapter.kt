package me.weicools.material.component.module

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main_design.*
import kotlinx.android.synthetic.main.main_item_feature.view.*
import me.weicools.material.component.R
import me.weicools.material.component.module.FeatureData.Companion.STATUS_WIP

/**
 * @author Weicools Create on 2019.08.10
 *
 * desc:
 */
class FeatureAdapter(private val activity: Activity, private val featureList: ArrayList<FeatureData>) : RecyclerView.Adapter<FeatureAdapter.FeatureHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureHolder {
    return FeatureHolder(LayoutInflater.from(activity).inflate(R.layout.main_item_feature, parent, false))
  }

  override fun onBindViewHolder(holder: FeatureHolder, position: Int) {
    holder.bindView(activity, featureList[position])
  }

  override fun getItemCount(): Int {
    return featureList.size
  }

  class FeatureHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(activity: Activity, featureData: FeatureData) {
      itemView.run {
        feature_title.setText(featureData.titleResId)
        feature_image.setImageResource(featureData.drawableResId)
        setOnClickListener { featureData.startActivity(activity) }
      }
      itemView.feature_status_wip_label.visibility = if (featureData.status == STATUS_WIP) View.VISIBLE else View.GONE
    }
  }
}
