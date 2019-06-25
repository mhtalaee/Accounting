package ir.goldenmind.accounting.features.expenseentry

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.repository.db.AccountDatabase

class ExpenseEntryModel(application: Application) {

    val db: AccountDatabase? = AccountDatabase.getDatabase(application)

    fun getSumExpenses(): Observable<Long> {
        return db!!.accountDao().getSumExpenses()
    }

    fun insertExpense(expense: Expense): Completable {
        return db!!.accountDao().insertExpense(expense)
    }

    fun getSumIncomes(): Observable<Long> {
        return db!!.accountDao().getSumIncomes()
    }

}