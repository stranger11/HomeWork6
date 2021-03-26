package com.example.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskViewModel
import kotlinx.android.synthetic.main.custom_row.view.*

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