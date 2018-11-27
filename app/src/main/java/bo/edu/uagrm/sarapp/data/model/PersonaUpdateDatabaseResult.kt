package bo.edu.uagrm.sarapp.data.model

import androidx.lifecycle.LiveData



data class PersonaUpdateDatabaseResult(
    val data: LiveData<Boolean>,
    val networkErrors: LiveData<String>
)