package com.xegami.excibit.rest.dto

import java.io.Serializable

data class ComicListDTO(
    val items: List<ComicSummaryDTO>
) : Serializable