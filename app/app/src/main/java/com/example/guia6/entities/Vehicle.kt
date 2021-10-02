package com.example.guia6.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "vehicle_table")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "year")
    val year: Int,
)
