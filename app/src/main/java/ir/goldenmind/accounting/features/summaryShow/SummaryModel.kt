package ir.goldenmind.accounting.features.summaryShow

import android.app.Application
import android.content.Context
import androidx.room.Room
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.repository.db.AccountDatabase

class SummaryModel(application: Application) {

    val db: AccountDatabase? = AccountDatabase.getDatabase(application)

    fun getSummaryList(): Observable<List<Expense>> {
        return db!!.accountDao().getSummaries()
    }

}