package bo.edu.uagrm.sarapp.data.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import java.util.concurrent.Executor

class FichaMedicaCache
    (private val fichaMedicaDao: FichaMedicaDao,
     private val ioExecutor: Executor)
{
    fun insert(fichaMedica: FichaMedica,insertedFinished:()->Unit ){
        ioExecutor.execute{
            fichaMedicaDao.insert(fichaMedica)
            Log.d("FichaCache","Insertando :"+fichaMedica)
            Log.d("FichaCacheLista",fichaMedicaDao.getFichasMedicas().toString())
            insertedFinished()
        }
    }

    fun getFichaMedica(key:String): LiveData<FichaMedica>{
        val fichaMedica =fichaMedicaDao.getFichaMedica(key)

        val mutable:LiveData<FichaMedica> = Transformations.map(fichaMedicaDao.getFichaMedica(key)){
            if(it!=null) {it}
            else {FichaMedica(alergias= ArrayList<String>(),cirugias = ArrayList<String>())}
        }

        /*if(fichaMedica.value==null){
            Log.d("FichaCacheMethod","Entro")
            val mutable:MutableLiveData<FichaMedica> = MutableLiveData()
            var fm = FichaMedica(alergias= ArrayList<String>(),cirugias = ArrayList<String>())
            mutable.postValue(fm)
            return mutable;
        }else {*/
            return mutable



        //return fichaMedicaDao.getFichaMedica(key)
    }


}