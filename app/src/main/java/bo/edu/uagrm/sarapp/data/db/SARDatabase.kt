package bo.edu.uagrm.sarapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import bo.edu.uagrm.sarapp.utils.Converters

@Database(entities = [Persona::class,FichaMedica::class, RevicionMedica::class],
    version = 12,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class SARDatabase : RoomDatabase(){
    abstract fun personasDao():PersonaDao
    abstract fun fichaMedicaDao():FichaMedicaDao
    abstract fun revicionMedicaDao():RevicionMedicaDao
    companion object {
        @Volatile
        private var INSTANCE: SARDatabase?=null

        fun getInstance(context: Context):SARDatabase =
                INSTANCE ?: synchronized(this){
                    INSTANCE ?: buildDatabase(context).also{INSTANCE=it}
                }
        private fun buildDatabase(context:Context) =
                Room.databaseBuilder(context.applicationContext,
                    SARDatabase::class.java,
                    "SARApp.db").build()

    }
}