package com.xegami.excibit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.xegami.excibit.R
import com.xegami.excibit.adapter.CharacterRecyclerViewAdapter
import com.xegami.excibit.listener.CharacterClickListener
import com.xegami.excibit.rest.controller.CharacterController
import com.xegami.excibit.rest.dto.CharacterDTO
import com.xegami.excibit.rest.event.ErrorEvent
import com.xegami.excibit.rest.event.GetCharactersEvent
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var character: CharacterDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        character = intent.getSerializableExtra("character") as CharacterDTO

        tv_activity_character_details_name.text = character.name
        tv_activity_character_details_description.text = if (character.description.isEmpty()) resources.getString(R.string.no_description) else character.description

        Picasso.with(this).load("${character.thumbnail.path}/landscape_xlarge.${character.thumbnail.extension}").into(iv_activity_character_details_thumbnail)

        val comics = mutableListOf<String>()
        character.comics.items.forEach { comic ->
            comics.add(comic.name)
        }

        lv_activity_character_details_comics.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, comics)
        tv_activity_character_details_resource.text = character.urls[0].url
    }
}