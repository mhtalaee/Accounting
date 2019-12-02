package ir.goldenmind.accounting.features.sample

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.repository.db.AccountDatabase

class SampleModel(application : Application) {

    val db : AccountDatabase? = AccountDatabase.getDatabase(application)

    fun getSummaryList(): Observable<List<Expense>> {
        return db!!.accountDao().getExpensesFromDB()
    }


    fun insertExpense(expense: Expense): Completable {

        return db!!.accountDao().insertExpense(expense)

    }
}