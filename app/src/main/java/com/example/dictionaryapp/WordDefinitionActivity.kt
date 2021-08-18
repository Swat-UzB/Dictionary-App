package com.example.dictionaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryapp.databinding.ActivityWordDefinitionBinding

class WordDefinitionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWordDefinitionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordDefinitionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}