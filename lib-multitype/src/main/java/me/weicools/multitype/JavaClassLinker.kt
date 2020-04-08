package me.weicools.multitype

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote An interface to link the items and delegates by the classes of delegates.
 */
interface JavaClassLinker<T> {
  /**
   * Returns the class of your registered delegates for your item.
   * @param position The position in items
   * @param item The item
   * @return The index of your registered delegates
   * @see OneToManyEndpoint.withJavaClassLinker
   */
  fun index(position: Int, item: T): Class<out ItemViewDelegate<T, *>>
}