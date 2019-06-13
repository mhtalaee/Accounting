package ir.goldenmind.accounting.features.incomeentry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.goldenmind.accounting.pojo.Income
import kotlinx.android.synthetic.main.activity_income_entry.*
import java.util.*
import android.app.DatePickerDialog
import java.text.SimpleDateFormat


class IncomeEntryActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var viewModel: IncomeEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ir.goldenmind.accounting.R.layout.activity_income_entry)

        init()
        initButtons()

    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(IncomeEntryViewModel::class.java)

        viewModel.getSumIncomes(this)

        viewModel.entryStatus.observe(this, Observer {
            Toast.makeText(this@IncomeEntryActivity, "New income added!", Toast.LENGTH_SHORT).show()
        })

        viewModel.sumIncomes.observe(this, Observer {
            tvTotalIncome.text = it.toString()
        })


        val myCalendar = Calendar.getInstance()

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }


        etDate.setOnClickListener {

            // TODO Auto-generated method stub
            DatePickerDialog(
                this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }

    fun updateLabel(myCalendar: Calendar) {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etDate.setText(sdf.format(myCalendar.getTime()));

    }

    private fun initButtons() {

        btnSaveIncome.setOnClickListener {

            viewModel.saveIncome(
                this,
                Income(etDate.text.toString(), etAmount.text.toString().toLong(), etComment.text.toString())
            )
            viewModel.getSumIncomes(this)

        }

    }
}
