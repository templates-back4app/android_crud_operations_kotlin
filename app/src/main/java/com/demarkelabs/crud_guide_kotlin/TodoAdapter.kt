package com.demarkelabs.crud_guide_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject

class TodoAdapter(private val list: ArrayList<ParseObject>, private val context: Context) : RecyclerView.Adapter<TodoHolder>() {
    var clickListenerToEdit = MutableLiveData<ParseObject>()
    var onDeleteListener = MutableLiveData<ParseObject>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoHolder(view)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val `object` = list[position]
        holder.title!!.text = `object`.getString("title")
        holder.description!!.text = `object`.getString("description")

        holder.edit!!.setOnClickListener { v: View? ->
            clickListenerToEdit.postValue(
                `object`
            )
        }

        holder.delete!!.setOnClickListener { v: View? ->
            onDeleteListener.postValue(
                `object`
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView? = null
    var description: TextView? = null
    var edit: ImageView? = null
    var delete: ImageView? = null

    init {
        title = itemView.findViewById(R.id.title)
        description = itemView.findViewById(R.id.description)
        edit = itemView.findViewById(R.id.edit)
        delete = itemView.findViewById(R.id.delete)
    }
}