package com.mteam.collector.Services

import com.mteam.collector.Model.CollectNumber

object DataService {

    var collectionNumbers = listOf(
        CollectNumber(1, false)
    )

    fun getCollection() : List<CollectNumber> {
        for (i in 2..100) {
            this.collectionNumbers += CollectNumber(i, false)
        }
        return collectionNumbers
    }

}