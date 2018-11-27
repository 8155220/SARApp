package bo.edu.uagrm.sarapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import bo.edu.uagrm.sarapp.data.db.PersonaLocalCache
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.data.service.PersonaService

class PersonaBoundaryCallBack (
    private val query: String,
    private val service: PersonaService,
    private val cache:PersonaLocalCache
) :PagedList.BoundaryCallback<Persona>(){
    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    val networkErrors: LiveData<String>
        get() = _networkErrors
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData(query)
    }
    override fun onItemAtEndLoaded(itemAtEnd: Persona) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
       /* if (isRequestInProgress) return

        isRequestInProgress = true
        service.searchPersonas(query, NETWORK_PAGE_SIZE, { personas ->
            cache.insert(personas) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })*/
    }

}