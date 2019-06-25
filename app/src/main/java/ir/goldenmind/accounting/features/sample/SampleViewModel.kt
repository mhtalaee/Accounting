package ir.goldenmind.accounting.features.sample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.pojo.Expense

class SampleViewModel(application: Application) : AndroidViewModel(application) {

    var expensesList = MutableLiveData<List<Expense>>()
//    val context = getApplication<Application>().applicationContext
    val sampleModel : SampleModel = SampleModel(application)

    fun getList() {

        sampleModel.getSummaryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                expensesList.value = it
            }, {
                Log.d("Accounting", "Error fetching list")

            }
            )
    }

    fun addData(expense: Expense): Completable {
        return sampleModel.insertExpense(expense)
    }

}