package com.xegami.excibit.rest.event

import org.greenrobot.eventbus.EventBus

open class BaseEvent {

    fun post() {
        EventBus.getDefault().post(this)
    }
}