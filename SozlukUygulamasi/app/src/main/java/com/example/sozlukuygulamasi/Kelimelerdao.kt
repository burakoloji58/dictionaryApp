package com.example.sozlukuygulamasi

class Kelimelerdao {

    fun TumKelimeler(vt:VeriTabaniYardimcisi) : ArrayList<Kelimeler>{

        val kelimelerListe = ArrayList<Kelimeler>()

        val db = vt.writableDatabase

        val c = db.rawQuery("SELECT * FROM kelimeler",null)

        while (c.moveToNext())
        {
            val kelime = Kelimeler(c.getInt(c.getColumnIndex("kelime_id")),
                c.getString(c.getColumnIndex("ingilizce")),
                c.getString(c.getColumnIndex("turkce")))
            kelimelerListe.add(kelime)
        }
        return kelimelerListe
    }

    fun AramaYap(vt:VeriTabaniYardimcisi,arananKelime:String) : ArrayList<Kelimeler>{

        val kelimelerListe = ArrayList<Kelimeler>()

        val db = vt.writableDatabase

        val c = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE '%$arananKelime%'",null)

        while (c.moveToNext())
        {
            val kelime = Kelimeler(c.getInt(c.getColumnIndex("kelime_id")),
                c.getString(c.getColumnIndex("ingilizce")),
                c.getString(c.getColumnIndex("turkce")))
            kelimelerListe.add(kelime)
        }
        return kelimelerListe
    }

}