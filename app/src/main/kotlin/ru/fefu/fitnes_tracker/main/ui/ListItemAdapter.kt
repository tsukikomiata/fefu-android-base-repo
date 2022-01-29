package ru.fefu.fitnes_tracker.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ListItemDateBinding
import ru.fefu.activitytracker.databinding.ListItemMyActivityBinding
import ru.fefu.activitytracker.databinding.ListItemUserActivityBinding

class ListItemAdapter (
    listItems: List<ListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = listItems.toMutableList()

    private var itemClickListener: (Int) -> Unit = {}

    private var myItemClickListener: (Int, ListItem.MyActivity) -> Unit =
        { position: Int, data: ListItem.MyActivity -> }

    private var userItemClickListener: (Int, ListItem.UserActivity) -> Unit =
        { position: Int, data: ListItem.UserActivity -> }

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    fun setMyItemClickListener(listener: (Int, ListItem.MyActivity) -> Unit) {
        myItemClickListener = listener
    }
    fun setUserItemClickListener(listener: (Int, ListItem.UserActivity) -> Unit) {
        userItemClickListener = listener
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
            is DateViewHolder -> holder.bind(items[position] as ListItem.Date)
            is MyActivityViewHolder -> holder.bind(items[position] as ListItem.MyActivity)
            is UserActivityViewHolder -> holder.bind(items[position] as ListItem.UserActivity)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
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

//    fun removeItem(position: Int) {
//        if (position in items.indices) {
//            items.removeAt(position)
//            notifyItemRemoved(position)
//        }
//    }

}
