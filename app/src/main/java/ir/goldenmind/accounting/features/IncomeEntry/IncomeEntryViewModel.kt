package ir.goldenmind.accounting.features.IncomeEntry

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Income

class IncomeEntryViewModel : ViewModel() {

    val entryStatus = MutableLiveData<Boolean>()
    val sumIncomes = MutableLiveData<Long>()
    lateinit var incomes : List<Income>
    val repository = IncomeEntryModel()

    fun incomeEntry(context: Context, income: Income) {

        repository.insertIncome(context, income)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                entryStatus.value = true
            },
                { error -> Log.e(TAG, "Unable to insert income", error) }
            )
    }


    fun getSumIncomes(context: Context) {

        repository.getSumIncomes(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sumIncomes.value = it
            },
                { error -> Log.e(TAG, "Unable to insert income", error) }
            )
    }


    fun getIncomeList(context: Context) {
        repository.getIncomes(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                incomes = it
            })
    }

}