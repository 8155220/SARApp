/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bo.edu.uagrm.sarapp.DI

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import bo.edu.uagrm.sarapp.data.db.FichaMedicaCache
import bo.edu.uagrm.sarapp.data.db.PersonaLocalCache
import bo.edu.uagrm.sarapp.data.db.RevicionMedicaCache
import bo.edu.uagrm.sarapp.data.db.SARDatabase
import bo.edu.uagrm.sarapp.data.repository.FichaMedicaRepository
import bo.edu.uagrm.sarapp.data.repository.PersonaRepository
import bo.edu.uagrm.sarapp.data.repository.RevicionMedicaRepository
import bo.edu.uagrm.sarapp.data.service.FichaMedicaService
import bo.edu.uagrm.sarapp.data.service.PersonaService
import bo.edu.uagrm.sarapp.data.service.RevicionMedicaService
import bo.edu.uagrm.sarapp.viewmodels.*
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [GithubLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): PersonaLocalCache {
        val database = SARDatabase.getInstance(context)
        return PersonaLocalCache(database.personasDao(), Executors.newSingleThreadExecutor())
    }
    private fun provideFichaMedicaCache(context:Context):FichaMedicaCache{
        val database = SARDatabase.getInstance(context)
        return FichaMedicaCache(database.fichaMedicaDao(), Executors.newSingleThreadExecutor())
    }
    private fun provideRevicionMedicaCache(context:Context): RevicionMedicaCache {
        val database = SARDatabase.getInstance(context)
        return RevicionMedicaCache(database.revicionMedicaDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun providePersonaRepository(context: Context): PersonaRepository {
        return PersonaRepository(PersonaService.create(), provideCache(context))
    }
    private fun provideFichaMedicaRepository(context: Context): FichaMedicaRepository {
        return FichaMedicaRepository(FichaMedicaService.create(), provideFichaMedicaCache(context))
    }
    private fun provideRevicionMedicaRepository(context: Context): RevicionMedicaRepository {
        return RevicionMedicaRepository(RevicionMedicaService.create(), provideRevicionMedicaCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(providePersonaRepository(context))
    }

    fun providePersonaDetailViewModelFactory(context:Context,personaId:String):ViewModelProvider.Factory{
        return PersonaDetailViewModelFactory(providePersonaRepository(context),personaId)
    }
    fun provideFichaMedicaViewModelFactory(context:Context,personaId:String):ViewModelProvider.Factory{
        return FichaMedicaViewModelFactory(provideFichaMedicaRepository(context), provideRevicionMedicaRepository(context),personaId)
    }
    fun provideFichaMedicaEditViewModelFactory(context:Context,personaId:String):ViewModelProvider.Factory{
        return FichaMedicaEditViewModelFactory(provideFichaMedicaRepository(context),personaId)
    }
    fun provideRevicionMedicaCreateViewModelFactory(context:Context,personaId:String):ViewModelProvider.Factory{
        return RevicionMedicaCreateViewModelFactory(provideRevicionMedicaRepository(context),personaId)
    }
}
