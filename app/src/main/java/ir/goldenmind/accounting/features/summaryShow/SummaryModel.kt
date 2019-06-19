package ir.goldenmind.accounting.features.summaryShow

import android.content.Context
import androidx.room.Room
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.repository.db.AccountDatabase

class SummaryModel() {

    var db: AccountDatabase? = null

    fun getSummaryList(context: Context): Observable<Expense> {
        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()
        return db!!.accountDao().getSummaries()
    }
}