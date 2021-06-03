package com.xegami.mac.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import com.xegami.mac.R
import com.xegami.mac.rest.dto.CharacterDTO
import kotlinx.android.synthetic.main.activity_character_details.*

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