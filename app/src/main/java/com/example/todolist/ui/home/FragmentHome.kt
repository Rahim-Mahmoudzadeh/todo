package com.example.todolist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.ui.dialog.FragmentDialog
import com.example.todolist.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FragmentHome : BaseFragment(), FragmentDialog.AddTask,AdapterTasks.UpdateTask {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val fragmentDialog = FragmentDialog(this)
    private val homeViewModel by viewModel<HomeViewModel>()
    var rahim:String?=null

    private val adapterTasks = AdapterTasks(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTasks()
        onClick()

    }

    private fun onClick() {
        binding.fabtnFragmentHomeAddTasks.setOnClickListener {
            fragmentDialog.show(parentFragmentManager, null)
        }
    }

    private fun getTasks() {
        val tasks = homeViewModel.getTasks()
        adapterTasks.tasks= tasks as ArrayList<Task>
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvFragmentHomeTasks.apply {
            adapter=adapterTasks
            layoutManager=LinearLayoutManager(requireContext())
        }
    }

    override fun addTask(task: Task) {
        homeViewModel.addTask(task)
        getTasks()
    }

    override fun clickTask(task: Task) {
        homeViewModel.update(task.apply { isChecked=true })
    }
}