package ir.goldenmind.accounting.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.goldenmind.accounting.pojo.Income

@Database(entities = arrayOf(Income::class), version = 1)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}