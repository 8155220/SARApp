package bo.edu.uagrm.sarapp.data.repository

import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.data.service.FichaMedicaService

class FichaMedicaRepository
    (private val service: FichaMedicaService
     //,
//     private val cache:FichaMedicaCache
){

    fun getFichaMedicaFromFirebase(personaId:String){
        //service.
    }

    fun updateFichaMedica(personaId:String,data: FichaMedica){
        //service.
    }
}

