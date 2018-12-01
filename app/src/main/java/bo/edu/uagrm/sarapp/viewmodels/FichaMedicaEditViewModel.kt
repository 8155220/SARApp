package bo.edu.uagrm.sarapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.repository.FichaMedicaRepository

class FichaMedicaEditViewModel
(private val fichaMedicaRepository: FichaMedicaRepository,
 private val personaId:String) : ViewModel(){

    var selectedItem = MutableLiveData<Int>()
    var onAddedItemAlergia = MutableLiveData<String>()
    var onAddedItemCirugia = MutableLiveData<String>()
    var fichaMedica:FichaMedica = FichaMedica(alergias = ArrayList(),cirugias = ArrayList())



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
    //switchs
    public fun onHtaAppChanged(bol:Boolean) {fichaMedica.htaApp=bol}
    public fun onHtaApfChanged(bol:Boolean) {fichaMedica.htaApf=bol}

    public fun onDiabetesAppChanged(bol:Boolean){fichaMedica.diabetesApp =bol}
    public fun onDiabetesApfChanged(bol:Boolean){fichaMedica.diabetesApf =bol}
    public fun onCardiopatiaAppChanged(bol:Boolean){fichaMedica.cardiopatiaApp =bol}
    public fun onCardiopatiaApfChanged(bol:Boolean){fichaMedica.cardiopatiaApf =bol}
    public fun onTuberculosisAppChanged(bol:Boolean){fichaMedica.tuberculosisApp =bol}
    public fun onTuberculosisApfChanged(bol:Boolean){fichaMedica.tuberculosisApf =bol}
    public fun onChagasAppChanged(bol:Boolean){fichaMedica.chagasApp =bol}
    public fun onChagasApfChanged(bol:Boolean){fichaMedica.chagasApf =bol}
    public fun onAsmaBronquialAppChanged(bol:Boolean){fichaMedica.asmaBronquialApp =bol}
    public fun onAsmaBronquialApfChanged(bol:Boolean){fichaMedica.asmaBronquialApf =bol}



    public fun onChagasChange(bol:Boolean){
        Log.d("sSelectedItem",bol.toString())
    }


    public fun onSaveButton(personaId:String,listaAlergias:MutableList<String>,listaCirugias:MutableList<String>){
        fichaMedica.alergias=listaAlergias
        fichaMedica.cirugias=listaCirugias
       // val service = FichaMedicaService();
        //service.updateFichaMedica(personaId,fichaMedica)

        fichaMedicaRepository.updateFichaMedica(personaId,fichaMedica)
    }


}