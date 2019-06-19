package ir.goldenmind.accounting.features.summaryShow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Expense
import kotlinx.android.synthetic.main.summary_recycler_view_item.view.*

class SummaryAdapter(val expenseList: ArrayList<Expense>) : RecyclerView.Adapter<SummaryAdapter.SummaryViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHoler {

        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.summary_recycler_view_item, parent, false)
        val viewHolder = SummaryViewHoler(inflatedView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: SummaryViewHoler, position: Int) {
        holder.bindItems(expenseList[position])
    }

    override fun getItemCount() = expenseList.size

    class SummaryViewHoler(val v: View) : RecyclerView.ViewHolder(v) {

        fun bindItems(expense: Expense) {
            v.tvDate.text = expense.date.toString()
            v.tvAmount.text = expense.amount.toString()
        }

    }

}