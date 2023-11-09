package com.example.tugasapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tugasapi.Models.CharacterData
import com.example.tugasapi.Models.CharacterModels
import com.example.tugasapi.Network.ApiClient
import com.example.tugasapi.databinding.ActivityDetailsBinding
import retrofit2.Call
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val id = bundle!!.getInt("EXTID")

        val client = ApiClient.getInstance()
        val response = client.getCharacterById(id)

        response.enqueue(object : retrofit2.Callback<CharacterData>{
            override fun onResponse(
                call: Call<CharacterData>,
                response: Response<CharacterData>
            ) {
                val thisResponse = response.body()

                println(thisResponse)

                val name =  thisResponse?.name
                val species = thisResponse?.species
                val image = thisResponse?.image

                println(name)
                println(species)
                println(image)

                binding.tvNamaKarakter.text = name
                binding.tvSpeciesKarakter.text = species
                Glide.with(binding.ivImage)
                    .load(image)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.ivImage)

                binding.btnBack.setOnClickListener(){
                    startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
                    finish()
                }

            }

            override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, "network error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}