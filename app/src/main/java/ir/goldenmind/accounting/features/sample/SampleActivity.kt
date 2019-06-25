package ir.goldenmind.accounting.features.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.goldenmind.accounting.R
import ir.goldenmind.accounting.pojo.Expense
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        viewModel = ViewModelProviders.of(this).get(SampleViewModel::class.java)

        viewModel.expensesList.observe(this, Observer {
            Log.d("Accounting", it.size.toString())
        })

        viewModel.getList()




        btnAdd.setOnClickListener {
            viewModel.addData(Expense("1", 2, "3"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Accounting", "Expense Record Added")
                }

        }
    }
}
