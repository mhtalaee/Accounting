package ir.goldenmind.accounting.features.IncomeEntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Income
import java.util.*

class IncomeEntryActivity : AppCompatActivity() {

    lateinit var viewModel : IncomeEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_entry)

        viewModel = ViewModelProviders.of(this).get(IncomeEntryViewModel::class.java)

        viewModel.incomeEntry(Income(Date(),50000,"First Income"))
    }
}
