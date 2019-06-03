package ir.goldenmind.accounting.features.IncomeEntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Income
import kotlinx.android.synthetic.main.activity_income_entry.*

class IncomeEntryActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var viewModel: IncomeEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_entry)

        viewModel = ViewModelProviders.of(this).get(IncomeEntryViewModel::class.java)

        initButtons()

        viewModel.entryStatus.observe(this, Observer {

            Toast.makeText(this@IncomeEntryActivity, "$it", Toast.LENGTH_LONG).show()

        })

    }

    private fun initButtons() {

        btnSaveIncome.setOnClickListener{

            viewModel.incomeEntry(this,Income(etDate.text.toString(), etAmount.text.toString().toLong(), etComment.text.toString()))
            viewModel.getIncomeList(this)

        }

    }
}
