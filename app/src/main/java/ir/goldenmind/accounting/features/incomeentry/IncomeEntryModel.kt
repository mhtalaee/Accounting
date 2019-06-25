package ir.goldenmind.accounting.features.incomeentry

import android.app.Application
import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Income
import ir.goldenmind.accounting.repository.db.AccountDatabase

class IncomeEntryModel(application: Application) {

    val db: AccountDatabase? = AccountDatabase.getDatabase(application)

    fun getSumIncomes(): Observable<Long> {
        return db!!.accountDao().getSumIncomes()
    }

    fun insertIncome(income: Income): Completable {
        return db!!.accountDao().insertIncome(income)
    }

    fun getSumExpenses(): Observable<Long> {
        return db!!.accountDao().getSumExpenses()
    }


}