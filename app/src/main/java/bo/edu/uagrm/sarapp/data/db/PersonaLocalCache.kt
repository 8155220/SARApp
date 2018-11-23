package bo.edu.uagrm.sarapp.data.db

import android.util.Log
import androidx.paging.DataSource
import bo.edu.uagrm.sarapp.data.model.Persona
import java.util.concurrent.Executor

class PersonaLocalCache(private val personaDao: PersonaDao,
                        private val ioExecutor: Executor){
    private val TAG = PersonaLocalCache::class.java.simpleName
    /**
     *  Inserta una lista de personas en la db, en un background thread.
     */
    fun insert(personas:List<Persona>,insertFinished: ()->Unit)
    {
        Log.d("PersonaLocalCache",personas.toString())
        ioExecutor.execute{
            Log.d(TAG,"inserting ${personas.size} personas")
            personaDao.insert(personas)
            insertFinished()
        }
    }

    fun personasByName(name:String):DataSource.Factory<Integer,Persona>{
        val query = "%${name.replace(' ','%')}%"
        return personaDao.personasByName(query)
    }
}