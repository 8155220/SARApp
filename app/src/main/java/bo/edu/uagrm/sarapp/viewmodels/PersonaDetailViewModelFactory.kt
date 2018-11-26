package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bo.edu.uagrm.sarapp.data.repository.PersonaRepository

class PersonaDetailViewModelFactory(private val repository: PersonaRepository,
                                    private val personaId:String): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass:Class<T>):T{
        if (modelClass.isAssignableFrom(PersonaDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonaDetailViewModel(repository,personaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}