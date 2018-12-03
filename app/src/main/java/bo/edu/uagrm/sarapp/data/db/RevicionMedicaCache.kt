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
    fun insert(revicionMedica: List<RevicionMedica>,insertedFinished:()->Unit){
        ioExecutor.execute{
            revicionMedicaDao.insert(revicionMedica)
            insertedFinished()
        }
    }
    fun getRevicionesMedicas(personaId:String)= revicionMedicaDao.getRevicionesMedicas(personaId)

    fun emptyRevicionMedicafromPersona(personaId:String,deleteFinished:()->Unit){
        ioExecutor.execute{
            revicionMedicaDao.emptyemptyRevicionMedicafromPersona(personaId)
            deleteFinished()
        }
    }
}