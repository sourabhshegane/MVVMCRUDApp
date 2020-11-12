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
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

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
        if (isUpdateOrDelete) {
            val name = inputName.value!!
            val email = inputEmail.value!!

            insertSubscriber(Subscriber(0, name, email))
            inputName.value = null
            inputEmail.value = null
        } else {
            updateSubscriber(subscriberToUpdateOrDelete)
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete)
            deleteSubscriber(subscriberToUpdateOrDelete)
        else
            clearAllSubscribers()
    }

    private fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.insertSubscriber(subscriber)
    }

    private fun deleteSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.delete(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    private fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.update(subscriber)
        subscriberRepository.delete(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    private fun clearAllSubscribers() = viewModelScope.launch {
        subscriberRepository.deleteAllSubscribers()
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email

        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber

        saveOrUpdateButton.value = "Update"
        clearAllOrDeleteButton.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}