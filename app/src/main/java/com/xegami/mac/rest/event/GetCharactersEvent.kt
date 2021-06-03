package com.xegami.mac.rest.event

import com.xegami.mac.rest.dto.CharacterDTO

data class GetCharactersEvent(val characters: List<CharacterDTO>) : BaseEvent()