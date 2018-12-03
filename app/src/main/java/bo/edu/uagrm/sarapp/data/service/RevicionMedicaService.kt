package bo.edu.uagrm.sarapp.data.service

import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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


    companion object {
        fun create():RevicionMedicaService{
            return RevicionMedicaService()
        }
    }

}