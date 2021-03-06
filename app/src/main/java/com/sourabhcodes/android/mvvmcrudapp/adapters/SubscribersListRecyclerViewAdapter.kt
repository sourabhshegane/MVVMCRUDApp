package com.sourabhcodes.android.mvvmcrudapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sourabhcodes.android.mvvmcrudapp.R
import com.sourabhcodes.android.mvvmcrudapp.databinding.ListItemBinding
import com.sourabhcodes.android.mvvmcrudapp.db.Subscriber

class SubscribersListRecyclerViewAdapter(
    private val subscribers: List<Subscriber>,
    private val clickListener: (Subscriber) -> Unit
) : RecyclerView.Adapter<SubscribersListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribersListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return SubscribersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscribersListViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }
}

class SubscribersListViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.tvListItemSubscriberName.text = subscriber.name
        binding.tvListItemSubscriberEmail.text = subscriber.email
        binding.clListItem.setOnClickListener {
            clickListener(subscriber)
        }
    }
}