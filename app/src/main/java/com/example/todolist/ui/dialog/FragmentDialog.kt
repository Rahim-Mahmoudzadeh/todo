package com.example.todolist.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.DialogFragmentAddBinding

class FragmentDialog(val addTask: AddTask) : DialogFragment() {

    private var _binding: DialogFragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        _binding =
            DialogFragmentAddBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        onClick()

        dialog.setView(binding.root)
        return dialog.create()
    }

    private fun onClick() {
        binding.btnAddTaskOk.setOnClickListener {
            val nameTask = binding.etDialogFragmentNameTask.text.toString()
            val task = Task(nameTask,0)
            addTask.addTask(task)
            dismiss()
        }
        binding.btnDialogAddFragmentCancel.setOnClickListener {
            dismiss()
        }
    }

    interface AddTask {
        fun addTask(task: Task)
    }
}