package com.example.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sozlukuygulamasi.databinding.ActivityDetayBinding
import com.example.sozlukuygulamasi.databinding.ActivityMainBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityDetayBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        ulas.textViewDetayTurkce.text=kelime.turkce
        ulas.textViewDetayIngilizce.text=kelime.ingilizce

    }
}