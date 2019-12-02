package ir.goldenmind.accounting.features.summaryShow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Expense
import kotlinx.android.synthetic.main.expense_recycler_view_item.view.*

class ExpensesRecyclerAdapter(val expenseList: List<Expense>) : RecyclerView.Adapter<ExpensesRecyclerAdapter.SummaryViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHoler {

        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.expense_recycler_view_item, parent, false)
        val viewHolder = SummaryViewHoler(inflatedView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: SummaryViewHoler, position: Int) {
        holder.bindItems(expenseList[position])
    }

    override fun getItemCount() = expenseList.size

    class SummaryViewHoler(val v: View) : RecyclerView.ViewHolder(v) {

        fun bindItems(expense: Expense) {
            v.tvDate.text = expense.date
            v.tvAmount.text = expense.amount.toString()
            v.tvComment.text = expense.comment
        }

    }

}