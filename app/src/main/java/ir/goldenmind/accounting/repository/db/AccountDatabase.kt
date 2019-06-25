package ir.goldenmind.accounting.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.pojo.Income
import androidx.room.Room


@Database(entities = arrayOf(Income::class, Expense::class), version = 1)
abstract class AccountDatabase : RoomDatabase() {

    companion object {

        var instance: AccountDatabase? = null
        fun getDatabase(context: Context): AccountDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AccountDatabase::class.java!!, "accountDB").build()
            }
            return instance
        }
    }

    abstract fun accountDao(): AccountDao


}