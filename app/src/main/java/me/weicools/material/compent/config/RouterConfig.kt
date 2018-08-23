package me.weicools.material.compent.config

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
class RouterConfig {
  fun get(): List<RouterData> {
    val dataList = ArrayList<RouterData>()
    dataList.add(RouterData(TYPE_TITLE, RouterName.BOTTOM_NAVIGATION, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_BASIC, RouterPath.MODULE_BOTTOM_NAVIGATION_BASIC))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_DARK, RouterPath.MODULE_BOTTOM_NAVIGATION_DARK))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_ICON, RouterPath.MODULE_BOTTOM_NAVIGATION_ICON))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_LIGHT, RouterPath.MODULE_BOTTOM_NAVIGATION_LIGHT))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_MAP_BLUE, RouterPath.MODULE_BOTTOM_NAVIGATION_MAP_BLUE))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_PRIMARY, RouterPath.MODULE_BOTTOM_NAVIGATION_PRIMARY))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_NAVIGATION_SHIFTING, RouterPath.MODULE_BOTTOM_NAVIGATION_SHIFTING))

    dataList.add(RouterData(TYPE_TITLE, RouterName.BOTTOM_SHEET, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BOTTOM_SHEET_BASIC, RouterPath.MODULE_BOTTOM_SHEET_BASIC))

    dataList.add(RouterData(TYPE_TITLE, RouterName.BUTTON, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.BUTTON_BASIC, RouterPath.MODULE_BUTTON_BASIC))

    dataList.add(RouterData(TYPE_TITLE, RouterName.CARD, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.CARD_BASIC, RouterPath.MODULE_CARD_BASIC))

    dataList.add(RouterData(TYPE_TITLE, RouterName.CHIP, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.CHIP_BASIC, RouterPath.MODULE_CHIP_BASIC))
    dataList.add(RouterData(TYPE_ITEM, RouterName.CHIP_TAG, RouterPath.MODULE_CHIP_TAG))

    dataList.add(RouterData(TYPE_TITLE, RouterName.DASHBOARD, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.DASHBOARD_FLIGHT, RouterPath.MODULE_DASHBOARD_FLIGHT))

    dataList.add(RouterData(TYPE_TITLE, RouterName.DIALOG, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.DIALOG_ADD_POST, RouterPath.MODULE_DIALOG_ADD_POST))

    dataList.add(RouterData(TYPE_TITLE, RouterName.EXPANSION_PANEL, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.EXPANSION_PANEL_BASIC, RouterPath.MODULE_EXPANSION_PANEL_BASIC))

    dataList.add(RouterData(TYPE_TITLE, RouterName.FORM, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.FORM_LOGIN, RouterPath.MODULE_FORM_LOGIN))

    dataList.add(RouterData(TYPE_TITLE, RouterName.GRID, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.GRID_ALBUMS, RouterPath.MODULE_GRID_ALBUMS))

    dataList.add(RouterData(TYPE_TITLE, RouterName.LIST, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.LIST_ANIMATION, RouterPath.MODULE_LIST_ANIMATION))

    dataList.add(RouterData(TYPE_TITLE, RouterName.LOGIN, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.LOGIN_CARD_LIGHT, RouterPath.MODULE_LOGIN_CARD_LIGHT))

    dataList.add(RouterData(TYPE_TITLE, RouterName.SEARCH, ""))
    dataList.add(RouterData(TYPE_ITEM, RouterName.SEARCH_HISTORY_CARD, RouterPath.MODULE_SEARCH_HISTORY_CARD))

    return dataList
  }

  companion object {
    const val TYPE_TITLE = 0
    const val TYPE_ITEM = 1
  }
}

data class RouterData(
    @SerializedName("type") @Expose val type: Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("path") @Expose val path: String
)