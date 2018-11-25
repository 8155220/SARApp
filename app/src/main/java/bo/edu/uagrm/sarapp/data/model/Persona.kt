package bo.edu.uagrm.sarapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import bo.edu.uagrm.sarapp.utils.FirebaseObject

@Entity(tableName="personas")
data class Persona(
    var apellidoMaterno:String ="",
    var apellidoPaterno:String ="",
    var capital:String ="",
    var celular:Long =128,
    var celularTutor:Long =128,
    var departamento:String ="",
    var direccion:String ="",
    var estado:String ="",
    var fechaNacimiento:Long =128,
    var fotoURL:String ="",
    var grado:String ="",
    var idCompania:String ="",
    var municipio:String ="",
    var nombre:String ="",
    var nombreCompleto:String ="",
    var nombreTutor:String ="",
    var numeroCarnetIdentidad:String ="",
    var pais:String ="",
    var sexo:String ="",
    var telefonoFijo:Long =128,
    var timestamp:Long =128,
    var tipoPersona:String =""
    //,var key:String ="keydefault"
): FirebaseObject(){
    
}