package com.xegami.excibit.rest.event

import com.xegami.excibit.rest.dto.CharacterDTO
import java.io.Serializable

data class GetCharacterByIdEvent(val character: CharacterDTO) : BaseEvent(), Serializable