package com.example.trabajodos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajodos.databinding.ItemCartBinding
import com.example.trabajodos.entities.Vehicle
import com.example.trabajodos.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("MemberVisibilityCanBePrivate")
class VehiclesAdapter(private val list: List<Vehicle>) :
    RecyclerView.Adapter<VehiclesAdapter.VehiclesViewHolder>() {
    class VehiclesViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)
    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VehiclesViewHolder(binding)

    }


    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        val repository = VehicleRepository.getRepository(holder.binding.root.context)

        holder.binding.btnDelete.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO){
                repository.deleteById(list[position].id)
            }


        }
        with(holder.binding) {
            tvTitle.text = "X"+list[position].color
            tvColor.text = list[position].name
            tvYear.text = "$ "+list[position].year.toString()



        }

    }


    override fun getItemCount(): Int = list.size
}

