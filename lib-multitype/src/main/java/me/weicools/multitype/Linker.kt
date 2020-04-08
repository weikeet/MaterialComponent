package me.weicools.multitype

import androidx.annotation.IntRange

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote An interface to link the items and delegates by the array index.
 */
interface Linker<T> {
  /**
   * Returns the index of your registered delegates for your item.
   * The result should be in range of `[0, one-to-multiple-delegates.length)`.
   */
  @IntRange(from = 0)
  fun index(position: Int, item: T): Int
}