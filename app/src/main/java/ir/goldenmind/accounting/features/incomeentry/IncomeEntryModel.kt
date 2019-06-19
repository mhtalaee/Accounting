package ir.goldenmind.accounting.features.incomeentry

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Income
import ir.goldenmind.accounting.repository.db.AccountDatabase

class IncomeEntryModel {

    var db: AccountDatabase? = null

    fun insertIncome(context: Context, income: Income): Completable {

        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()

        return db!!.accountDao().insertIncome(income)

    }

    fun getSumIncomes(context: Context): Observable<Long> {

        db = Room.databaseBuilder(context, AccountDatabase::class.java, "accountDB").build()

        return db!!.accountDao().getSumIncomes()

    }

}