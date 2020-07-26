package com.xegami.excibit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.xegami.excibit.R
import com.xegami.excibit.adapter.CharacterRecyclerViewAdapter
import com.xegami.excibit.listener.CharacterClickListener
import com.xegami.excibit.rest.controller.CharacterController
import com.xegami.excibit.rest.dto.CharacterDTO
import com.xegami.excibit.rest.event.ErrorEvent
import com.xegami.excibit.rest.event.GetCharacterByIdEvent
import com.xegami.excibit.rest.event.GetCharactersEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), CharacterClickListener {

    private lateinit var adapter: CharacterRecyclerViewAdapter
    private lateinit var characters: List<CharacterDTO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CharacterController.instance.getCharacters(100)

        sv_activity_main_filter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                adapter.filterByName(p0!!)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filterByName(p0!!)
                return true
            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: GetCharactersEvent) {
        characters = event.characters
        adapter = CharacterRecyclerViewAdapter(this, characters.toMutableList(), cListener = this)

        rv_activity_main_characters.adapter = adapter
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: ErrorEvent) {
        Snackbar.make(findViewById(android.R.id.content), event.message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)

        super.onStop()
    }

    override fun onClick(characterDTO: CharacterDTO) {
        CharacterController.instance.getCharacterById(characterDTO.id)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: GetCharacterByIdEvent) {
        startActivity(Intent(this, CharacterDetailsActivity::class.java).putExtra("character", event.character))
    }

}