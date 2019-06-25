package ir.goldenmind.accounting.features.incomeentry

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Income

class IncomeEntryViewModel(application: Application) : AndroidViewModel(application) {

    val entryStatus = MutableLiveData<Boolean>()
    val sumIncomes = MutableLiveData<Long>()
    val repository = IncomeEntryModel(application)
    val remained = MutableLiveData<Long>()
    val composite = CompositeDisposable()

    fun saveIncome(income: Income) {

        composite.add(repository.insertIncome(income)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                entryStatus.value = true
            },
                { error -> Log.e(TAG, "Unable to insert income", error) }
            )
        )
    }

    fun getSumIncomes() {

        composite.add(repository.getSumIncomes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sumIncomes.value = it

                repository.getSumExpenses()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        remained.value = sumIncomes.value!! - it

                    }

            },
                { Log.e(TAG, "Unable to insert income", it) }
            )
        )
    }

}