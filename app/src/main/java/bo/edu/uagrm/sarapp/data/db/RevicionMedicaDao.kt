package bo.edu.uagrm.sarapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bo.edu.uagrm.sarapp.data.model.RevicionMedica

@Dao
interface RevicionMedicaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(revicionMedica:RevicionMedica)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(revicionMedica:List<RevicionMedica>)

    @Query("SELECT * FROM reviciones_medicas WHERE `personaId`= :personaId ORDER BY fecha DESC")
    fun getRevicionesMedicas(personaId:String): LiveData<MutableList<RevicionMedica>>

    @Query("DELETE  FROM reviciones_medicas WHERE personaId= :personaId")
    fun emptyemptyRevicionMedicafromPersona(personaId:String)


}