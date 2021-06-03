package com.xegami.mac.rest.event

import org.greenrobot.eventbus.EventBus

open class BaseEvent {

    fun post() {
        EventBus.getDefault().post(this)
    }
}