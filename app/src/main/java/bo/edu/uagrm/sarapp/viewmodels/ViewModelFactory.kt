package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bo.edu.uagrm.sarapp.data.repository.PersonaRepository

class ViewModelFactory (private val repository:PersonaRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass:Class<T>):T{
        if (modelClass.isAssignableFrom(PersonaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}