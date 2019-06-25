package ir.goldenmind.accounting.features.incomeentry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.goldenmind.accounting.pojo.Income
import kotlinx.android.synthetic.main.activity_income_entry.*
import java.util.*
import android.app.DatePickerDialog
import android.view.MenuItem
import ir.goldenmind.accounting.R
import java.text.SimpleDateFormat

class IncomeEntryActivity : AppCompatActivity() {

    lateinit var viewModel: IncomeEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_entry)

        init()
        initCalendar()
        initButtons()

    }

    private fun init() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = supportActionBar?.title.toString().plus(" > Income")


        viewModel = ViewModelProviders.of(this).get(IncomeEntryViewModel::class.java)

        viewModel.sumIncomes.observe(this, Observer {
            tvTotalIncome.text = it.toString()
        })

        viewModel.getSumIncomes()

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

    private fun initButtons() {

        btnSaveIncome.setOnClickListener {
            viewModel.saveIncome(
                Income(
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
