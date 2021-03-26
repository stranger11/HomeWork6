package com.example.tasklist

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskViewModel
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_list_of_tasks.view.*
import kotlinx.android.synthetic.main.fragment_update_task.*

class ListOfTasksFragment : Fragment() {

    private lateinit var  mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list_of_tasks, container, false)

        val adapter = ListOfTasksAdapter()
        val recView = root.rec_view
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
        })

        root.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listOfTasksFragment_to_addTaskFragment)
        }

        return root
    }

}