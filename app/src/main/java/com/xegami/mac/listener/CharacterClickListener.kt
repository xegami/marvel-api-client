package com.xegami.mac.listener

import com.xegami.mac.rest.dto.CharacterDTO

interface CharacterClickListener {

    fun onClick(characterDTO: CharacterDTO)

}