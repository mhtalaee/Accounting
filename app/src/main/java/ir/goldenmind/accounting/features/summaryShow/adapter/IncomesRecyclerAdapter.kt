package ir.goldenmind.accounting.features.summaryShow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Income
import kotlinx.android.synthetic.main.expense_recycler_view_item.view.*

class IncomesRecyclerAdapter(val incomeList: List<Income>) : RecyclerView.Adapter<IncomesRecyclerAdapter.IncomeViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHoler {

        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.income_recycler_view_item, parent, false)
        val viewHolder = IncomeViewHoler(inflatedView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: IncomeViewHoler, position: Int) {
        holder.bindItems(incomeList[position])
    }

    override fun getItemCount() = incomeList.size

    class IncomeViewHoler(val v: View) : RecyclerView.ViewHolder(v) {

        fun bindItems(income: Income) {
            v.tvDate.text = income.date
            v.tvAmount.text = income.amount.toString()
            v.tvComment.text = income.comment
        }

    }

}