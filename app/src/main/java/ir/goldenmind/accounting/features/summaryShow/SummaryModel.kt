package ir.goldenmind.accounting.features.summaryShow

import android.app.Application
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.pojo.Income
import ir.goldenmind.accounting.repository.db.AccountDatabase

class SummaryModel(application: Application) {

    val db: AccountDatabase? = AccountDatabase.getDatabase(application)

    fun getExpenseList(): Observable<List<Expense>> {
        return db!!.accountDao().getExpensesFromDB()
    }

    fun getIncomeList(): Observable<List<Income>> {
        return db!!.accountDao().getIncomesFromDB()
    }
}