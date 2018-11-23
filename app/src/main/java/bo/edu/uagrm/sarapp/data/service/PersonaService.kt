package bo.edu.uagrm.sarapp.data.service

import android.util.Log
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.utils.getArrayValue
import com.google.firebase.database.*

class PersonaService {
    private val db: FirebaseDatabase;
    private lateinit var dbRef:DatabaseReference;
    private val PERSONA_PATH="animals"

    init {
        db = FirebaseDatabase.getInstance();
        dbRef=db.getReference(PERSONA_PATH)
    }

    fun searchPersonas(query:String,itemsPerPage:Int,onSuccess:(personas:List<Persona>)->Unit,
                      onError:(error:String)->Unit){

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Log.i("DataSnapshot :",dataSnapshot.value.toString())
                        var elements = dataSnapshot.children.toMutableList();
                        var lista: ArrayList<Persona> = ArrayList<Persona>();
                        Log.d("PersonaBundaryCallBack",elements.toString())
                        onSuccess(dataSnapshot.getArrayValue(Persona::class.java))

                        /*for (element in elements) {
                            var persona: Persona = element.getValue(Persona::class.java) as Persona
                            persona.key = element.key as String;
                            lista.add(persona);
                            Log.d("PersonaBundaryCallBack",persona.toString())
                        }
                        onSuccess(lista)*/
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.i("PersonalModel","Error")
                    Log.i("PersonalModel",p0.message)
                    onError(p0.message)
                }
            })
    }


    companion object {
        fun create():PersonaService{
            return PersonaService()
        }
    }



}