package com.xegami.excibit.rest.event

import com.xegami.excibit.rest.dto.CharacterDTO

data class GetCharactersEvent(val characters: List<CharacterDTO>) : BaseEvent()