package bo.edu.uagrm.sarapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.data.model.PersonaSearchResult
import bo.edu.uagrm.sarapp.data.model.PersonaUpdateDatabaseResult
import bo.edu.uagrm.sarapp.data.repository.PersonaRepository

class PersonaViewModel(private val repository:PersonaRepository) : ViewModel() {
    //private var mPersonas: MutableLiveData<ArrayList<Persona>> = MutableLiveData()
    private val queryLiveData =  MutableLiveData<String>()
    private val personaResult : LiveData<PersonaSearchResult> = Transformations.map(queryLiveData){
        repository.search(it)
    }

    private val update = MutableLiveData<Boolean>()
    private val updateDatabaseResult: LiveData<PersonaUpdateDatabaseResult> = Transformations.map(update){
        repository.updateLocalDatabase()
    }
    val updateDatabaseStatus:LiveData<Boolean> = Transformations.switchMap(updateDatabaseResult){it->it.data}
    val updateDatabaseError:LiveData<String> = Transformations.switchMap(updateDatabaseResult){it->it.networkErrors}

    val personas:LiveData<PagedList<Persona>> = Transformations.switchMap(personaResult) { it -> it.data }
    //val networkErrors:LiveData<String> = Transformations.switchMap(personaResult){it->it.networkErrors}


    fun searchPersona(queryString:String){
        queryLiveData.postValue(queryString)
    }
    fun lastQueryValue(): String? = queryLiveData.value

    fun update(){
        update.postValue(true)
    }


}