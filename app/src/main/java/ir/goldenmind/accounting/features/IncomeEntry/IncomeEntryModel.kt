package ir.goldenmind.accounting.features.IncomeEntry

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Income
import ir.goldenmind.accounting.repository.AccountDatabase

class IncomeEntryModel {

    var db: AccountDatabase? = null


    fun insertIncome(context: Context, income: Income): Completable {

        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()

        return db!!.accountDao().insertAll(income)

    }

    fun getIncomes(context: Context): Observable<List<Income>> {

        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()
        return db!!.accountDao().getAll()
    }

}