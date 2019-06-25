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
    val repository = ExpenseEntryModel(application)
    val sumExpenses = MutableLiveData<Long>()
    val remained = MutableLiveData<Long>()
    val composite = CompositeDisposable()
    val context = getApplication<Application>().applicationContext

    fun saveExpense(expense: Expense) {

        composite.add(
            repository.insertExpense(expense)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        entryStatus.value = true
                    },
                    {
                        Log.e(ContentValues.TAG, "Unable to insert expense", it)
                    }
                )
        )

    }

    fun getSumExpenses() {

        composite.add(
            repository.getSumExpenses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    sumExpenses.value = it

                    repository.getSumIncomes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            remained.value = it - sumExpenses.value!!
                        }

                },
                {
                    Log.e(ContentValues.TAG, "Unable to insert expense", it)
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}