package com.example.guia6.repository

import android.content.Context
import com.example.guia6.dao.VehicleDao
import com.example.guia6.database.VehicleRoomDatabase
import com.example.guia6.entities.Vehicle
import kotlinx.coroutines.flow.Flow
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class VehicleRepository(private val vehicleDao: VehicleDao) {
    companion object {
        private var INSTANCE : VehicleRepository? = null
        fun getRepository(context: Context) : VehicleRepository {
            return INSTANCE ?: synchronized(this) {
                val database = VehicleRoomDatabase.getDatabase(context)
                val instance = VehicleRepository(database.vehicleDao())
                INSTANCE = instance
                instance
            }
        }
    }
    // Room executes all queries on a separate thread.
// Observed Flow will notify the observer when the data has changed.
    val allCart: Flow<List<Vehicle>> = vehicleDao.getAlphabetizedVehicles()
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
// implement anything else to ensure we're not doing long running database work
// off the main thread.
    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }
    suspend fun deleteAll() {
        vehicleDao.deleteAll()
    }
    suspend fun deleteById(id:Int){
        vehicleDao.deleteById(id)
    }
}