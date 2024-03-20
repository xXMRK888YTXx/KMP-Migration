@file:Suppress("DEPRECATION")

package com.xxmrk888ytxx.coreandroid

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

inline fun Context.buildNotificationChannel(
    id: String,
    name: String,
    configuration: NotificationChannel.() -> Unit = {}
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT).apply(configuration)

        val notificationManager = getSystemService<NotificationManager>()

        notificationManager?.createNotificationChannel(channel)
    }
}

inline fun Context.buildNotification(
    channelId:String,
    configuration: Notification.Builder.() -> Unit
) : Notification {
    val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Notification.Builder(this,channelId)
    } else {
        Notification.Builder(this)
    }

    return notificationBuilder.apply(configuration).build()
}

fun CoroutineScope.launchAndCancelChildren(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) : Job {
    this.coroutineContext.cancelChildren()

    return launch(context, start, block)
}