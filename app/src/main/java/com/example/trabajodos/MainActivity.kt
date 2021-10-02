package com.example.trabajodos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trabajodos.databinding.ActivityMainBinding
import com.example.trabajodos.entities.Vehicle
import com.example.trabajodos.repository.VehicleRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.log
import android.widget.Toast.makeText as toastMakeText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()

    }

    private fun buildList() {
// Get Repository
        val repository = VehicleRepository.getRepository(this)
// Build Layout manager
        val layoutManager = GridLayoutManager(this, 1)
// Catch other thread
        lifecycleScope.launch {


            repository.allVehicles.collect { Vehicle ->
                binding.rvVehicles.apply {
                    adapter = VehiclesAdapter(Vehicle)
                    setLayoutManager(layoutManager)
                }
                var total=0
                //Toast.makeText(applicationContext, it.year.toString(),Toast.LENGTH_SHORT).show()
                Vehicle.forEach {

                    total+=it.year

                }
                binding.total.apply {
                    text="$ " +total.toString()
                }
                //Toast.makeText(applicationContext, total.toString(),Toast.LENGTH_SHORT).show()
                }


            }


        }




    private fun addListeners() {


        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddVehicleActivity::class.java))
        }
       // binding.btnCleanList.setOnClickListener {
           // val repository = VehicleRepository.getRepository(this)
            //lifecycleScope.launch {
               // repository.deleteAll()
            }
        }


