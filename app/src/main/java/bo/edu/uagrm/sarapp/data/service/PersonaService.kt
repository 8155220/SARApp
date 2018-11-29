package bo.edu.uagrm.sarapp.data.service

import android.util.Log
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.utils.getArrayValue
import com.google.firebase.database.*

class PersonaService {
    private val db: FirebaseDatabase;
    private lateinit var dbRef:DatabaseReference;
    private val PERSONA_PATH="personas"
    private val TAG = PersonaService::class.java.canonicalName
    init {
        db = FirebaseDatabase.getInstance();
        dbRef=db.getReference(PERSONA_PATH)
    }

    fun searchPersonas(query:String,itemsPerPage:Int,onSuccess:(personas:List<Persona>)->Unit,
                      onError:(error:String)->Unit){

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Log.d(TAG,"DataCambio")
                        onSuccess(dataSnapshot.getArrayValue(Persona::class.java))
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