package bo.edu.uagrm.sarapp.data.model

import androidx.lifecycle.LiveData

class RevicionMedicaResult
    (val data: LiveData<RevicionMedica>,
     val networkErrors:LiveData<String>)


