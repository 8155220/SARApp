package bo.edu.uagrm.sarapp.data.model

import androidx.room.Entity
import bo.edu.uagrm.sarapp.utils.FirebaseObject

@Entity(tableName="personas")
data class Persona(
    var nombre:String ="",
    var apellidoPaterno:String ="",
    var apellidoMaterno:String ="",
    var sexo:String ="",
    var fechaNacimiento:Long =128,
    var numeroCarnetIdentidad:String ="",
    var direccion:String ="",
    var celular:Long =128,
    var telefonoFijo:Long =128,
    var pais:String ="",
    var departamento:String ="",
    var provincia:String="",
    var capital:String ="",
    var municipio:String ="",
    var nombreTutor:String ="",
    var celularTutor:Long =128,
    var estado:String ="",
    var tipoPersona:String ="",
    var grado:String ="",
    var idCompania:String ="", //Peticion para compnia
    var fotoURL:String ="",
    var nombreCompleto:String ="",
    var timestamp:Long =128): FirebaseObject(){
    
}