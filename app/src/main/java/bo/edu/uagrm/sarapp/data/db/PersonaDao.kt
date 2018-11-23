package bo.edu.uagrm.sarapp.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bo.edu.uagrm.sarapp.data.model.Persona

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personas:List<Persona>)


    @Query("SELECT * FROM personas WHERE(name LIKE:queryString) OR (description LIKE :queryString) ORDER BY name DESC")
    fun personasByName(queryString:String): DataSource.Factory<Integer, Persona>
}