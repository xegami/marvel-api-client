package com.xegami.mac.rest.dto

import java.io.Serializable

data class CharacterDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ImageDTO,
    val urls: List<UrlDTO>,
    val comics: ComicListDTO
) : Serializable