package ir.goldenmind.accounting.features.summaryShow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.features.expenseentry.ExpenseEntryActivity
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

        val list = arrayListOf<Expense>()
//        list.add(Expense("2019/06/17",35000, "expense one"))
//        list.add(Expense("2019/06/18",55000, "expense two"))

        viewModel.expenseItem.observe(this, Observer {
            list.add(it)
        })

        viewModel.getSummaryList()

        rvExpenseList.adapter = SummaryAdapter(list)
        rvExpenseList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (mToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


