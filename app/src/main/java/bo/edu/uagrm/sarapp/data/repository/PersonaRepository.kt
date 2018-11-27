package bo.edu.uagrm.sarapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import bo.edu.uagrm.sarapp.data.db.PersonaLocalCache
import bo.edu.uagrm.sarapp.data.model.PersonaSearchResult
import bo.edu.uagrm.sarapp.data.model.PersonaUpdateDatabaseResult
import bo.edu.uagrm.sarapp.data.service.PersonaService

class PersonaRepository
    (
    private val service: PersonaService,
    private val cache: PersonaLocalCache
) {
    private var updateSucess = MutableLiveData<Boolean>()
    private var networkError = MutableLiveData<String>()
    private val TAG = PersonaRepository::class.java.canonicalName

    fun search(query: String): PersonaSearchResult {
        Log.d("PersonaRepository", "New query: $query")
        val dataSourceFactory = cache.personasByName(query)
        val boundaryCallBack = PersonaBoundaryCallBack(query, service, cache)
        val networkError = boundaryCallBack.networkErrors
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallBack)
            .build()
        return PersonaSearchResult(data)
    }

    fun getPersona(key: String) = cache.getPersona(key)

    fun updateLocalDatabase(): PersonaUpdateDatabaseResult {

        service.searchPersonas("", 10, { personas ->
            cache.insert(personas) {
                updateSucess.postValue(true)
            }
        }, { error ->
            networkError.postValue(error)
        })
        return PersonaUpdateDatabaseResult(updateSucess, networkError)


    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20;
    }
}