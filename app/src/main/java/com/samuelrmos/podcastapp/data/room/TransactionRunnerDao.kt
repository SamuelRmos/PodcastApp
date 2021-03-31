package com.samuelrmos.podcastapp.data.room

import androidx.room.Dao
import androidx.room.Transaction

@Dao
abstract class TransactionRunnerDao : TransactionRunner {
    @Transaction
    protected open suspend fun runInTransaction(tx: suspend () -> Unit) = tx()

    override suspend fun invoke(tx: suspend () -> Unit) {
        runInTransaction(tx)
    }
}

interface TransactionRunner {
    suspend operator fun invoke(tx: suspend () -> Unit)
}