package com.scientiavitae.pitassistant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.scientiavitae.pitassistant.databinding.FragmentTwoDeckBinding

class TwoDeckFragment : Fragment() {

    private lateinit var binding: FragmentTwoDeckBinding
    private val dealerCardsList = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "T", "A")
    private val playerCardsList = arrayOf(
        "Hard", "H5-8", "H9", "H10", "H11", "H12", "H13", "H14", "H15", "H16", "H17", "H18+",
        "Soft", "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20+",
        "Pairs", "P2-2", "P3-3", "P4-4", "P5-5", "P6-6", "P7-7", "P8-8", "P9-9", "PT-T", "PA-A")
    private val countBroadcastReceiver = CountBroadcastReceiver()
    private var currentCount = 0
    private var countVisible = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoDeckBinding.inflate(layoutInflater, container, false)
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
            ContextCompat.registerReceiver(
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


    private fun buildChart(): MutableList<MutableList<String>> {
        //var headers = mutableListOf( "Your\nCards", "2", "3", "4", "5", "6", "7", "8", "9", "T", "A" )
        var chart = mutableListOf(
            // Hard Totals
            mutableListOf("Hard", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("5-8", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"),
            mutableListOf("9", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("10", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"),
            mutableListOf("11", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh"),
            mutableListOf("12", "H", "H", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("13", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("14", "X", "X", "X", "X", "X", "H", "H", "H", "H", "H"),
            mutableListOf("15", "X", "X", "X", "X", "X", "H", "H", "H", "Rh", "Rh"),
            mutableListOf("16", "X", "X", "X", "X", "X", "H", "H", "H", "Rh", "Rh"),
            mutableListOf("17", "X", "X", "X", "X", "X", "X", "X", "X", "X", "Rs"),
            mutableListOf("18+", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),

            // Soft Totals
            mutableListOf(""),
            mutableListOf("Soft", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A"),
            mutableListOf("13", "H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"),
            mutableListOf("14", "H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"),
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
            mutableListOf("6-6", "S", "S", "S", "S", "S", "S", "H", "H", "H", "H"),
            mutableListOf("7-7", "S", "S", "S", "S", "S", "S", "S", "H", "H", "H"),
            mutableListOf("8-8", "S", "S", "S", "S", "S", "S", "S", "S", "S", "Rp"),
            mutableListOf("9-9", "S", "S", "S", "S", "S", "X", "S", "S", "X", "X"),
            mutableListOf("T-T", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"),
            mutableListOf("A-A", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S")
        )

        return chart
    }

    /*
    * I think I should load the chart initially with the lowest deviations, then amend the chart
    * as necessary based on the count. If the user isn't tracking the count, then it should just
    * update the chart to currentCount=0. As the count increases/decreases, the chart should then
    * update without a problem, I think...
    *
    * Deviations:
    * 13v3: Stand at -2 or higher, hit otherwise. (Normal basic strategy is: S)
    * 12v5: Stand at -2 or higher, hit otherwise. (Normal basic strategy is: S)
    * 13v2: Stand at -1 or higher, hit otherwise. (Normal basic strategy is: S)
    * 12v6: Stand at -1 or higher, hit otherwise. (Normal basic strategy is: S)
    * 16vT: Stand at 0 or higher, hit otherwise. (Normal basic strategy is: Rh)
    * 12v4: Stand at 0 or higher, hit otherwise. (Normal basic strategy is: S)
    * 11vA: Double at +1 or higher, hit otherwise. (Normal basic strategy is: Dh)
    * 9v2: Double at +1 or higher, hit otherwise. (Normal basic strategy is: Dh)
    * 12v3: Stand at +2 or higher, hit otherwise. (Normal basic strategy is: H)
    * 12v2: Stand at +3 or higher, hit otherwise. (Normal basic strategy is: H)
    * 9v7: Double at +3 or higher, hit otherwise. (Normal basic strategy is: H)
    * 15vT: Stand at +4 or higher, hit otherwise. (Normal basic strategy is: Rh)
    * TTv6: Split at +4 or higher, stand otherwise. (Normal basic strategy is: S)
    * 10vT: Double at +4 or higher, hit otherwise. (Normal basic strategy is: H)
    * 10vA: Double at +4 or higher, hit otherwise. (Normal basic strategy is: H)
    * 16v9: Stand at +5 or higher, hit otherwise. (Normal basic strategy is: H)
    * TTv5: Split at +5 or higher, stand otherwise. (Normal basic strategy is: S)
    *
    * Surrender if:
    * 15v9 at +3 or higher
    * 15vT at 0 or higher
    * 15vA at +2 or higher if S17, or at -1 or higher if dealer H17
    * 14vT at +4 or higher
    *
    * */



    // Gets the table coordinates for the desired deviation. After making this, I don't actually
    // think I need it. I think I can just do something like:
    // if (playerHand in playerCardsList) { playerCoord = playerCardsList.indexOf(playerHand) }
    private fun getTableCoords(playerHand: String, dealerCard: String): Pair<Int, Int> {
//        var playerCoord = 0
//        var dealerCoord = 0
//        for (rowHeader in playerCardsList) {
//            playerCoord = playerCardsList.indexOf(rowHeader)
//        }
//        for (colHeader in dealerCardsList) {
//            dealerCoord = dealerCardsList.indexOf(colHeader)
//        }
//        return Pair(playerCoord, dealerCoord)
        return Pair(playerCardsList.indexOf(playerHand), dealerCardsList.indexOf(dealerCard))
    }



    private fun buildTableView(chart: MutableList<MutableList<String>>) {
        for (currentRow in chart) {
            val tableRow = TableRow(this.context)
            for (currentCell in currentRow) {
                val cellTextView = buildTextView(currentCell)
                tableRow.addView(cellTextView)
            }
            tableRow.gravity = Gravity.CENTER
            binding.tableLayoutTwoDeck.addView(tableRow)
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