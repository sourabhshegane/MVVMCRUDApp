package com.sourabhcodes.android.mvvmcrudapp.db.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sourabhcodes.android.mvvmcrudapp.R
import com.sourabhcodes.android.mvvmcrudapp.SubscriberViewModel
import com.sourabhcodes.android.mvvmcrudapp.SubscriberViewModelFactory
import com.sourabhcodes.android.mvvmcrudapp.adapters.SubscribersListRecyclerViewAdapter
import com.sourabhcodes.android.mvvmcrudapp.databinding.ActivityMainBinding
import com.sourabhcodes.android.mvvmcrudapp.db.SubscriberDatabase
import com.sourabhcodes.android.mvvmcrudapp.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(applicationContext).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.subscriberViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvSubscribers.layoutManager = LinearLayoutManager(applicationContext)
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscriberViewModel.allSubscribers.observe(this, Observer {
            binding.rvSubscribers.adapter = SubscribersListRecyclerViewAdapter(it)
        })
    }
}