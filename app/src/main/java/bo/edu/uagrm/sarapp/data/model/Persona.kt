package bo.edu.uagrm.sarapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import bo.edu.uagrm.sarapp.utils.FirebaseObject

@Entity(tableName="personas")
data class Persona(var name:String="Default",
    var description:String ="Default"
    //,var key:String ="keydefault"
): FirebaseObject(){
    
}