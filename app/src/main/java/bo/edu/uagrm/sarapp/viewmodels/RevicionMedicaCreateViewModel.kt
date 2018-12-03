package bo.edu.uagrm.sarapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import bo.edu.uagrm.sarapp.data.model.RevicionMedicaResult
import bo.edu.uagrm.sarapp.data.repository.RevicionMedicaRepository

class RevicionMedicaCreateViewModel
    (
    private val revicionMedicaRepository: RevicionMedicaRepository,
    private val personaId: String
) : ViewModel() {

    //val revicionMedica=MutableLiveData<RevicionMedica>()

    val onSubmitRevicionMedica = MutableLiveData<RevicionMedica>()

    val revicionMedicaResult: LiveData<RevicionMedicaResult> = Transformations.map(onSubmitRevicionMedica){
        Log.d("aslk2332l","asdf14as6d5f")
        revicionMedicaRepository.createRevicionMedicaFirebase(personaId, it)
    }
    val createSuccess: LiveData<RevicionMedica> = Transformations.switchMap(revicionMedicaResult) { it.data }
    val networkError: LiveData<String> = Transformations.switchMap(revicionMedicaResult) { it.networkErrors }


    fun onCreateRevicionMedica(revicionMedica:RevicionMedica) {

        onSubmitRevicionMedica.postValue(revicionMedica)
    }
}