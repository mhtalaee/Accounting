package ir.goldenmind.accounting.features.expenseentry

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Expense
import kotlinx.android.synthetic.main.activity_expense_entry.*
import java.text.SimpleDateFormat
import java.util.*

class ExpenseEntryActivity : AppCompatActivity() {

    lateinit var viewModel: ExpenseEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_entry)

        init()
        initButtons()

    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(ExpenseEntryViewModel::class.java)

        viewModel.getSumExpenses(this)

        viewModel.sumExpenses.observe(this, Observer {
            tvTotalExpense.text = it.toString()
        })




        viewModel.getRemained(this)
        viewModel.remained.observe(this, Observer {
            tvRemained.text = it.toString()
        })


        viewModel.entryStatus.observe(this, Observer {
            Toast.makeText(this, "New expense added!", Toast.LENGTH_SHORT).show()
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

        btnSaveExpense.setOnClickListener {

            viewModel.saveExpense(
                this,
                Expense(etDate.text.toString(), etAmount.text.toString().toLong(), etComment.text.toString())
            )

            viewModel.getSumExpenses(this)
            viewModel.getRemained(this)

        }

    }
}
