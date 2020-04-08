package me.weicools.multitype

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote
 */
internal class DelegateNotFoundException(clazz: Class<*>) : RuntimeException(
  "Have you registered the ${clazz.name} type and its delegate or binder?"
)