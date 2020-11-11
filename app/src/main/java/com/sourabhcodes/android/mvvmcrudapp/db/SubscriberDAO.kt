package com.sourabhcodes.android.mvvmcrudapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("Delete from subscriber")
    suspend fun deleteAllSubscribers()

    @Query("Select * from subscriber")
    fun getAllSubscribers(): LiveData<ArrayList<Subscriber>>
}