package me.weicools.common.preferences

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * @author weicools
 * @date 2020.04.08
 */

inline fun <reified E : Enum<E>> RxPreferences.getEnumOnce(key: String, defaultValue: E): Single<E> =
  getStringOnce(key, defaultValue.name).map { name -> enumValueOf<E>(name) }

inline fun <reified E : Enum<E>> RxPreferences.getEnumStream(key: String, defaultValue: E): Observable<E> =
  getStringStream(key, defaultValue.name).map { name -> enumValueOf<E>(name) }

inline fun <reified E : Enum<E>> RxPreferences.getEnumByOrdinalOnce(key: String, defaultValue: E): Single<E> =
  getIntOnce(key, defaultValue.ordinal).map { ordinal -> enumValues<E>()[ordinal] }

inline fun <reified E : Enum<E>> RxPreferences.getEnumByOrdinalStream(key: String, defaultValue: E): Observable<E> =
  getIntStream(key, defaultValue.ordinal).map { ordinal -> enumValues<E>()[ordinal] }

inline fun RxPreferences.edit(edits: RxPreferences.Editor.() -> RxPreferences.Editor): Completable =
  edits.invoke(this.edit()).commit()

inline fun <reified E : Enum<E>> RxPreferences.Editor.putEnum(key: String, value: E): RxPreferences.Editor =
  putString(key, value.name)

inline fun <reified E : Enum<E>> RxPreferences.Editor.putEnumByOrdinal(key: String, value: E): RxPreferences.Editor =
  putInt(key, value.ordinal)