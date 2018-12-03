package bo.edu.uagrm.sarapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import bo.edu.uagrm.sarapp.data.model.RevicionMedica

@Dao
interface RevicionMedicaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(revicionMedica:RevicionMedica)

}