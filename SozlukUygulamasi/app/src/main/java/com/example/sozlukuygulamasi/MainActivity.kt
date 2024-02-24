package com.example.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sozlukuygulamasi.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener{
    private lateinit var ulas : ActivityMainBinding
    private lateinit var kelimelerListe : ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt : VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        VeriTabaniKopyala()

        ulas.toolbar.title = "Sözlük Uygulaması"
        setSupportActionBar(ulas.toolbar)

        ulas.rv.setHasFixedSize(true)
        ulas.rv.layoutManager = LinearLayoutManager(this)

        vt = VeriTabaniYardimcisi(this)

        kelimelerListe = Kelimelerdao().TumKelimeler(vt)


        adapter = KelimelerAdapter(this,kelimelerListe)

        ulas.rv.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu?.findItem(R.id.action_ara)
        val nesneArama = item?.actionView as SearchView
        nesneArama.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null)
        {
            arama(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null)
        {
            arama(newText)
        }
        return true
    }

    fun VeriTabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

    fun arama(arananKelime:String){

        kelimelerListe = Kelimelerdao().AramaYap(vt,arananKelime)

        adapter = KelimelerAdapter(this,kelimelerListe)

        ulas.rv.adapter = adapter

    }
    

}