package com.xegami.excibit.rest.dto

import java.io.Serializable

data class ImageDTO(
    val path: String,
    val extension: String
) : Serializable