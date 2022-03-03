package me.weicools.material.component.main

/**
 * @author Weicools Create on 2019.08.10
 */
class FeatureDataManager private constructor() {

  internal object FeatureDataHolder {
    val INSTANCE = FeatureDataManager()
  }

  companion object {
    val instance: FeatureDataManager
      get() = FeatureDataHolder.INSTANCE
  }

  private val mFeatureDataList = ArrayList<FeatureData>()

  val featureDataList: ArrayList<FeatureData>
    get() = mFeatureDataList

  fun addFeatureData(featureData: FeatureData) {
    mFeatureDataList.add(featureData)
  }
}
