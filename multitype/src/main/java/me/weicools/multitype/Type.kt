package me.weicools.multitype

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote
 */
data class Type<T>(
  val clazz: Class<out T>,
  val delegate: ItemViewDelegate<T, *>,
  val linker: Linker<T>
)