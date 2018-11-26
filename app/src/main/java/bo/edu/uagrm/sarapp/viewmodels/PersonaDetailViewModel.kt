package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.data.repository.PersonaRepository

class PersonaDetailViewModel
    (private val personarepository: PersonaRepository,private val personaId:String):ViewModel(){

    val persona: LiveData<Persona>

    init {
        persona = personarepository.getPersona(personaId)
    }

}