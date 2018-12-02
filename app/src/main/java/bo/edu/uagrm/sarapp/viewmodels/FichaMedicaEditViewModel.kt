package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.model.FichaMedicaResult
import bo.edu.uagrm.sarapp.data.repository.FichaMedicaRepository

class FichaMedicaEditViewModel
(private val fichaMedicaRepository: FichaMedicaRepository,
 private val personaId:String) : ViewModel(){

    var fichaMedicaLocal: LiveData<FichaMedica>
    //form liveData
    var selectedItem = MutableLiveData<Int>()
    var onAddedItemAlergia = MutableLiveData<String>()
    var onAddedItemCirugia = MutableLiveData<String>()

    //Users Click submitButton
    val onSubmitFichaMedica= MutableLiveData<FichaMedica>()
    //Send and Waiting for result of updating
    var fichaMedicaResult: LiveData<FichaMedicaResult> = Transformations.map(onSubmitFichaMedica){
        fichaMedicaRepository.updateFichaMedica(personaId,it as FichaMedica)
    }
    //OnSuccess
    var updateSuccess: LiveData<FichaMedica> = Transformations.switchMap(fichaMedicaResult){
        it.data
    }
    //OnError
    val updateError: LiveData<String> = Transformations.switchMap(fichaMedicaResult){
        it.networkErrors
    }


    init {
        fichaMedicaLocal = fichaMedicaRepository.getFichaMedicaLocal(personaId)
    }

    public fun onAddAlergia(data:String?=""){
        if(data!=null){
            onAddedItemAlergia.postValue(data)
        }
    }
    public fun onAddCirugia(data:String?=""){
        if(data!=null){
            onAddedItemCirugia.postValue(data)
        }

    }

    public fun onSaveButton(personaId:String,listaAlergias:MutableList<String>,listaCirugias:MutableList<String>){
        onSubmitFichaMedica.postValue(fichaMedicaLocal.value)
    }


}