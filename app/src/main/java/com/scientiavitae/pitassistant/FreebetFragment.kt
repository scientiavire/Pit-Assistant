package com.scientiavitae.pitassistant

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.*
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.scientiavitae.pitassistant.databinding.FragmentFreebetBinding

class FreebetFragment : Fragment() {


    private lateinit var binding: FragmentFreebetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFreebetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val realMoneyChart: MutableList<MutableList<String>> = buildRealMoneyChart()
        buildTableView(realMoneyChart, binding.tableFbRealMoney)

        val freeHandChart: MutableList<MutableList<String>> = buildFreeHandChart()
        buildTableView(freeHandChart, binding.tableFbFreeHand)

        val pairsChart: MutableList<MutableList<String>> = buildPairsChart()
        buildTableView(pairsChart, binding.tableFbPairs)
    }

    private fun buildRealMoneyChart(): MutableList<MutableList<String>> {
        return mutableListOf(
            // Hard Totals
            mutableListOf("Hard", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("5-8", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("9-11", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh"),
            mutableListOf("12", "H", "H", "H", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("13", "H", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("14", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("15", "X", "X", "X", "X", "X", "H", "H", "H", "Rh", "Rh"),
            mutableListOf("16", "X", "X", "X", "X", "X", "H", "H", "Rh", "Rh", "Rh"),
            mutableListOf("17", "X", "X", "X", "X", "X", "X", "X", "X", "X", "Rs"),
            mutableListOf("18+", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),

            // Soft Totals
            mutableListOf(""),
            mutableListOf("Soft", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("12-15", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("16", "H", "H", "H", "H", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("17", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("18", "X", "X", "X", "Ds", "Ds", "X", "X", "H", "H", "H"),
            mutableListOf("19+", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X")
        )
    }

    private fun buildFreeHandChart(): MutableList<MutableList<String>> {
        return mutableListOf(
            // Hard Totals
            mutableListOf("Hard", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("5-8", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("9-11", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh"),
            mutableListOf("12", "H", "H", "H", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("13", "H", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("14", "H", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("15", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("16", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("17", "X", "X", "X", "X", "X", "H", "H", "H", "X", "H"),
            mutableListOf("18+", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),

            // Soft Totals
            mutableListOf(""),
            mutableListOf("Soft", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("12-15", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("16", "H", "H", "H", "H", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("17", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("18", "X", "X", "Ds", "Ds", "Ds", "X", "H", "H", "H", "H"),
            mutableListOf("19", "X", "X", "X", "Ds", "Ds", "X", "X", "X", "X", "X"),
            mutableListOf("20", "X", "X", "X", "X", "Ds", "X", "X", "X", "X", "X"),
            mutableListOf("21", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X")
        )
    }

    private fun buildPairsChart(): MutableList<MutableList<String>> {
        return mutableListOf(
            // Pairs
            mutableListOf("Pair", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("2-2", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("3-3", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("4-4", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("5-5", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh"),
            mutableListOf("6-6", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("7-7", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("8-8", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("9-9", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("T-T", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),
            mutableListOf("A-A", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S")
        )
    }

    private fun buildTableView(chart: MutableList<MutableList<String>>, layout: TableLayout) {
        for (currentRow in chart) {
            val tableRow = TableRow(this.context)
            for (currentCell in currentRow) {
                val cellTextView = buildTextView(currentCell)
                tableRow.addView(cellTextView)
            }
            tableRow.gravity = Gravity.CENTER
            layout.addView(tableRow)
        }
    }

    private fun buildTextView(cellContents: String): TextView {
        val cellTextView = TextView(this.context)
        cellTextView.text = cellContents
        cellTextView.setTextColor(resources.getColor(R.color.table_background))
        cellTextView.gravity = Gravity.CENTER
        cellTextView.setPadding(2)
        cellTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.toFloat())



        when (cellContents) {
            "H" -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_hit))
                cellTextView.gravity = Gravity.CENTER
                cellTextView.setTextColor(resources.getColor(R.color.table_background))
            }
            "X" -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_stand))
                cellTextView.gravity = Gravity.CENTER
                cellTextView.setTextColor(resources.getColor(R.color.table_background))
            }
            "Dh" -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_double_hit))
                cellTextView.gravity = Gravity.CENTER
                cellTextView.setTextColor(resources.getColor(R.color.table_background))
            }
            "Ds" -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_double_stand))
                cellTextView.gravity = Gravity.CENTER
                cellTextView.setTextColor(resources.getColor(R.color.table_background))
            }
            "S" -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_split))
                cellTextView.gravity = Gravity.CENTER
                cellTextView.setTextColor(resources.getColor(R.color.table_background))
            }
            "Rh", "Rs", "Rp" -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_surrender))
                cellTextView.gravity = Gravity.CENTER
                cellTextView.setTextColor(resources.getColor(R.color.table_background))
            }
            else -> {
                cellTextView.setBackgroundColor(resources.getColor(R.color.table_background))
                cellTextView.setTextColor(resources.getColor(R.color.table_header_text))
                cellTextView.gravity = Gravity.RIGHT
                cellTextView.setPadding(0, 0, 8, 0)
            }

        }
        return cellTextView
    }
}