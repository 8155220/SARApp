package bo.edu.uagrm.sarapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.model.FichaMedicaResult
import bo.edu.uagrm.sarapp.data.repository.FichaMedicaRepository

class FichaMedicaViewModel
(private val fichaMedicaRepository: FichaMedicaRepository,
 private val personaId:String) : ViewModel(){

    var fichaMedicaLocal: LiveData<FichaMedica>
    //var revicionMedicaList:LiveData<List<RevicionMedica>>
    //Users Click submitButton
    val onSubmitFichaMedica= MutableLiveData<FichaMedica>()
    //Send and Waiting for result of updating
    var fichaMedicaResult:LiveData<FichaMedicaResult> = Transformations.map(onSubmitFichaMedica){
        Log.d("fichaMedicaResult","")
        fichaMedicaRepository.updateLocalDB(personaId)
    }
    //OnSuccess
    var updateSuccess:LiveData<FichaMedica> = Transformations.switchMap(fichaMedicaResult){
        Log.d("updateSuccess","")
        it.data
    }
    //OnError
    val updateError:LiveData<String> = Transformations.switchMap(fichaMedicaResult){
        it.networkErrors
    }
    init {
        fichaMedicaLocal = fichaMedicaRepository.getFichaMedicaLocal(personaId)
        //revicionMedicaList =
    }

    fun updatefichaMedica(){
        onSubmitFichaMedica.postValue(fichaMedicaLocal.value)
    }
}