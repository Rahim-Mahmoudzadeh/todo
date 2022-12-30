package com.example.todolist.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.AdapterTaskBinding

class AdapterTasks(val updateTask: UpdateTask) : RecyclerView.Adapter<AdapterTasks.ViewHolder>() {

    var tasks = ArrayList<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: AdapterTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.cbAdapterTaskNameTask.text = task.name
            binding.cbAdapterTaskNameTask.isChecked = task.isChecked

            itemView.setOnClickListener {
                updateTask.clickTask(task)
            }
            binding.cbAdapterTaskNameTask.setOnClickListener {
                updateTask.clickTask(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    interface UpdateTask{
        fun clickTask(task: Task)
    }
}