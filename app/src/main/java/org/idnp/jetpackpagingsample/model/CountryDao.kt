package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.entities.User

@Dao
interface CountryDao {
    @Query("SELECT * FROM country where countryId >= :startRow and countryId < :endRow")
    suspend fun getCountries(startRow:Int, endRow:Int): List<Country>

    @Insert
    suspend fun insertCountry(country: Country)
}
