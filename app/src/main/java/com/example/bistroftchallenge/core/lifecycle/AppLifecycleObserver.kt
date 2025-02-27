package com.example.bistroftchallenge.core.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.bistroftchallenge.domain.model.LifecycleEvent
import com.example.bistroftchallenge.domain.repository.LifecycleEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class AppLifecycleObserver @Inject constructor(
    private val repository: LifecycleEventRepository
) : DefaultLifecycleObserver {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private fun logEvent(eventName: String, owner: LifecycleOwner) {
        val event = LifecycleEvent(
            event = eventName,
            timestamp = System.currentTimeMillis(),
            activityName = owner.javaClass.simpleName
        )
        coroutineScope.launch {
            repository.insertEvent(event)
        }
        Log.d("LifecycleEvent", "Evento guardado: $eventName en ${owner.javaClass.simpleName}")
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        logEvent(Lifecycle.Event.ON_CREATE.name, owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        logEvent(Lifecycle.Event.ON_START.name, owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        logEvent(Lifecycle.Event.ON_RESUME.name, owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        logEvent(Lifecycle.Event.ON_PAUSE.name, owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        logEvent(Lifecycle.Event.ON_STOP.name, owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        logEvent(Lifecycle.Event.ON_DESTROY.name, owner)
    }

}