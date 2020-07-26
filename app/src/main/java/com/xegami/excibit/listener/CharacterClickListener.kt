package com.xegami.excibit.listener

import com.xegami.excibit.rest.dto.CharacterDTO

interface CharacterClickListener {

    fun onClick(characterDTO: CharacterDTO)

}