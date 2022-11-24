package com.scientiavitae.pitassistant

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.scientiavitae.pitassistant.databinding.FragmentEightDeckBinding

class EightDeckFragment : Fragment() {


    private lateinit var binding: FragmentEightDeckBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEightDeckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var chart: MutableList<MutableList<String>> = buildChart()
        buildTableView(chart)

    }

//    private fun buildChartHashMap(): HashMap<String, String> {
//        val basicStrategyEightDeck = HashMap<String, String>()
///*
//        Hard
//        DH:23456789TA
//        05:HHHHHHHHHH
//        06:HHHHHHHHHH
//        07:HHHHHHHHHH
//        08:HHHDDHHHHH
//        09:DDDDDHHHHH
//        10:DDDDDDDDHH
//        11:DDDDDDDDDD
//        12:HHSSSHHHHH
//        13:SSSSSHHHHH
//        14:SSSSSHHHHH
//        15:SSSSSHHHHH
//        16:SSSSSHHHHH
//        17:SSSSSSSSSS
//        18:SSSSSSSSSS
//        19:SSSSSSSSSS
//        20:SSSSSSSSSS
//        21:SSSSSSSSSS
//
//        Soft
//        A2:HHDDDHHHHH
//        A3:HHDDDHHHHH
//        A4:HHDDDHHHHH
//        A5:HHDDDHHHHH
//        A6:DDDDDHHHHH
//        A7:SDDDDSSHHS
//        A8:SSSSDSSSSS
//        A9:SSSSSSSSSS
//        AT:SSSSSSSSSS
//
//        Pair:
//        22:PPPPPPHHHH
//        33:PPPPPPPHHH
//        44:HHPPPHHHHH
//        55:DDDDDDDDHH
//        66:PPPPPPHHHH
//        77:PPPPPPPHSH
//        88:PPPPPPPPPP
//        99:PPPPPSPPSS
//        TT:SSSSSSSSSS
//        AA:PPPPPPPPPP
//
//*/
//
//        for val chartReadPos
//
//
//
//
//
//        basicStrategyEightDeck["5v2"] = "H"
//
//        return basicStrategyEightDeck
//    }

    private fun buildChart(): MutableList<MutableList<String>> {
        //var headers = mutableListOf( "Your\nCards", "2", "3", "4", "5", "6", "7", "8", "9", "T", "A" )
        var chart = mutableListOf(
            // Hard Totals
            mutableListOf("Hard", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("5-8", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("9", "H", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("10", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"),
            mutableListOf("11", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh"),
            mutableListOf("12", "H", "H", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("13", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("14", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("15", "X", "X", "X", "X", "X", "H", "H", "H", "Rh", "Rh"),
            mutableListOf("16", "X", "X", "X", "X", "X", "H", "H", "Rh", "Rh", "Rh"),
            mutableListOf("17", "X", "X", "X", "X", "X", "X", "X", "X", "X", "Rs"),
            mutableListOf("18+", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),

            // Soft Totals
            mutableListOf(""),
            mutableListOf("Soft", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("13", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("14", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("15", "H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("16", "H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("17", "H", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("18", "Ds", "Ds", "Ds", "Ds", "Ds", "X", "X", "H", "H", "H"),
            mutableListOf("19", "X", "X", "X", "X", "Ds", "X", "X", "X", "X", "X"),
            mutableListOf("20+", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),

            // Pairs
            mutableListOf(""),
            mutableListOf("Pair", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("2-2", "S", "S", "S", "S", "S", "S", "H", "H", "H", "H"),
            mutableListOf("3-3", "S", "S", "S", "S", "S", "S", "H", "H", "H", "H"),
            mutableListOf("4-4", "H", "H", "H", "S", "S", "H", "H", "H", "H", "H"),
            mutableListOf("5-5", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"),
            mutableListOf("6-6", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H"),
            mutableListOf("7-7", "S", "S", "S", "S", "S", "S", "H", "H", "H", "H"),
            mutableListOf("8-8", "S", "S", "S", "S", "S", "S", "S", "S", "S", "Rp"),
            mutableListOf("9-9", "S", "S", "S", "S", "S", "X", "S", "S", "X", "X"),
            mutableListOf("T-T", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),
            mutableListOf("A-A", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S")
        )

        return chart
    }

    private fun buildTableView(chart: MutableList<MutableList<String>>) {
        for (currentRow in chart) {
            val tableRow = TableRow(this.context)
            for (currentCell in currentRow) {
                val cellTextView = buildTextView(currentCell)
                tableRow.addView(cellTextView)
            }
            tableRow.gravity = Gravity.CENTER
            binding.tableLayoutEightDeck.addView(tableRow)
        }
    }

    private fun buildTextView(cellContents: String): TextView {
        val cellTextView = TextView(this.context)
        cellTextView.text = cellContents
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
                cellTextView.gravity = Gravity.END
                cellTextView.setPadding(0, 0, 8, 0)
            }

        }

        return cellTextView
    }
}