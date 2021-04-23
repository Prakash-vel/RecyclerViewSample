package com.example.recyclerviewsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.recyclerviewsample.database.NameData
import com.example.recyclerviewsample.databinding.NamedataListItemBinding

//class NameDataAdapter: RecyclerView.Adapter<NameDataAdapter.ViewHolder>
class NameDataAdapter: ListAdapter<NameData,NameDataAdapter.ViewHolder>(NameDataDiffCallBack()){
//    var data= listOf<NameData>()
//      set(value){
//          field=value
//          notifyDataSetChanged()
//
//      }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val namedata = getItem(position)
        holder.bind(namedata)
    }



    class ViewHolder(val binding: NamedataListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val textView: TextView=binding.dataName

        fun bind(namedata: NameData) {
            textView.text = namedata.name
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{

                val layoutInflater=LayoutInflater.from(parent.context)
                val view=layoutInflater.inflate(R.layout.namedata_list_item, parent, false)
                val binding=NamedataListItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }


}


class NameDataDiffCallBack : DiffUtil.ItemCallback<NameData>(){
    override fun areItemsTheSame(oldItem: NameData, newItem: NameData): Boolean {
        return oldItem.nameId==newItem.nameId
    }

    override fun areContentsTheSame(oldItem: NameData, newItem: NameData): Boolean {
        return oldItem == newItem
    }

}