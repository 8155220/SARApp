package bo.edu.uagrm.sarapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bo.edu.uagrm.sarapp.data.model.Persona

@Database(entities = [Persona::class],
    version = 7,
    exportSchema = false)
abstract class SARDatabase : RoomDatabase(){
    abstract fun personasDao():PersonaDao

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