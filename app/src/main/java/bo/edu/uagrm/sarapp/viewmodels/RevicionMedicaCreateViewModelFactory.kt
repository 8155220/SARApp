package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bo.edu.uagrm.sarapp.data.repository.RevicionMedicaRepository

class RevicionMedicaCreateViewModelFactory (private val repository: RevicionMedicaRepository,
                                            private val personaId:String): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass:Class<T>):T{
        if (modelClass.isAssignableFrom(RevicionMedicaCreateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RevicionMedicaCreateViewModel(repository,personaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}