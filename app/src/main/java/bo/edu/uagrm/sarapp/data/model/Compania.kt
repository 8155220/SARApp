package bo.edu.uagrm.sarapp.data.model

import androidx.room.Entity
import bo.edu.uagrm.sarapp.utils.FirebaseObject


@Entity(tableName="companias")
data class Compania(
    var imagenURL:String="",
    var nombre:String="",
    var descripcion:String="",
    var anio:Number=0
): FirebaseObject(){

}