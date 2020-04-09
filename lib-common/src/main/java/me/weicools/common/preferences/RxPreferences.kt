package me.weicools.common.preferences

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Single

/**
 * @author weicools
 * @date 2020.04.08
 */
class RxPreferences(private val preferences: SharedPreferences) {
  companion object {
    fun create(context: Context, spFileName: String): RxPreferences = RxPreferences(context, spFileName)
  }

  constructor(context: Context, spFileName: String) : this(context.getSharedPreferences(spFileName, Context.MODE_PRIVATE))

  fun getAllOnce(): Single<Map<String, *>> {
    return Single.fromCallable(preferences::getAll)
  }

  //<editor-fold desc="Get OnceValue">
  fun getStringOnce(key: String, defaultValue: String): Single<String> {
    return Single.fromCallable { preferences.getString(key, defaultValue) }
  }

  fun getStringSetOnce(key: String, defaultValue: Set<String>): Single<Set<String>> {
    return Single.fromCallable { preferences.getStringSet(key, defaultValue) }
  }

  fun getIntOnce(key: String, defaultValue: Int): Single<Int> {
    return Single.fromCallable { preferences.getInt(key, defaultValue) }
  }

  fun getLongOnce(key: String, defaultValue: Long): Single<Long> {
    return Single.fromCallable { preferences.getLong(key, defaultValue) }
  }

  fun getFloatOnce(key: String, defaultValue: Float): Single<Float> {
    return Single.fromCallable { preferences.getFloat(key, defaultValue) }
  }

  fun getBooleanOnce(key: String, defaultValue: Boolean): Single<Boolean> {
    return Single.fromCallable { preferences.getBoolean(key, defaultValue) }
  }

  fun containsOnce(key: String): Single<Boolean> {
    return Single.fromCallable { preferences.contains(key) }
  }
  //</editor-fold>

  //<editor-fold desc="Get StreamValue">
  fun getAllStream(): Observable<Map<String, *>> {
    return getAllOnce()
      .toObservable()
      .mergeWith(Observable.create { emitter ->
        registerRxPreferenceListener(RxAllPreferencesListener(emitter), emitter)
      })
  }

  fun getStringStream(key: String, defaultValue: String): Observable<String> {
    return getStringOnce(key, defaultValue)
      .toObservable()
      .mergeWith(createPreferenceObservable(key, defaultValue, object : GetPreferences<String> {
        override fun invoke(preferences: SharedPreferences, key: String, value: String): String {
          return preferences.getString(key, defaultValue) ?: ""
        }
      }))
  }

  fun getStringSetStream(key: String, defaultValue: Set<String>): Observable<Set<String>> {
    return getStringSetOnce(key, defaultValue)
      .toObservable()
      .mergeWith(createPreferenceObservable(key, defaultValue, object : GetPreferences<Set<String>> {
        override fun invoke(preferences: SharedPreferences, key: String, value: Set<String>): Set<String> {
          return preferences.getStringSet(key, defaultValue) ?: HashSet()
        }
      }))
  }

  fun getIntStream(key: String, defaultValue: Int): Observable<Int> {
    return getIntOnce(key, defaultValue)
      .toObservable()
      .mergeWith(createPreferenceObservable(key, defaultValue, object : GetPreferences<Int> {
        override fun invoke(preferences: SharedPreferences, key: String, value: Int): Int {
          return preferences.getInt(key, defaultValue)
        }
      }))
  }

  fun getLongStream(key: String, defaultValue: Long): Observable<Long> {
    return getLongOnce(key, defaultValue)
      .toObservable()
      .mergeWith(createPreferenceObservable(key, defaultValue, object : GetPreferences<Long> {
        override fun invoke(preferences: SharedPreferences, key: String, value: Long): Long {
          return preferences.getLong(key, defaultValue)
        }
      }))
  }

  fun getFloatStream(key: String, defaultValue: Float): Observable<Float> {
    return getFloatOnce(key, defaultValue)
      .toObservable()
      .mergeWith(createPreferenceObservable(key, defaultValue, object : GetPreferences<Float> {
        override fun invoke(preferences: SharedPreferences, key: String, value: Float): Float {
          return preferences.getFloat(key, defaultValue)
        }
      }))
  }

  fun getBooleanStream(key: String, defaultValue: Boolean): Observable<Boolean> {
    return getBooleanOnce(key, defaultValue)
      .toObservable()
      .mergeWith(createPreferenceObservable(key, defaultValue, object : GetPreferences<Boolean> {
        override fun invoke(preferences: SharedPreferences, key: String, value: Boolean): Boolean {
          return preferences.getBoolean(key, defaultValue)
        }
      }))
  }

  fun containsStream(key: String): Observable<Boolean> {
    return containsOnce(key)
      .toObservable()
      .mergeWith(Observable.create { emitter ->
        registerRxPreferenceListener(RxPreferenceContainsListener(key, emitter), emitter)
      })
  }

  private fun <T> createPreferenceObservable(key: String, defaultValue: T, getPreferences: GetPreferences<T>): Observable<T> {
    return Observable.create { emitter ->
      registerRxPreferenceListener(RxPreferenceChangeListener(key, defaultValue, getPreferences, emitter), emitter)
    }
  }
  //</editor-fold>

  private fun <T> registerRxPreferenceListener(
    listener: RxPreferenceListener<T>, emitter: ObservableEmitter<T>
  ) {
    emitter.setCancellable { preferences.unregisterOnSharedPreferenceChangeListener(listener) }
    preferences.registerOnSharedPreferenceChangeListener(listener)
  }

  fun edit(): Editor {
    return Editor(preferences.edit())
  }

  class Editor(private val preferencesEditor: SharedPreferences.Editor) {
    fun putString(key: String?, value: String): Editor {
      preferencesEditor.putString(key, value)
      return this
    }

    fun putStringSet(key: String, values: Set<String>): Editor {
      preferencesEditor.putStringSet(key, values)
      return this
    }

    fun putInt(key: String, value: Int): Editor {
      preferencesEditor.putInt(key, value)
      return this
    }

    fun putLong(key: String, value: Long): Editor {
      preferencesEditor.putLong(key, value)
      return this
    }

    fun putFloat(key: String, value: Float): Editor {
      preferencesEditor.putFloat(key, value)
      return this
    }

    fun putBoolean(key: String, value: Boolean): Editor {
      preferencesEditor.putBoolean(key, value)
      return this
    }

    fun remove(key: String): Editor {
      preferencesEditor.remove(key)
      return this
    }

    fun clear(): Editor {
      preferencesEditor.clear()
      return this
    }

    fun commit(): Completable {
      return Completable.fromAction {
        val success = preferencesEditor.commit()
        if (!success) {
          throw CommitException()
        }
      }
    }

    class CommitException : RuntimeException("Failed to commit the desired preference changes")
  }
}