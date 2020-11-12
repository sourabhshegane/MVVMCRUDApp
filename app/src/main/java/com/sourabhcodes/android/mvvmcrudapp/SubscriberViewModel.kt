package com.sourabhcodes.android.mvvmcrudapp

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhcodes.android.mvvmcrudapp.db.SubscriberRepository

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel() {
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

    }

    fun clearAllOrDelete() {

    }
}