package bo.edu.uagrm.sarapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.repository.FichaMedicaRepository
import bo.edu.uagrm.sarapp.utils.getViewValueGrupoSanguineoGromValueUtils

class FichaMedicaViewModel
(private val fichaMedicaRepository: FichaMedicaRepository,
 private val personaId:String) : ViewModel(){

    var selectedItem = MutableLiveData<Int>()
    var onAddedItemAlergia = MutableLiveData<String>()
    var onAddedItemCirugia = MutableLiveData<String>()
    var fichaMedica:FichaMedica = FichaMedica(alergias = ArrayList(),cirugias = ArrayList())
    var fichaMedicaLocal: LiveData<FichaMedica>

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
    public fun onAsmaBronquialApfChanged(bol:Boolean){fichaMedica.asmaBronquialApf =bol}
    public fun onChagasChange(bol:Boolean){
        Log.d("sSelectedItem",bol.toString())
    }


    public fun onSaveButton(personaId:String,listaAlergias:MutableList<String>,listaCirugias:MutableList<String>){
        fichaMedicaRepository.updateFichaMedica(personaId,fichaMedicaLocal.value as FichaMedica)
    }

    fun getPosFromGrupoSanguineo(data:String):Int{
        return getPosFromGrupoSanguineo(data)
    }
    fun getViewValueGrupoSanguineoGromValue(value:String):String{
        //Log.d("viewModelTest",value)
        return getViewValueGrupoSanguineoGromValueUtils(value)
    }


}