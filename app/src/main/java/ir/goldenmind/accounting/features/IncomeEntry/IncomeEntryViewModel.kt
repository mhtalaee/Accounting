package ir.goldenmind.accounting.features.IncomeEntry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.goldenmind.accounting.pojo.Income

class IncomeEntryViewModel : ViewModel(){

    val entryStatus = MutableLiveData<Boolean>()

    fun incomeEntry(income : Income){

    }

}