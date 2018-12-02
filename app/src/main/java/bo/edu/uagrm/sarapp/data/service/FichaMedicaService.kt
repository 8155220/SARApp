package bo.edu.uagrm.sarapp.data.service

import android.util.Log
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.utils.getArrayValue
import bo.edu.uagrm.sarapp.utils.getSingleValue
import com.google.firebase.database.*

class FichaMedicaService {

    private val db: FirebaseDatabase;
    private lateinit var dbRef: DatabaseReference;
    private val PATH="fichasMedicas"
    private val TAG = FichaMedicaService::class.java.canonicalName
    private val errorMessage = "Ocurrio un error intente mas tarde"
    init {
        db = FirebaseDatabase.getInstance();
        dbRef=db.getReference(PATH)
    }

    fun searchPersonas(query:String, itemsPerPage:Int, onSuccess:(personas:List<Persona>)->Unit,
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

    fun updateFichaMedica(personaId:String,data: FichaMedica,onSuccess: (fichaMedica:FichaMedica) -> Unit,onError: (error: String) -> Unit){
        //val key = dbRef.push().key.toString()
        dbRef.child(personaId).setValue(data).addOnSuccessListener {
            data.key=personaId
            Log.d("FMService",data.toString())
            onSuccess(data)

        }.addOnFailureListener{
            onError(errorMessage)
        }
    }

    fun updateFichaMedicaLocalDbFromFirebase(personaId:String,onSuccess: (fichaMedica:FichaMedica) -> Unit,onError: (error: String) -> Unit){
        dbRef.child(personaId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    onSuccess(dataSnapshot.getSingleValue(FichaMedica::class.java))
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.i("FichaMedicaService","Error")
                onError(p0.message)
            }
        })
    }

    companion object {
        fun create():FichaMedicaService{
            return FichaMedicaService()
        }
    }
}