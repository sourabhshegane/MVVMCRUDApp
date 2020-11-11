package com.sourabhcodes.android.mvvmcrudapp.db

class SubscriberRepository(private var dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insertSubscriber(subscriber: Subscriber) {
        dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber) {
        dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) {
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAllSubscribers() {
        dao.deleteAllSubscribers()
    }
}