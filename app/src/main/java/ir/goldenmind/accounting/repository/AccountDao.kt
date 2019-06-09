package ir.goldenmind.accounting.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.goldenmind.accounting.pojo.Income

@Dao
interface AccountDao {

    @Query("SELECT * FROM income")
    fun getAll(): Observable<List<Income>>

    @Query("SELECT sum(amount) FROM income")
    fun getSumIncomes(): Observable<Long>

    @Insert
    fun insertAll(vararg incomes: Income?) : Completable

    @Delete
    fun delete(income: Income)
}