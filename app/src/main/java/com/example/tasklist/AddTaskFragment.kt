package com.example.tasklist

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*


class AddTaskFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_task, container, false)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        root.add_button.setOnClickListener {
            InsertDataToDatabase()
        }
        return root
    }

    private fun InsertDataToDatabase() {
        val task = add_task.text.toString()
        val description = add_description.text.toString()
        if(inputCheck(task, description)) {
            val task = Task(0, task, description )
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(),
                R.string.success,
                Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addTaskFragment_to_listOfTasksFragment)
        } else {
            Toast.makeText(requireContext(),
                R.string.no_success,
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(task: String, description: String): Boolean {
        return !(TextUtils.isEmpty(task) && TextUtils.isEmpty(description))
    }
}