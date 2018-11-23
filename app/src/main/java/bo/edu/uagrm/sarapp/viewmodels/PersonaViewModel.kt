package bo.edu.uagrm.sarapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.data.model.PersonaSearchResult
import bo.edu.uagrm.sarapp.data.repository.PersonaRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PersonaViewModel(private val repository:PersonaRepository) : ViewModel() {
    //private var mPersonas: MutableLiveData<ArrayList<Persona>> = MutableLiveData()
    private val queryLiveData =  MutableLiveData<String>()

    private val personaResult : LiveData<PersonaSearchResult> = Transformations.map(queryLiveData){
        repository.search(it)
    }

    val personas:LiveData<PagedList<Persona>> = Transformations.switchMap(personaResult) { it -> it.data }

    val networkErrors:LiveData<String> = Transformations.switchMap(personaResult){it->it.networkErrors}


    //private val repository = PersonaRepository()

    fun searchPersona(queryString:String){
        queryLiveData.postValue(queryString)
    }
    fun lastQueryValue(): String? = queryLiveData.value
    /*
    fun getPersonas2(): LiveData<ArrayList<Persona>> {
        if (mPersonas.value == null) {
            FirebaseDatabase.getInstance()
                .getReference("animals")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Log.i("DataSnapshot :",dataSnapshot.value.toString())
                            var elements = dataSnapshot.children.toMutableList();
                            var lista: ArrayList<Persona> = ArrayList<Persona>();
                            //var lista: ArrayList<Persona> = dataSnapshot.value;
                            /*for(element in dataSnapshot.children){
                               // val map = element.getValue(Map::class.java) as Map<String, String>
                                Log.i("Element :",element.value.toString())

                                val persona = element.getValue(Persona::class.java) as Persona
                                lista.add(persona);
                                //lista.add(Persona(map.get("nombreCompleto") as String));
                            }*/
                            for (element in elements) {
                                //lista.add(Persona(element.toString()));
                                var persona: Persona = element.getValue(Persona::class.java) as Persona
                                lista.add(persona);
                            }
                            mPersonas.postValue(lista)
                        }
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        Log.i("PersonalModel","Error")
                        Log.i("PersonalModel",p0.message)
                    }
                })
        }

        return mPersonas
    }*/


}