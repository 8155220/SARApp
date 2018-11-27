package bo.edu.uagrm.sarapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bo.edu.uagrm.sarapp.data.model.Compania

@Dao
interface CompaniasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(companias:List<Compania>)

    @Query("SELECT * FROM companias WHERE `key`= :key")
    fun getCompanias(key:String): LiveData<Compania>
}