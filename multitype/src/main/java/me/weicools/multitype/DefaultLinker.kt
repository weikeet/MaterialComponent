package me.weicools.multitype

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote
 */
internal class DefaultLinker<T> : Linker<T> {
  override fun index(position: Int, item: T): Int = 0
}