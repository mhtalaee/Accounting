package ir.goldenmind.accounting.features.summaryShow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.features.summaryShow.SummaryViewModel
import ir.goldenmind.accounting.features.summaryShow.adapter.ExpensesRecyclerAdapter
import ir.goldenmind.accounting.pojo.Expense
import kotlinx.android.synthetic.main.fragment_expense.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ExpenseFragment : Fragment() {

    lateinit var viewModel: SummaryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        initRecyclerView()
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    private fun initRecyclerView() {

        viewModel = ViewModelProviders.of(this).get(SummaryViewModel::class.java)

        var list : List<Expense>? = null

        viewModel.expenseItem.observe(this, Observer {
            list = it
            rvExpenseList.adapter = ExpensesRecyclerAdapter(list!!)
            rvExpenseList.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        })

        viewModel.getExpenseList()


    }


}
