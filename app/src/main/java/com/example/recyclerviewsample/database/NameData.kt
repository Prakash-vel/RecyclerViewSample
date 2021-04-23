package com.example.recyclerviewsample.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name_data")
data class NameData (
    @PrimaryKey(autoGenerate = true)
    var nameId:Long=0L,

    @ColumnInfo(name = "name")
    var name:String=""
)
