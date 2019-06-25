package ir.goldenmind.accounting.features.summaryShow

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Expense

class SummaryViewModel(application: Application) : AndroidViewModel(application) {

    val repository = SummaryModel(application)
    val context = getApplication<Application>().applicationContext
    val composite = CompositeDisposable()
    val expenseItem = MutableLiveData<List<Expense>>()

    fun getSummaryList() {

        composite.add(repository.getSummaryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    expenseItem.value = it
                },
                { Log.d("SummaryViewModel:", it.message) }
            )
        )

    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}