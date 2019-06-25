package ir.goldenmind.accounting.features.expenseentry

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
        initCalendar()
        initButtons()

    }

    private fun init() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = supportActionBar?.title.toString().plus(" > Expense")


        viewModel = ViewModelProviders.of(this).get(ExpenseEntryViewModel::class.java)

        viewModel.sumExpenses.observe(this, Observer {
            tvTotalExpense.text = it.toString()
        })

        viewModel.getSumExpenses()

        viewModel.remained.observe(this, Observer {
            tvRemained.text = it.toString()
        })

    }

    private fun initCalendar() {
        val myCalendar = Calendar.getInstance()

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        etDate.setOnClickListener {

            DatePickerDialog(
                this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
    }

    private fun initButtons() {

        btnSaveExpense.setOnClickListener {
            viewModel.saveExpense(
                Expense(
                    etDate.text.toString(),
                    etAmount.text.toString().toLong(),
                    etComment.text.toString()
                )
            )
        }
    }

    fun updateLabel(myCalendar: Calendar) {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etDate.setText(sdf.format(myCalendar.getTime()));

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
