package com.example.tasklist

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.fragment_update_task.view.*

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_update_task, container, false)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        root.update_task.setText(args.currentTask.task)
        root.update_description.setText(args.currentTask.description)

        root.update_button.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return root
    }

    private fun updateItem() {
        val task = update_task.text.toString()
        val description = update_description.text.toString()

        if(inputCheck(task, description)) {
            val updatedTask = Task(args.currentTask.id, task, description)
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(),
                R.string.success_upd,
                Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_updateTaskFragment_to_listOfTasksFragment)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            findNavController().navigate(R.id.action_updateTaskFragment_to_listOfTasksFragment)
            Toast.makeText(requireContext(),
                R.string.delete,
                Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle(R.string.del_que)
        builder.setMessage(R.string.sure_in_del)
        builder.create().show()
    }
}