package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bo.edu.uagrm.sarapp.data.repository.FichaMedicaRepository

class FichaMedicaViewModelFactory (private val repository: FichaMedicaRepository,
                                   private val personaId:String): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass:Class<T>):T{
        if (modelClass.isAssignableFrom(FichaMedicaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FichaMedicaViewModel(repository,personaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}