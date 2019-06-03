package ir.goldenmind.accounting.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ir.goldenmind.accounting.pojo.Income

@Dao
interface AccountDao {

    @Query("SELECT * FROM income")
    fun getAll(): Observable<List<Income>>

    @Insert
    fun insertAll(vararg incomes: Income?): Completable

    @Delete
    fun delete(income: Income)
}