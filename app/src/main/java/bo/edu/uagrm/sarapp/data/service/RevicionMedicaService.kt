package bo.edu.uagrm.sarapp.data.service

import android.util.Log
import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import bo.edu.uagrm.sarapp.utils.getArrayValue
import com.google.firebase.database.*

class RevicionMedicaService {
    private val db: FirebaseDatabase;
    private var dbRef: DatabaseReference;
    private val PATH="revicionesMedicas"
    private val TAG = RevicionMedicaService::class.java.canonicalName
    private val errorMessage = "Ocurrio un error intente mas tarde"
    init {
        db = FirebaseDatabase.getInstance();
        dbRef=db.getReference(PATH)
    }
    fun createRevicionMedica (personaId:String, data: RevicionMedica, onSuccess:(revicionMedica:RevicionMedica)->Unit,
                              onError:(error:String)->Unit) {

        val key = dbRef.push().key.toString()
        data.key=key
        dbRef.child(personaId).child(key).setValue(data).addOnSuccessListener {
            data.personaId=personaId
            onSuccess(data)
        }.addOnFailureListener{
            onError(errorMessage)
        }
    }

    fun updateRevicionMedicaLocalDbFromFirebase(personaId:String, onSuccess: (revicionMedica: List<RevicionMedica>) -> Unit, onError: (error: String) -> Unit){
        dbRef.child(personaId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    onSuccess(dataSnapshot.getArrayValue(RevicionMedica::class.java))
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.i("FichaMedicaService","Error")
                onError(p0.message)
            }
        })
    }


    companion object {
        fun create():RevicionMedicaService{
            return RevicionMedicaService()
        }
    }

}