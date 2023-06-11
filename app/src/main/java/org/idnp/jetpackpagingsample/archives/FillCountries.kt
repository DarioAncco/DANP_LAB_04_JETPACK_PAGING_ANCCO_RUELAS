package org.idnp.jetpackpagingsample.archives

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.model.AppDatabase
import java.io.BufferedReader
import java.io.InputStreamReader

class FillCountries {
    fun llenarBaseDeDatos(context: Context) {
        val archivo = context.assets.open("countries.txt")
        val bufferedReader = BufferedReader(InputStreamReader(archivo))

        val countryList = mutableListOf<Country>()

        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                val countryId: Int = 0
                val name_en: String = " "
                val name_es: String = " "
                val continent_en: String = " "
                val continent_es: String = " "
                val capital_en: String = " "
                val capital_es: String = " "
                val dial_code: String = " "
                val ode_2: String = " "
                val code_3:  String = " "
                val tld: String = " "
                val km2: Int = 1

                val campos = line.split(",")
                val country = Country(countryId,name_en,name_es,continent_en,continent_es,capital_en,capital_es,dial_code,ode_2,code_3,tld,km2)

                countryList.add(country)
            }
        }
        val dataBase = Room.databaseBuilder(context, AppDatabase::class.java, "country").build()
        val countryDao = dataBase.countryDao()

        GlobalScope.launch(Dispatchers.IO) {
            countryList.forEach { country ->
                countryDao.insertCountry(country)
            }
        }
    }
}