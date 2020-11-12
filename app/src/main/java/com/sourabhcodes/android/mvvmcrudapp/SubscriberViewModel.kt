package com.sourabhcodes.android.mvvmcrudapp

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabhcodes.android.mvvmcrudapp.db.Subscriber
import com.sourabhcodes.android.mvvmcrudapp.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel(),
    Observable {

    val allSubscribers = subscriberRepository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    val saveOrUpdateButton = MutableLiveData<String>()
    val clearAllOrDeleteButton = MutableLiveData<String>()

    init {
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!

        insertSubscriber(Subscriber(0, name, email))
        inputName.value = null
        inputEmail.value = null
    }

    fun clearAllOrDelete() {
        clearAllSubscribers()
    }

    private fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.insertSubscriber(subscriber)
    }

    private fun deleteSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.delete(subscriber)
    }

    private fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.update(subscriber)
    }

    private fun clearAllSubscribers() = viewModelScope.launch {
        subscriberRepository.deleteAllSubscribers()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}