package com.scientiavitae.pitassistant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.registerReceiver
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.scientiavitae.pitassistant.databinding.FragmentEightDeckBinding

class EightDeckFragment : Fragment() {

    private lateinit var binding: FragmentEightDeckBinding
    private val countBroadcastReceiver = CountBroadcastReceiver()
    private var currentCount = 0
    private var countVisible = false



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

        val backgroundColor = activity?.let { ContextCompat.getColor(it.applicationContext, R.color.app_background) }
        val textColor = activity?.let { ContextCompat.getColor(it.applicationContext, R.color.table_header_text) }

        // Start off with the Count being "invisible" by setting text color the same as the
        // background, since I could never actually make an invisible view interactable.
        if (backgroundColor != null) {
            binding.buttonCount.setTextColor(backgroundColor)
        }

        // Clear the count when long pressed
        binding.buttonCount.setOnLongClickListener {
            currentCount = 0
            binding.buttonCount.text = "Count: " + currentCount
            true
        }

        // Pressing the Count button will toggle the "visibility" of the current count
        binding.buttonCount.setOnClickListener {
            when (countVisible) {
                true -> {
                    if (backgroundColor != null) {
                        binding.buttonCount.setTextColor(backgroundColor)
                    }
                    countVisible = false
                }
                false -> {
                    if (textColor != null) {
                        binding.buttonCount.setTextColor(textColor)
                    }
                    countVisible = true
                }
            }
        }


    }


    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(countBroadcastReceiver)
    }


    override fun onResume() {
        super.onResume()
        this.context?.let {
            registerReceiver(
                it, countBroadcastReceiver,
                IntentFilter("com.scientiavitae.pitassistant.COUNT_DIRECTION"),
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
        }
    }


    inner class CountBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null) {
                return
            }
            val countDirection = intent.getStringExtra("COUNT_DIRECTION")
            Log.d("Broadcast", "Broadcast received: " + countDirection + " received.")
            when (countDirection) {
                "UP" -> currentCount++
                "DOWN" -> currentCount--
                "RESET", null -> currentCount = 0
            }
            binding.buttonCount.text = "Count: " + currentCount
        }
    }


    private fun toggleButtonVisibility() {

    }


    private fun buildChart(): MutableList<MutableList<String>> {
        //var headers = mutableListOf( "Your\nCards", "2", "3", "4", "5", "6", "7", "8", "9", "T", "A" )
        var chart = mutableListOf(
            // Hard Totals
            mutableListOf("Hard", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("5-8", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("9", "H", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("10", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"),
            mutableListOf("11", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh"),
            mutableListOf("12", "H", "H", "S", "S", "S", "H", "H", "H", "H", "H"),
            mutableListOf("13", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H"),
            mutableListOf("14", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H"),
            mutableListOf("15", "S", "S", "S", "S", "S", "H", "H", "H", "Rh", "Rh"),
            mutableListOf("16", "S", "S", "S", "S", "S", "H", "H", "Rh", "Rh", "Rh"),
            mutableListOf("17", "S", "S", "S", "S", "S", "S", "S", "S", "S", "Rs"),
            mutableListOf("18+", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),

            // Soft Totals
            mutableListOf(""),
            mutableListOf("Soft", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("13", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("14", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("15", "H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("16", "H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("17", "H", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("18", "Ds", "Ds", "Ds", "Ds", "Ds", "S", "S", "H", "H", "H"),
            mutableListOf("19", "S", "S", "S", "S", "Ds", "S", "S", "S", "S", "S"),
            mutableListOf("20+", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),

            // Pairs
            mutableListOf(""),
            mutableListOf("Pair", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("2-2", "P", "P", "P", "P", "P", "P", "H", "H", "H", "H"),
            mutableListOf("3-3", "P", "P", "P", "P", "P", "P", "H", "H", "H", "H"),
            mutableListOf("4-4", "H", "H", "H", "P", "P", "H", "H", "H", "H", "H"),
            mutableListOf("5-5", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"),
            mutableListOf("6-6", "P", "P", "P", "P", "P", "H", "H", "H", "H", "H"),
            mutableListOf("7-7", "P", "P", "P", "P", "P", "P", "H", "H", "H", "H"),
            mutableListOf("8-8", "P", "P", "P", "P", "P", "P", "P", "P", "P", "Rp"),
            mutableListOf("9-9", "P", "P", "P", "P", "P", "S", "P", "P", "S", "S"),
            mutableListOf("T-T", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
            mutableListOf("A-A", "P", "P", "P", "P", "P", "P", "P", "P", "P", "P")
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
            "S" -> {
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
            "SP" -> {
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


//    public fun onKeyPress(keyCode: Int, event: KeyEvent?) {
//        when (keyCode) {
//            KeyEvent.KEYCODE_VOLUME_UP -> {
//                currentCount++
//                binding.buttonCount.text = "Count: " + currentCount
//                binding.buttonCount.visibility = VISIBLE
//            }
//            KeyEvent.KEYCODE_VOLUME_DOWN -> {
//                currentCount--
//                binding.buttonCount.text = "Count: " + currentCount
//                binding.buttonCount.visibility = VISIBLE
//            }
//            KeyEvent.KEYCODE_BACK -> binding.buttonCount.visibility = INVISIBLE
//        }
//
//
//    }




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

