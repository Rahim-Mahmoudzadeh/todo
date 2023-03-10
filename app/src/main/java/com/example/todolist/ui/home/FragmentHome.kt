package com.example.todolist.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.ui.dialog.FragmentDialog
import com.example.todolist.utils.BaseFragment
import com.example.todolist.utils.Constant.TASK
import com.example.todolist.utils.Constant.TASK_CHECK
import com.example.todolist.utils.Constant.TASK_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHome : BaseFragment(), FragmentDialog.AddTask, AdapterTasks.ClickTask {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val fragmentDialog = FragmentDialog(this)
    private val homeViewModel by viewModel<HomeViewModel>()

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
        getSearchText()
        setRecyclerView()
    }

    private fun getSearchText() {
        binding.etFragmentHomeSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text != null) {
                    searchTask(text.toString())
                } else {
//                    getTasks()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun onClick() {
        binding.fabtnFragmentHomeAddTasks.setOnClickListener {
            fragmentDialog.show(parentFragmentManager, null)
        }
        binding.ivFragmentHomeDeleteTask.setOnClickListener {
            homeViewModel.deleteAllTask()
        }
    }

    private fun getTasks() {
        homeViewModel.tasks.observe(viewLifecycleOwner) {
            it?.let { tasks ->
                adapterTasks.tasks = tasks as ArrayList<Task>
            }
        }
    }

    private fun searchTask(textSearch: String) {
        homeViewModel.searchTask(textSearch).observe(viewLifecycleOwner) {
            it?.let { tasks ->
                adapterTasks.tasks = tasks as ArrayList<Task>
            }
        }
    }

    private fun setRecyclerView() {
        binding.rvFragmentHomeTasks.apply {
            adapter = adapterTasks
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun addTask(task: Task) {
        homeViewModel.addTask(task)
    }

    override fun updateTask(task: Task) {
        homeViewModel.update(task)
    }

    override fun clickTask(task: Task) {
        homeViewModel.update(task.apply { isChecked = !task.isChecked })
    }

    override fun update(task: Task) {

        val bundle = Bundle().apply {
            putParcelable(TASK, task)
        }
        fragmentDialog.arguments = bundle
        fragmentDialog.show(parentFragmentManager, null)
    }

    override fun removeTask(task: Task) {
        homeViewModel.deleteTask(task)
    }
}