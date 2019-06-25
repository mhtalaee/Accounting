package ir.goldenmind.accounting.features.summaryShow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.features.expenseentry.ExpenseEntryActivity
import ir.goldenmind.accounting.features.expenseentry.ExpenseEntryViewModel
import ir.goldenmind.accounting.features.incomeentry.IncomeEntryActivity
import ir.goldenmind.accounting.features.summaryShow.adapter.SummaryAdapter
import ir.goldenmind.accounting.pojo.Expense
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var mToggle: ActionBarDrawerToggle? = null
    lateinit var viewModel: SummaryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDrawer()

        initButtons()

        initRecyclerView()

    }

    private fun initDrawer() {
        mToggle = ActionBarDrawerToggle(
            this, drawer_layout,
            R.string.open,
            R.string.close
        )
        drawer_layout.addDrawerListener(mToggle!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToggle!!.syncState()
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

    private fun initRecyclerView() {

        viewModel = ViewModelProviders.of(this).get(SummaryViewModel::class.java)

        var list : List<Expense>? = null

        viewModel.expenseItem.observe(this, Observer {
            list = it
            rvExpenseList.adapter = SummaryAdapter(list!!)
            rvExpenseList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        })

        viewModel.getSummaryList()


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (mToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        drawer_layout.closeDrawers()
        super.onResume()
    }


}


