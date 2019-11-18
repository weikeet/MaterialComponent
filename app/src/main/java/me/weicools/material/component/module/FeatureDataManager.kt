package me.weicools.material.component.module

/**
 * @author Weicools Create on 2019.08.10
 *
 * desc:
 */
class FeatureDataManager private constructor() {

  private val mFeatureDataList = ArrayList<FeatureData>()

  val featureDataList: ArrayList<FeatureData>
    get() = mFeatureDataList

  internal object FeatureDataHolder {
    val INSTANCE = FeatureDataManager()
  }

  fun addFeatureData(featureData: FeatureData) {
    mFeatureDataList.add(featureData)
  }

  companion object {
    val instance: FeatureDataManager
      get() = FeatureDataHolder.INSTANCE
  }
}
