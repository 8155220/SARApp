package bo.edu.uagrm.sarapp.data.db

import android.util.Log
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import java.util.concurrent.Executor

class FichaMedicaCache
    (private val fichaMedicaDao: FichaMedicaDao,
     private val ioExecutor: Executor)
{
    fun insert(fichaMedica: FichaMedica,insertedFinished:()->Unit ){
        ioExecutor.execute{
            fichaMedicaDao.insert(fichaMedica)
            Log.d("FichaCache","Insertando :"+fichaMedica)
            Log.d("FichaCacheLista",fichaMedicaDao.getFichasMedicas().toString())
            insertedFinished()
        }

    }
}