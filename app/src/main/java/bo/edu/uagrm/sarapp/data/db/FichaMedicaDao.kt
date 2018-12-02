package bo.edu.uagrm.sarapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bo.edu.uagrm.sarapp.data.model.FichaMedica

@Dao
interface FichaMedicaDao {
    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fichaMedica:List<FichaMedica>)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fichaMedica:FichaMedica)

    @Query("SELECT * FROM fichas_medicas")
    fun getFichasMedicas():MutableList<FichaMedica>

    @Query("SELECT * FROM fichas_medicas where `key`=:key")
    fun getFichaMedica(key:String):LiveData<FichaMedica>
}