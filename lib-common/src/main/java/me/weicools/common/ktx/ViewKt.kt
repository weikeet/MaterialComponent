package me.weicools.common.ktx

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.MessageQueue
import android.util.Log

/**
 * @author Weicools Create on 2020.09.03
 *
 * desc:
 */

@JvmOverloads
fun doOnMainThreadIdle(action: () -> Unit, timeout: Long? = null) {
  val handler = Handler(Looper.getMainLooper())

  val idleHandler = MessageQueue.IdleHandler {
    handler.removeCallbacksAndMessages(null)
    action()
    return@IdleHandler false
  }

  fun setupIdleHandler(queue: MessageQueue) {
    if (timeout != null) {
      handler.postDelayed({
        queue.removeIdleHandler(idleHandler)
        action()
        if (true) {
          Log.d("doOnMainThreadIdle", "${timeout}ms timeout!")
        }
      }, timeout)
    }
    queue.addIdleHandler(idleHandler)
  }

  if (Looper.getMainLooper() == Looper.myLooper()) {
    setupIdleHandler(Looper.myQueue())
    return
  }

  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    setupIdleHandler(Looper.getMainLooper().queue)
  } else {
    handler.post { setupIdleHandler(Looper.myQueue()) }
  }
}