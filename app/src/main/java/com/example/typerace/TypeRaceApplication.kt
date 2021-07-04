package com.example.typerace

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TypeRaceApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { TypeRaceDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TypeRaceRepository(database.wordDao()) }
}