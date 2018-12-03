package bo.edu.uagrm.sarapp.data.model

import androidx.room.Entity
import bo.edu.uagrm.sarapp.utils.FirebaseObject

@Entity(tableName="reviciones_medicas")
data class RevicionMedica
    (var personaId:String= "",
     var fecha:Long=1543622400,
     var peso:Double=0.0,
     var estatura:Double=0.0,
     var indiceMasaCorporal:Double=0.0,
     var presionArterial:Int=0,
     var frecuenciaCardiaca:Int=0,
     var tratamiento:String=""):FirebaseObject()
{
}