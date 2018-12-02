package bo.edu.uagrm.sarapp.data.model

import androidx.room.Entity
import androidx.room.TypeConverters
import bo.edu.uagrm.sarapp.utils.Converters
import bo.edu.uagrm.sarapp.utils.FirebaseObject
@Entity(tableName="fichas_medicas")
data class FichaMedica(

    var tipoSangre:String = "oNegativo",
    @TypeConverters(Converters::class)
    var alergias:MutableList<String> = ArrayList(),
    @TypeConverters(Converters::class)
    var cirugias:MutableList<String> = ArrayList(),
    var htaApp:Boolean=false,
    var htaApf:Boolean=false,
    var diabetesApp:Boolean=false,
    var diabetesApf:Boolean=false,
    var cardiopatiaApp:Boolean=false,
    var cardiopatiaApf:Boolean=false,
    var tuberculosisApp:Boolean=false,
    var tuberculosisApf:Boolean=false,
    var chagasApp:Boolean=false,
    var chagasApf:Boolean=false,
    var asmaBronquialApp:Boolean=false,
    var asmaBronquialApf:Boolean=false,
    var otras:String=""
):FirebaseObject() {
}