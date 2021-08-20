package com.example.testprogrammv2.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testprogrammv2.R
import com.example.testprogrammv2.currency.`object`.xml.RecordV
import com.example.testprogrammv2.currency.graph.GraphObject
import com.example.testprogrammv2.currency.model.CurrencyViewModel
import com.example.testprogrammv2.currency.network.ConnectionResult
import com.example.testprogrammv2.databinding.FragmentCurrencyBinding
import com.example.testprogrammv2.util.BackListener
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class CurrencyFragment : Fragment(){

    private lateinit var binding: FragmentCurrencyBinding
    private lateinit var curViewModel: CurrencyViewModel

    private lateinit var lineChart: LineChart
    private lateinit var bListener: BackListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bListener = BackListener(binding.rlc, requireActivity(), viewLifecycleOwner)

        val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val yesterday = LocalDateTime.now().minusMonths(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        binding.textV.text = getString(R.string.period, yesterday, today)

        binding.lineChart.setNoDataText(getString(R.string.no_text_char))

        curViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        curViewModel.setDate(yesterday, today)

        curViewModel.liveDataCurrency.observe(activity as LifecycleOwner, Observer {
            val regState = it ?: return@Observer
                createGraph(regState)
       })

        binding.swipeRefreshLayout.setOnRefreshListener {
            curViewModel.getValue()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        curViewModel.getValue()
    }


    private fun createGraph(connRes: ConnectionResult) {

        if (connRes.success != null) {
            val regState: ArrayList<RecordV> = connRes.success
            lineChart = GraphObject(binding.lineChart, regState).initiate()
            binding.loading.visibility = View.GONE
            lineChart.invalidate()

            lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry, h: Highlight?) {
                    val xLabel = lineChart.xAxis.valueFormatter.getAxisLabel(e.x, lineChart.xAxis)
                    binding.textI.text =
                        getString(R.string.date_currency, String.format("%.3f", e.y), xLabel)
                }
                override fun onNothingSelected() {}
            })
        }
        else
        {
            binding.loading.visibility = View.GONE
            binding.lineChart.setNoDataText(getString(R.string.internetError))
            binding.lineChart.invalidate()
            Snackbar.make(binding.rlc, connRes.error.toString(), Snackbar.LENGTH_LONG).show()
        }
    }


    companion object {
        fun newInstance() = CurrencyFragment()
    }

}