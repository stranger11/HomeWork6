package com.example.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.data.Task
import kotlinx.android.synthetic.main.custom_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListOfTasksAdapter: RecyclerView.Adapter<ListOfTasksAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.task_text.text = currentItem.task
        holder.itemView.text_description.text = currentItem.description
        holder.itemView.date_txt.text = currentItem.date

        holder.itemView.setOnClickListener {
            val action = ListOfTasksFragmentDirections.actionListOfTasksFragmentToUpdateTaskFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}