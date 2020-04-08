package me.weicools.multitype

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote Process and flow operators for one-to-many.
 */
interface OneToManyFlow<T> {
  /**
   * Sets some item view delegates to the item type.
   *
   * @param delegates the item view delegates
   * @return end flow operator
   */
  fun to(vararg delegates: ItemViewDelegate<T, *>): OneToManyEndpoint<T>

  fun to(vararg delegates: ItemViewBinder<T, *>): OneToManyEndpoint<T>
}