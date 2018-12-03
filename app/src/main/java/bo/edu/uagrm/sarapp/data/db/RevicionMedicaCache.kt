package bo.edu.uagrm.sarapp.data.db

import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import java.util.concurrent.Executor

class RevicionMedicaCache
    (private val revicionMedicaDao:RevicionMedicaDao,
     private val ioExecutor: Executor)
{
    fun insert(revicionMedica: RevicionMedica,insertedFinished:()->Unit){
        ioExecutor.execute{
            revicionMedicaDao.insert(revicionMedica)
            insertedFinished()
        }
    }
}