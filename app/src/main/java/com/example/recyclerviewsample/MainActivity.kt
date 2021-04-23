package com.example.recyclerviewsample

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewsample.database.NameData
import com.example.recyclerviewsample.database.NameDatabase
import com.example.recyclerviewsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var data: List<NameData>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

            val application = requireNotNull(this).application
            val dataSource = NameDatabase.getInstance(application).NameDatabaseDao


            val viewModelFactory = MainViewModelFactory(dataSource)
            val mainViewModel: MainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
            binding.button.setOnClickListener {
                val str = binding.textBox.text.toString()
                binding.textBox.setText("")
                mainViewModel.insert(str)
                mainViewModel.getData()
            }
            mainViewModel.state.observe(this, Observer {
                if (it == true) {
                    data = mainViewModel.data!!
                    mainViewModel.state.value = false
                    showData()
                }
            })

            binding.nameData.layoutManager=LinearLayoutManager(this)
            mainViewModel.getData()
        } catch (e: Exception) {
            Log.i("hello ", "error $e")
        }
    }

    fun showData() {


        val adapter=NameDataAdapter()

        binding.nameData.adapter = adapter
        adapter.submitList(data)


    }
}