package com.example.tugasapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasapi.Network.ApiClient
import com.example.tugasapi.databinding.ActivityMainBinding
import com.example.tugasapi.Models.CharacterData
import com.example.tugasapi.Models.CharacterModels
import retrofit2.Call
import retrofit2.Response
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getCharacter()

        val characterList = ArrayList<CharacterData>()

        response.enqueue(object: retrofit2.Callback<CharacterModels> {
            override fun onResponse(
                call: Call<CharacterModels>,
                response: Response<CharacterModels>
            ) {
                val thisResponse = response.body()
                val datas = thisResponse?.results ?: emptyList()

                if(datas.isNotEmpty()) {
                    for(i in datas){
                        val characterData = CharacterData(i.id ,i.name, i.species, i.image)
                        characterList.add(characterData)
                    }
                    with(binding) {
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        recyclerView.setHasFixedSize(true)

                        val adapter = CharacterAdapter(characterList)
                        recyclerView.adapter = adapter
                        adapter.setOnItemClickListener(object : CharacterAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                Toast.makeText(this@MainActivity,"You Clicked ${characterList[position].name}", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                                intent.putExtra("EXTID", characterList[position].id)
                                startActivity(intent)
                                finish()
                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<CharacterModels>, t: Throwable) {
                Toast.makeText(this@MainActivity, "network error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}