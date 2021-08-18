package com.example.dictionaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.dictionaryapp.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val DICTIONARY_WEBSITE =
        "https://www.dictionaryapi.com/api/v3/references/learners/json/"
    private val API_KEY = "093936de-1688-499d-8539-0a62b0008298"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val queue = Volley.newRequestQueue(this)
        binding.findButton.setOnClickListener {
            val word = binding.searchEditText.text.toString()
            val url = String.format("%s%s?key=%s", DICTIONARY_WEBSITE, word, API_KEY)
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    response
                },
                Response.ErrorListener { error ->
                    error
                }
            )
            queue.add(stringRequest)
        }
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
    }
}