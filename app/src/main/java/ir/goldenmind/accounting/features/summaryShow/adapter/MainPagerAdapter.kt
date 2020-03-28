package ir.goldenmind.accounting.features.summaryShow.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ir.goldenmind.accounting.features.summaryShow.fragments.ExpenseFragment
import ir.goldenmind.accounting.features.summaryShow.fragments.IncomeFragment

class MainPagerAdapter (fragmentManager: FragmentManager, behavior : Int): FragmentPagerAdapter(fragmentManager, behavior) {

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> return ExpenseFragment()
            1 -> return IncomeFragment()
            else -> return ExpenseFragment()
        }

    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Expense List"
            1 -> return "Income List"
            else -> return "Expense LIst"
        }
    }

}