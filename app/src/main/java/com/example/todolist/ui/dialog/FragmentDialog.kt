package com.example.todolist.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.DialogFragmentAddBinding
import com.example.todolist.utils.Constant

class FragmentDialog(val addTask: AddTask) : DialogFragment() {

    private var _binding: DialogFragmentAddBinding? = null
    private val binding get() = _binding!!

    private var taskArgument: Task? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            taskArgument = it.getParcelable(Constant.TASK)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        _binding =
            DialogFragmentAddBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        onClick()
        showNameTask()
        dialog.setView(binding.root)
        return dialog.create()
    }

    private fun showNameTask() {
        taskArgument?.let {
            binding.etDialogFragmentNameTask.setText(it.name)
        }
    }

    private fun onClick() {
        binding.btnAddTaskOk.setOnClickListener {
            val nameTaskEditText = binding.etDialogFragmentNameTask.text.toString()
            if (taskArgument != null) {
                val task = Task(nameTaskEditText, 0, taskArgument!!.isChecked)
                task.id = taskArgument!!.id
                addTask.updateTask(task)
            } else {
                val task = Task(nameTaskEditText, 0)
                addTask.addTask(task)
            }
            dismiss()
        }
        binding.btnDialogAddFragmentCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        arguments = null
        taskArgument = null
    }

    interface AddTask {
        fun addTask(task: Task)
        fun updateTask(task: Task)
    }
}