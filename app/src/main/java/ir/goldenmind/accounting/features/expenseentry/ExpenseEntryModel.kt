package ir.goldenmind.accounting.features.expenseentry

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.repository.db.AccountDatabase

class ExpenseEntryModel {

    var db: AccountDatabase? = null

    fun insertExpense(context: Context, expense: Expense): Completable {

        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()
        return db!!.accountDao().insertExpense(expense)

    }

    fun getSumExpenses(context: Context): Observable<Long> {

        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()

        return db!!.accountDao().getSumExpenses()

    }

    @SuppressLint("CheckResult")
    fun getRemained(context: Context): Observable<Long> {

        val sumExpenses = db!!.accountDao().getSumExpenses()
        val sumIncomes = db!!.accountDao().getSumIncomes()

        return Observable.concat(sumExpenses, sumIncomes)

    }

}