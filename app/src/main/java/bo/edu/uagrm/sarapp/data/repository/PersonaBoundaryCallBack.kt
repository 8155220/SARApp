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
        if (isRequestInProgress) return

        isRequestInProgress = true
        service.searchPersonas(query, NETWORK_PAGE_SIZE, { personas ->
            cache.insert(personas) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })

        ///
        /*
        Log.d("PersonaBundaryCallBack","Antes de obtener Datos")
        FirebaseDatabase.getInstance()
            .getReference("animals")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Log.i("DataSnapshot :",dataSnapshot.value.toString())
                        var elements = dataSnapshot.children.toMutableList();
                        var lista: ArrayList<Persona> = ArrayList<Persona>();

                        Log.d("PersonaBundaryCallBack",elements.toString())
                        for (element in elements) {
                            //lista.add(Persona(element.toString()));
                            var persona: Persona = element.getValue(Persona::class.java) as Persona
                            persona.key = element.key as String;
                            lista.add(persona);
                            Log.d("PersonaBundaryCallBack",persona.toString())
                        }
                        cache.insert(lista){
                            //cache.insert(Arrays.asList(*lista)){
                            lastRequestedPage++
                            isRequestInProgress = false
                        };
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.i("PersonalModel","Error")
                    Log.i("PersonalModel",p0.message)
                    _networkErrors.postValue(p0.message)
                    isRequestInProgress = false
                }
            })*/
        ////
    }

}