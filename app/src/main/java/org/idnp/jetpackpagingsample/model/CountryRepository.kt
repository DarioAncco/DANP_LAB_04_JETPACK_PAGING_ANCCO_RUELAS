package org.idnp.jetpackpagingsample.model

import org.idnp.jetpackpagingsample.entities.Country

class CountryRepository(private val appDatabase: AppDatabase) {
    suspend fun getCountries(starRow:Int,numRows:Int):List<Country>{
        val endRow = starRow + numRows
        return appDatabase.countryDao().getCountries(starRow,endRow)
    }
}
