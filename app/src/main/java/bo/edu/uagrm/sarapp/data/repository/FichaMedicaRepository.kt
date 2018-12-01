package bo.edu.uagrm.sarapp.data.repository

import androidx.lifecycle.MutableLiveData
import bo.edu.uagrm.sarapp.data.db.FichaMedicaCache
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.model.FichaMedicaResult
import bo.edu.uagrm.sarapp.data.service.FichaMedicaService

class FichaMedicaRepository
    (private val service: FichaMedicaService,
     private val cache: FichaMedicaCache
){
    private var updateSucess = MutableLiveData<Boolean>()
    private var networkError = MutableLiveData<String>()
    fun getFichaMedicaFromFirebase(personaId:String){
        //service.
    }

    fun updateFichaMedica(personaId:String,data: FichaMedica):FichaMedicaResult{
        //service.
        service.updateFichaMedica(personaId,data,{ fichaMedica ->
            cache.insert(fichaMedica){
                updateSucess.postValue(true)
            }
        },{error ->
            networkError.postValue(error)
        })

        return FichaMedicaResult(updateSucess,networkError)
    }

    fun getFichaMedicaLocal(personaId:String)=cache.getFichaMedica(personaId)

}

