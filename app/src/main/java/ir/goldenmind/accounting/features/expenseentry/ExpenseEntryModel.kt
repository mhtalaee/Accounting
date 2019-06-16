package ir.goldenmind.accounting.features.expenseentry

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.repository.AccountDatabase

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

//        var sumExpenses: Long = 0
//        var sumIncomes: Long = 0
//
//        db!!.accountDao().getSumExpenses()
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                sumExpenses = it
//            }
//
//        db!!.accountDao().getSumIncomes()
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                sumIncomes = it
//            }

//        return sumIncomes - sumExpenses


        return db!!.accountDao().getSumExpenses()
            .mergeWith(
                db!!.accountDao().getSumIncomes()
            )

    }

}