package bo.edu.uagrm.sarapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import bo.edu.uagrm.sarapp.data.db.RevicionMedicaCache
import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import bo.edu.uagrm.sarapp.data.model.RevicionMedicaResult
import bo.edu.uagrm.sarapp.data.service.RevicionMedicaService

class RevicionMedicaRepository
    (private val service: RevicionMedicaService,
     private val cache: RevicionMedicaCache){
    private val updateSuccess = MutableLiveData<RevicionMedica>()
    private val networkError = MutableLiveData<String>()

    fun createRevicionMedicaFirebase(personaId:String,data:RevicionMedica): RevicionMedicaResult {
        Log.d("sdfas23","sdfasf")
        service.createRevicionMedica(personaId,data,{revicionMedica->
            cache.insert(revicionMedica){
                updateSuccess.postValue(revicionMedica)
            }
        },{error->
            networkError.postValue(error)
        })
        return RevicionMedicaResult(updateSuccess,networkError)
    }


}