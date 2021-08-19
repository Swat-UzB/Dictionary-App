package com.example.dictionaryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.dictionaryapp.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private val KEY = "SHORT_DEF"
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

            val stringRequest = StringRequest(
                Request.Method.GET, getUrl(),
                { response ->
                    try {
                        extractDefinitionFromJson(response)
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }

                },
                { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT)
                }
            )
            queue.add(stringRequest)
        }
    }

    private fun getUrl(): String {
        val word = binding.searchEditText.text.toString()
        return String.format("%s%s?key=%s", DICTIONARY_WEBSITE, word, API_KEY)
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val getFirstShortDef = getShortDefinition.get(0).toString()

        val intent = Intent(this, WordDefinitionActivity::class.java).apply {
            putExtra(KEY, getFirstShortDef)
        }
        startActivity(intent)
    }
}