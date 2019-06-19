package ir.goldenmind.accounting.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.goldenmind.accounting.pojo.Expense
import ir.goldenmind.accounting.pojo.Income

@Dao
interface AccountDao {


    //Income DAO
    @Insert
    fun insertIncome(vararg incomes: Income?): Completable

    @Query("SELECT sum(amount) FROM income")
    fun getSumIncomes(): Observable<Long>


    //Expense DAO
    @Insert
    fun insertExpense(expense: Expense): Completable

    @Query("SELECT sum(amount) FROM expense")
    fun getSumExpenses(): Observable<Long>

    //Summary DAO
    @Query("SELECT * FROM expense")
    fun getSummaries() : Observable<Expense>
}