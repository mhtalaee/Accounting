package ir.goldenmind.accounting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import ir.goldenmind.accounting.features.expenseentry.ExpenseEntryActivity
import ir.goldenmind.accounting.features.incomeentry.IncomeEntryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initButtons()


    }

    private fun initButtons() {

        btnSaveIncome.setOnClickListener {
            val intentToIncomeEntry = Intent(this, IncomeEntryActivity::class.java)
            startActivity(intentToIncomeEntry)
        }

        btnSaveExpense.setOnClickListener {
            val intentToExpenseEntry = Intent(this, ExpenseEntryActivity::class.java)
            startActivity(intentToExpenseEntry)
        }

    }

    private fun init() {
        mToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(mToggle!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToggle!!.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (mToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


