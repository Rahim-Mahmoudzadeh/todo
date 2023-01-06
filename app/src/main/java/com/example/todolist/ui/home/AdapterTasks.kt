package com.example.todolist.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.AdapterTaskBinding

class AdapterTasks(val clickTask: ClickTask) : RecyclerView.Adapter<AdapterTasks.ViewHolder>() {

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
                clickTask.clickTask(task)
            }
            binding.cbAdapterTaskNameTask.setOnClickListener {
                clickTask.clickTask(task)
            }
            itemView.setOnLongClickListener {
                clickTask.update(task)
                true
            }
            itemView.setOnClickListener {
                clickTask.removeTask(task)
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

    interface ClickTask{
        fun clickTask(task: Task)
        fun update(task:Task)
        fun removeTask(task: Task)
    }
}