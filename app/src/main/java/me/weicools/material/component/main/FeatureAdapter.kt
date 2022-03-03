package me.weicools.material.component.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.weicools.material.component.R
import me.weicools.material.component.main.FeatureData.Companion.STATUS_WIP

/**
 * @author Weicools Create on 2019.08.10
 */
class FeatureAdapter(
  private val activity: Activity,
  private val featureList: ArrayList<FeatureData>
) : RecyclerView.Adapter<FeatureAdapter.FeatureHolder>() {

  class FeatureHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val featureTitle: TextView = itemView.findViewById(R.id.feature_title)
    private val featureImage: ImageView = itemView.findViewById(R.id.feature_image)
    private val featureStatusWipLabel: View = itemView.findViewById(R.id.feature_status_wip_label)

    fun bindView(activity: Activity, featureData: FeatureData) {
      itemView.run {
        featureTitle.setText(featureData.titleResId)
        featureImage.setImageResource(featureData.drawableResId)
        setOnClickListener { featureData.startActivity(activity) }
      }
      featureStatusWipLabel.visibility = if (featureData.status == STATUS_WIP) View.VISIBLE else View.GONE
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureHolder {
    return FeatureHolder(LayoutInflater.from(activity).inflate(R.layout.main_item_feature, parent, false))
  }

  override fun onBindViewHolder(holder: FeatureHolder, position: Int) {
    holder.bindView(activity, featureList[position])
  }

  override fun getItemCount(): Int {
    return featureList.size
  }
}
