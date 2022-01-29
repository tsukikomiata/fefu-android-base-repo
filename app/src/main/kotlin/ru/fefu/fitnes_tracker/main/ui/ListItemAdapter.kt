package ru.fefu.fitnes_tracker.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ItemDateBinding
import ru.fefu.activitytracker.databinding.ItemMyActivityBinding
import ru.fefu.activitytracker.databinding.ItemUserActivityBinding

class ListItemAdapter (
    items: List<ListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = items.toMutableList()

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
            R.layout.item_date -> DateViewHolder(
                    ItemDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_my_activity -> MyActivityViewHolder(
                ItemMyActivityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_user_activity -> UserActivityViewHolder(
                ItemUserActivityBinding.inflate(
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
            is ListItem.Date -> R.layout.item_date
            is ListItem.MyActivity -> R.layout.item_my_activity
            is ListItem.UserActivity -> R.layout.item_user_activity
        }
    }

        inner class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(date: ListItem.Date) {
                binding.dateItemTextView.text = date.date
            }
        }

    inner class MyActivityViewHolder(private val binding: ItemMyActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.myActivityItemCardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) itemClickListener.invoke(position)
            }
        }
        fun bind(activity: ListItem.MyActivity) {
            binding.myActivityItemTextViewActivity.text = activity.activity
            binding.myActivityItemTextViewDistance.text = activity.distance
            binding.myActivityItemTextViewTime.text = activity.time
            binding.myActivityItemTextViewDate.text = activity.date
        }
    }

    inner class UserActivityViewHolder(private val binding: ItemUserActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.userActivityItemCardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) itemClickListener.invoke(position)
            }
        }
        fun bind(activity: ListItem.UserActivity) {
            binding.userActivityItemTextViewActivity.text = activity.activity
            binding.userActivityItemTextViewDistance.text = activity.distance
            binding.userActivityItemTextViewTime.text = activity.time
            binding.userActivityItemTextViewDate.text = activity.date
            binding.userActivityItemTextViewUser.text = activity.user
        }
    }

//    fun removeItem(position: Int) {
//        if (position in items.indices) {
//            items.removeAt(position)
//            notifyItemRemoved(position)
//        }
//    }

}
