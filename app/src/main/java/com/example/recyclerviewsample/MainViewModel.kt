package com.example.recyclerviewsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewsample.database.NameData
import com.example.recyclerviewsample.database.NameDatabaseDao
import kotlinx.coroutines.*

class MainViewModel(

    private val database: NameDatabaseDao
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val state = MutableLiveData<Boolean>()

    init {
        state.value = false
    }

    var data: List<NameData>? = null
    fun insert(str: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                var sampleData = NameData()
                sampleData.name = str
                database.insert(sampleData)
            }
        }
    }

    fun getData() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                data = database.getAllTasks()
            }
            state.value = true
        }
    }

}


