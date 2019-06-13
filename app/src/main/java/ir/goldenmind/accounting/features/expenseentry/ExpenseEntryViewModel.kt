package ir.goldenmind.accounting.features.expenseentry

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Expense

class ExpenseEntryViewModel : ViewModel() {

    val entryStatus = MutableLiveData<Boolean>()
    val repository = ExpenseEntryModel()
    val sumExpenses = MutableLiveData<Long>()
    val remained = MutableLiveData<Long>()

    @SuppressLint("CheckResult")
    fun saveExpense(context: Context, expense: Expense) {

        repository.insertExpense(context, expense)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                entryStatus.value = true
            },
                { error ->
                    Log.e(ContentValues.TAG, "Unable to insert expense", error)
                })
    }

    @SuppressLint("CheckResult")
    fun getSumExpenses(context: Context) {
        repository.getSumExpenses(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    sumExpenses.value = it
                },
                { error ->
                    Log.e(ContentValues.TAG, "Unable to insert income", error)
                }
            )
    }

    fun getRemained(context: Context) {
        remained.value = repository.getRemained(context)
    }

}