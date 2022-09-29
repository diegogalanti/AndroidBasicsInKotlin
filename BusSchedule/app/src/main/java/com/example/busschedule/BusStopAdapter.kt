package com.example.busschedule

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.databinding.BusStopItemBinding
import java.util.*

class BusStopAdapter(val clickListener: ScheduleListener) : ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder
    {
        val viewHolder = BusStopViewHolder(
            BusStopItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(getItem(position), clickListener)
    }

    class BusStopViewHolder(private var binding: BusStopItemBinding): RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule, scheduleListener: ScheduleListener)
        {
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a").format(
                Date(schedule.arrivalTime.toLong() * 1000)
            )
            binding.scheduleListener = scheduleListener
            binding.schedule = schedule
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>()
        {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean
            {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean
            {
                return oldItem == newItem
            }
        }
    }
}

class ScheduleListener(val clickListener: (schedule: Schedule) -> Unit) {
    fun onClick(schedule: Schedule) = clickListener(schedule)
}