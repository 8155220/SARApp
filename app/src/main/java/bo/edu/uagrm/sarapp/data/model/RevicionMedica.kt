package bo.edu.uagrm.sarapp.data.model

import android.util.Log
import androidx.room.Entity
import bo.edu.uagrm.sarapp.utils.FirebaseObject
import java.util.*




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

    /*fun timeStampToDateString(timestamp:Long):String{
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(timestamp)
        return formatter.format(calendar.time)
     }   */

    fun timeStampToDateString(timestamp:Long):String{
        val utc = TimeZone.getTimeZone("UTC") // avoiding local time zone overhead
        val cal = GregorianCalendar(utc)


        //val cal = Calendar.getInstance( )
        cal.timeInMillis = timestamp
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month =  cal.get(Calendar.MONTH) + 1
        val year = cal.get(Calendar.YEAR)

        Log.d("RevicionMedica",timestamp.toString())
        Log.d("RevicionMedica","$day-$month-$year")


        return "$day-$month-$year"
    }


   /* companion object {
        fun timeStampToDateString(timestamp:Long):String{
            val formatter = SimpleDateFormat("dd-mm-yyyy")
            val calendar = Calendar.getInstance()
            calendar.setTimeInMillis(timestamp)
            return formatter.format(calendar.time)
        }
    }*/

}