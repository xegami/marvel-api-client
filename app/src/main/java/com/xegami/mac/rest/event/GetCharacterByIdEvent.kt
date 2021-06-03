package com.xegami.mac.rest.event

import com.xegami.mac.rest.dto.CharacterDTO
import java.io.Serializable

data class GetCharacterByIdEvent(val character: CharacterDTO) : BaseEvent(), Serializable