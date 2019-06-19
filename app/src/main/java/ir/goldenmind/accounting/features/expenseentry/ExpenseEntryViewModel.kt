package ir.goldenmind.accounting.features.expenseentry

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Expense

class ExpenseEntryViewModel(application: Application) : AndroidViewModel(application) {

    val entryStatus = MutableLiveData<Boolean>()
    val repository = ExpenseEntryModel()
    val sumExpenses = MutableLiveData<Long>()
    val remained = MutableLiveData<Long>()
    val composite = CompositeDisposable()
    val context = getApplication<Application>().applicationContext

    fun saveExpense(expense: Expense) {

        composite.add(
            repository.insertExpense(context, expense)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    entryStatus.value = true
                },
                    { error ->
                        Log.e(ContentValues.TAG, "Unable to insert expense", error)
                    })
        )
    }

    fun getSumExpenses() {
        composite.add(repository.getSumExpenses(context)
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
        )
    }

    fun getRemained() {

        var lastItem = 0L

        composite.add(repository.getRemained(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                remained.value = it - lastItem
                lastItem = it

            },
                {
                    Log.d("REMAINED_ERROR", it.message)
                }

            ))
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}