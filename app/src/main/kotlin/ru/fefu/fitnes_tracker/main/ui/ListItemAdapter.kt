package ru.fefu.fitnes_tracker.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ListItemDateBinding
import ru.fefu.activitytracker.databinding.ListItemMyActivityBinding
import ru.fefu.activitytracker.databinding.ListItemUserActivityBinding

class ListItemAdapter
    : ListAdapter<ListItem, RecyclerView.ViewHolder>(ListItemCallback()) {

    private var itemClickListener: (Int) -> Unit = {}

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    fun getItemClickListener(): (Int) -> Unit {
        return itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.list_item_date -> DateViewHolder(
                ListItemDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_my_activity -> MyActivityViewHolder(
                ListItemMyActivityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_user_activity -> UserActivityViewHolder(
                ListItemUserActivityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DateViewHolder -> holder.bind(currentList[position] as ListItem.Date)
            is MyActivityViewHolder -> holder.bind(currentList[position] as ListItem.MyActivity)
            is UserActivityViewHolder -> holder.bind(currentList[position] as ListItem.UserActivity)
        }
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is ListItem.Date -> R.layout.list_item_date
            is ListItem.MyActivity -> R.layout.list_item_my_activity
            is ListItem.UserActivity -> R.layout.list_item_user_activity
        }
    }

    inner class DateViewHolder(private val binding: ListItemDateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: ListItem.Date) {
            binding.tvListItemDate.text = date.date
        }
    }

    inner class MyActivityViewHolder(private val binding: ListItemMyActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cvListItemMy.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) itemClickListener.invoke(position)
            }
        }
        fun bind(activity: ListItem.MyActivity) {
            binding.tvListItemMyActivity.text = activity.activity
            binding.tvListItemMyDistance.text = activity.distance
            binding.tvListItemMyTime.text = activity.time
            binding.tvListItemMyDate.text = activity.date
        }
    }

    inner class UserActivityViewHolder(private val binding: ListItemUserActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cvListItemUser.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) itemClickListener.invoke(position)
            }
        }
        fun bind(activity: ListItem.UserActivity) {
            binding.tvListItemUserActivity.text = activity.activity
            binding.tvListItemUserDistance.text = activity.distance
            binding.tvListItemUserTime.text = activity.time
            binding.tvListItemUserDate.text = activity.date
            binding.tvListItemUserUsername.text = activity.username
        }
    }

    internal class ListItemCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.Date && newItem is ListItem.Date ->
                    oldItem.date == newItem.date
                oldItem is ListItem.MyActivity && newItem is ListItem.MyActivity ->
                    oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.Date && newItem is ListItem.Date ->
                    areItemsTheSame(oldItem, newItem)
                oldItem is ListItem.MyActivity && newItem is ListItem.MyActivity ->
                    oldItem == newItem
                else -> false
            }

        }

//    fun removeItem(position: Int) {
//        if (position in items.indices) {
//            items.removeAt(position)
//            notifyItemRemoved(position)
//        }
//    }
    }
}
